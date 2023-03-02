package com.imjcm.back_attend_system.response;

import com.imjcm.back_attend_system.enums.CourseModality;
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
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate createdAt;
    private TeacherResponse teacher;
    private CourseModality courseModality;
    private List<StudentResponse> students = new ArrayList<>();
}
