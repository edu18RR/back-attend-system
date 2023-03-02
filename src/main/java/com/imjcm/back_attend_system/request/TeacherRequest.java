package com.imjcm.back_attend_system.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherRequest {
    private String name;
    private String surname;
    private String email;
    private String codeTeacher;
}
