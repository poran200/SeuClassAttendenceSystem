package com.seu.edu.bd.cas.repository;

import com.seu.edu.bd.cas.model.Faculty;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty,String> {
    Optional<Faculty> findByInitial(String s);
}
