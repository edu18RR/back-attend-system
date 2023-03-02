package com.imjcm.back_attend_system.repository;

import com.imjcm.back_attend_system.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
