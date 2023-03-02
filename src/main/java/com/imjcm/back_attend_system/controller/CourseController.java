package com.imjcm.back_attend_system.controller;

import com.imjcm.back_attend_system.request.CourseRequest;
import com.imjcm.back_attend_system.response.CourseResponse;
import com.imjcm.back_attend_system.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/courses")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
        return new ResponseEntity<>(courseService.createCourse(courseRequest), HttpStatus.CREATED);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseResponse> updateCourseById(@RequestBody CourseRequest courseRequest, @PathVariable Long id) {
        return new ResponseEntity<>(courseService.updateCourseById(courseRequest, id), HttpStatus.OK);
    }

    @PutMapping("/courses/{id_course}/teachers/{id_teacher}")
    public ResponseEntity<CourseResponse> assignTeacherToSubject(@PathVariable Long id_course, @PathVariable Long id_teacher) {
        return new ResponseEntity<>(courseService.assignTeacherToSubject(id_course, id_teacher), HttpStatus.OK);
    }

    @PutMapping("/courses/{id_course}/students/{id_student}")
    public ResponseEntity<CourseResponse> enrolledStudentToSubject(@PathVariable Long id_course, @PathVariable Long id_student) {
        return new ResponseEntity<>(courseService.enrolledStudent(id_course, id_student), HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
    }
}
