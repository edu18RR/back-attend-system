package com.imjcm.back_attend_system.service;

import com.imjcm.back_attend_system.exception.InternalServerErrorException;
import com.imjcm.back_attend_system.exception.NotFoundException;
import com.imjcm.back_attend_system.model.Attendance;
import com.imjcm.back_attend_system.model.Student;
import com.imjcm.back_attend_system.repository.AttendanceRepository;
import com.imjcm.back_attend_system.repository.StudentRepository;
import com.imjcm.back_attend_system.response.AttendanceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper mapper;

    public AttendanceResponse getAttendanceById(Long id) {
        return mapper.map(findAttendanceById(id), AttendanceResponse.class);
    }

    public List<AttendanceResponse> getAllAttendances() {
        return attendanceRepository.findAll().stream().map(a -> mapper.map(a, AttendanceResponse.class)).collect(Collectors.toList());
    }

    public AttendanceResponse recordStudentToAttendance(Long idAttendance, Long idStudent) {
        Attendance attendance = findAttendanceById(idAttendance);
        attendance.getStudents().add(findStudentById(idStudent));
        return mapper.map(saveAttendance(attendance), AttendanceResponse.class);
    }

    private Attendance findAttendanceById(Long id) {
        return attendanceRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found Attendance with id = " + id));
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found Student with id = " + id));
    }

    private Attendance saveAttendance(Attendance attendance) {
        Attendance attendanceSaved;
        try {
            attendanceSaved = attendanceRepository.save(attendance);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
        return attendanceSaved;
    }

}
