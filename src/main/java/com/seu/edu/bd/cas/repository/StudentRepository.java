package com.seu.edu.bd.cas.repository;

import com.seu.edu.bd.cas.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student,Long> {
}
