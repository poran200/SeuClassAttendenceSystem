package com.seu.edu.bd.cas.repository;

import com.seu.edu.bd.cas.model.ClassLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ClassLogRepository extends JpaRepository<ClassLog,Long> {
       List<ClassLog> findAllBySection_SectionIdAndConductAtGreaterThanEqualAndConductAtLessThanEqual(String section_sectionId, Date conductAt, Date conductAt2);
       List<ClassLog>findAllBySection_Faculty_Initial(String section_faculty_initial);
       Integer countClassLogBySection_SectionId(String sectionId);
       Integer countClassLogBySection_Faculty_Initial(String section_faculty_initial);
       List<ClassLog>findAllBySection_SectionId(String section_sectionId);
       List<ClassLog>findAllBySection_SectionIdAndConductAtBetween(String section_sectionId, Date conductAt, Date conductAt2);
}
