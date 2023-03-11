package com.imjcm.back_attend_system.controller;

import com.imjcm.back_attend_system.response.AttendanceResponse;
import com.imjcm.back_attend_system.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendances")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getAttendanceById(@PathVariable Long id) {
        return new ResponseEntity<>(attendanceService.getAttendanceById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getAllAttendances() {
        return new ResponseEntity<>(attendanceService.getAllAttendances(), HttpStatus.OK);
    }
}
