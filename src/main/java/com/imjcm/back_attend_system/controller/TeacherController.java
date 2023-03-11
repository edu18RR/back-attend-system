package com.imjcm.back_attend_system.controller;

import com.imjcm.back_attend_system.request.TeacherRequest;
import com.imjcm.back_attend_system.response.TeacherResponse;
import com.imjcm.back_attend_system.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody TeacherRequest teacherRequest) {
        return new ResponseEntity<>(teacherService.createTeacher(teacherRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable Long id) {
        return new ResponseEntity<>(teacherService.getTeacherById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
        return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> updateTeacherById(@PathVariable Long id, @RequestBody TeacherRequest teacherRequest) {
        return new ResponseEntity<>(teacherService.updateTeacherById(id, teacherRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacherById(@PathVariable Long id) {
        teacherService.deleteTeacherById(id);
    }
}
