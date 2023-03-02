package com.imjcm.back_attend_system.controller;

import com.imjcm.back_attend_system.response.StudentResponse;
import com.imjcm.back_attend_system.request.StudentRequest;
import com.imjcm.back_attend_system.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest studentRequest) {
        StudentResponse student = studentService.createStudent(studentRequest);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

}
