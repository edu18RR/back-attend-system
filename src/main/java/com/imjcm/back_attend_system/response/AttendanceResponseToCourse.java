package com.imjcm.back_attend_system.response;

import com.imjcm.back_attend_system.enums.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceResponseToCourse {
    private Long id;
    private AttendanceStatus status;
    private LocalDate createAt;
    private List<StudentResponse> students = new ArrayList<>();
}
