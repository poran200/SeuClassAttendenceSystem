package com.seu.edu.bd.cas.repository;

import com.seu.edu.bd.cas.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration,Integer> {
}
