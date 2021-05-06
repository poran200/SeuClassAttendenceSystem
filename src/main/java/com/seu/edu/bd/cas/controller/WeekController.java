package com.seu.edu.bd.cas.controller;

import com.seu.edu.bd.cas.model.Semester;
import com.seu.edu.bd.cas.repository.SemesterRepository;
import com.seu.edu.bd.cas.util.DateUtilWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/weeks")
@RequiredArgsConstructor
public class WeekController {
    private final SemesterRepository semesterRepository;
    @GetMapping("")
    public ResponseEntity<Object> getWeeks(){
        Optional<Semester> semester = semesterRepository.findById(1);
        DateUtilWeek dateUtilWeek = new DateUtilWeek(semester.get());
        return ResponseEntity.status(HttpStatus.OK).body(dateUtilWeek.getWeeks());
    }
}
