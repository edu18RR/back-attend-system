package com.imjcm.back_attend_system.repository;

import com.imjcm.back_attend_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
