package com.imjcm.back_attend_system.service;

import com.imjcm.back_attend_system.mapper.DataMapper;
import com.imjcm.back_attend_system.model.Student;
import com.imjcm.back_attend_system.repository.StudentRepository;
import com.imjcm.back_attend_system.request.StudentRequest;
import com.imjcm.back_attend_system.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentResponse createStudent(StudentRequest studentRequest) {
        return DataMapper.buildStudentDto(studentRepository.save(DataMapper.buildStudent(studentRequest)));
    }

    public StudentResponse getStudentById(Long id) {
        return DataMapper.buildStudentDto(studentRepository.findById(id).orElseThrow(() -> new RuntimeException("error get id")));
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream().map(DataMapper::buildStudentDto).collect(Collectors.toList());
    }


    public StudentResponse updateStudentById(Long id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("error update id"));
        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setEmail(studentRequest.getEmail());
        student.setStudentCode(studentRequest.getStudentCode());
        return DataMapper.buildStudentDto(studentRepository.save(student));
    }

    public void deleteUserById(Long id) {
        studentRepository.findById(id).orElseThrow(() -> new RuntimeException("error delete id"));
        studentRepository.deleteById(id);
    }
}

