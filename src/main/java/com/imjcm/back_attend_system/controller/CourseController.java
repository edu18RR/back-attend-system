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
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
        return new ResponseEntity<>(courseService.createCourse(courseRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourseById(@RequestBody CourseRequest courseRequest, @PathVariable Long id) {
        return new ResponseEntity<>(courseService.updateCourseById(courseRequest, id), HttpStatus.OK);
    }

    @PutMapping("/{id_course}/teachers/{id_teacher}")
    public ResponseEntity<CourseResponse> assignTeacherToSubject(@PathVariable Long id_course, @PathVariable Long id_teacher) {
        return new ResponseEntity<>(courseService.assignTeacherToSubject(id_course, id_teacher), HttpStatus.OK);
    }

    @PutMapping("/{id_course}/students/{id_student}")
    public ResponseEntity<CourseResponse> enrolledStudentToSubject(@PathVariable Long id_course, @PathVariable Long id_student) {
        return new ResponseEntity<>(courseService.enrolledStudent(id_course, id_student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
    }

    @PutMapping("/{id_course}/attendance")
    public ResponseEntity<CourseResponse> createAttendance(@PathVariable Long id_course) {
        return new ResponseEntity<>(courseService.createAttendance(id_course), HttpStatus.OK);
    }

    @PutMapping("/{id_course}/attendances/{id_attendance}/students/{id_student}")
    public ResponseEntity<CourseResponse> recordStudentToAttendance(@PathVariable Long id_course, @PathVariable Long id_attendance, @PathVariable Long id_student) {
        return new ResponseEntity<>(courseService.recordStudentToAttendance(id_course, id_attendance, id_student), HttpStatus.OK);
    }

}
