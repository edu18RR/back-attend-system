package com.imjcm.back_attend_system.service;

import com.imjcm.back_attend_system.mapper.DataMapper;
import com.imjcm.back_attend_system.model.Student;
import com.imjcm.back_attend_system.repository.StudentRepository;
import com.imjcm.back_attend_system.request.StudentRequest;
import com.imjcm.back_attend_system.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student student = DataMapper.buildStudent(studentRequest);
        studentRepository.save(student);
        log.info("Student Saved {}", student);
        return DataMapper.buildStudentDto(student);
    }
}
