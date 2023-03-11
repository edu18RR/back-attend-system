package com.imjcm.back_attend_system.service;

import com.imjcm.back_attend_system.repository.AttendanceRepository;
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
    private final ModelMapper mapper;

    public AttendanceResponse getAttendanceById(Long id) {
        return mapper.map(attendanceRepository.findById(id).orElseThrow(() -> new RuntimeException("this attendance does not exist.")), AttendanceResponse.class);
    }

    public List<AttendanceResponse> getAllAttendances() {
        return attendanceRepository.findAll().stream().map(a -> mapper.map(a, AttendanceResponse.class)).collect(Collectors.toList());
    }
}
