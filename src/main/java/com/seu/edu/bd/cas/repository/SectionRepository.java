package com.seu.edu.bd.cas.repository;

import com.seu.edu.bd.cas.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section,String> {

    List<Section> findAllByFacultyInitial(String initial);
}
