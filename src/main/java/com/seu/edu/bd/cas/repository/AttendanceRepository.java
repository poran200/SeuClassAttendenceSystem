package com.seu.edu.bd.cas.repository;

import com.seu.edu.bd.cas.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {
}
