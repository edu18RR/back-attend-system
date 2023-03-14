package com.imjcm.back_attend_system.repository;

import com.imjcm.back_attend_system.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
