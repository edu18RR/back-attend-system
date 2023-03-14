package com.imjcm.back_attend_system.service;

import com.imjcm.back_attend_system.exception.InternalServerErrorException;
import com.imjcm.back_attend_system.exception.NotFoundException;
import com.imjcm.back_attend_system.model.Student;
import com.imjcm.back_attend_system.repository.StudentRepository;
import com.imjcm.back_attend_system.request.StudentRequest;
import com.imjcm.back_attend_system.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper mapper;

    public StudentResponse createStudent(StudentRequest studentRequest) {
        return mapper.map(saveStudent(mapper.map(studentRequest, Student.class)), StudentResponse.class);
    }

    public StudentResponse getStudentById(Long id) {
        return mapper.map(findStudentById(id), StudentResponse.class);
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream().map(
                        s -> mapper.map(s, StudentResponse.class))
                .collect(Collectors.toList());
    }

    public StudentResponse updateStudentById(Long id, StudentRequest studentRequest) {
        Student student = findStudentById(id);
        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setEmail(studentRequest.getEmail());
        student.setStudentCode(studentRequest.getStudentCode());
        student.setEditedAt(LocalDateTime.now());
        return mapper.map(saveStudent(student), StudentResponse.class);
    }

    public void deleteUserById(Long id) {
        studentRepository.deleteById(findStudentById(id).getId());
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found Student with id = " + id));
    }

    private Student saveStudent(Student student) {
        Student studentSaved;
        try {
            studentSaved = studentRepository.save(student);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());

        }
        return studentSaved;
    }

}

