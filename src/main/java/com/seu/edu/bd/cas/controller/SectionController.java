package com.seu.edu.bd.cas.controller;

import com.seu.edu.bd.cas.anotation.APiController;
import com.seu.edu.bd.cas.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@APiController
@RequestMapping("/api/v1/section")
public class SectionController {
    @Autowired
    SectionRepository sectionRepository;

    @GetMapping("/{initial}")
    public ResponseEntity<Object> finByFaculty(@PathVariable String initial){
        return ResponseEntity.ok().body(sectionRepository.findAllByFacultyInitial(initial));
    }
    @GetMapping("/students/{section}")
    public ResponseEntity<Object> getSectionStudents(@PathVariable String section){
        return ResponseEntity.ok().body(sectionRepository.findById(section));
    }

}
