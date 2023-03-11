package com.imjcm.back_attend_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String codeTeacher;
    private double salary;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime editedAt = LocalDateTime.now();
    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses = new ArrayList<>();
}
