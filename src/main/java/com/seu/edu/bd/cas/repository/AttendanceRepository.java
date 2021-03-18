package com.seu.edu.bd.cas.repository;

import com.seu.edu.bd.cas.model.Attendance;
import com.seu.edu.bd.cas.model.ClassLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {

    long countAllByClassLogAndStudent_IdAndAttendTrue(ClassLog classLog, long student_id);
}
