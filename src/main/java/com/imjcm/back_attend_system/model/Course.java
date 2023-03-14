package com.imjcm.back_attend_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imjcm.back_attend_system.enums.CourseModality;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private CourseModality courseModality;
    private LocalDate createdAt = LocalDate.now();
    private LocalDate editedAt = LocalDate.now();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;
    @ManyToMany
    @JoinTable(
            name = "student_enrolled",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Attendance> attendances = new ArrayList<>();
}
