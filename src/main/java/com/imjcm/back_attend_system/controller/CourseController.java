package com.imjcm.back_attend_system.controller;

import com.imjcm.back_attend_system.request.CourseRequest;
import com.imjcm.back_attend_system.response.CourseResponse;
import com.imjcm.back_attend_system.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/teachers/{id}")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest, @PathVariable Long id) {
        CourseResponse courseResponse = courseService.createCourse(courseRequest, id);
        return new ResponseEntity<>(courseResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id_course}/students/{id_student}")
    public ResponseEntity<CourseResponse> enrolledStudent(@PathVariable Long id_course, @PathVariable Long id_student) {
        CourseResponse courseResponse = courseService.enrolledStudent(id_course, id_student);
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

}
