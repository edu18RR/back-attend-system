package com.imjcm.back_attend_system.response;

import com.imjcm.back_attend_system.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String studentCode;
    private LocalDateTime createdAt;
}
