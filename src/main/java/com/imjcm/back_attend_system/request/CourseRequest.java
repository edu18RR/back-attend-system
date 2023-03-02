package com.imjcm.back_attend_system.request;

import com.imjcm.back_attend_system.enums.CourseModality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequest {
    private String title;
    private String description;
    private CourseModality courseModality;
}
