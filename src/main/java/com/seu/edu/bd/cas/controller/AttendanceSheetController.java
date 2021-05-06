package com.seu.edu.bd.cas.controller;

import com.seu.edu.bd.cas.anotation.APiController;
import com.seu.edu.bd.cas.service.AttendanceSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@APiController
@RequestMapping("api/v1/attendance-sheet")
@RequiredArgsConstructor
public class AttendanceSheetController {

    private  final AttendanceSheetService attendanceSheetService;

    @GetMapping("/{sectionId}")
    public ResponseEntity<Object> getAttendanceSheetBySection(@PathVariable String sectionId){
      return ResponseEntity.ok().body(attendanceSheetService.getAttendanceBySection(sectionId));
    }
}
