package com.seu.edu.bd.cas.repository;

import com.seu.edu.bd.cas.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,String> {
}
