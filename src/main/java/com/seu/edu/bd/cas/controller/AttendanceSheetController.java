package com.seu.edu.bd.cas.controller;

import com.seu.edu.bd.cas.service.AttendanceSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/attendance-sheet")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AttendanceSheetController {

    private  final AttendanceSheetService attendanceSheetService;

    @GetMapping("/{sectionId}")
    public ResponseEntity<Object> getAttendanceSheetBySection(@PathVariable String sectionId){
      return ResponseEntity.ok().body(attendanceSheetService.getAttendanceBySection(sectionId));
    }
}
