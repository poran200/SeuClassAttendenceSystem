package com.seu.edu.bd.cas.controller;

import com.seu.edu.bd.cas.anotation.APiController;
import com.seu.edu.bd.cas.anotation.CurrentUser;
import com.seu.edu.bd.cas.config.CUserDetails;
import com.seu.edu.bd.cas.dto.ClassLogFillerReq;
import com.seu.edu.bd.cas.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@APiController
@RequestMapping("api/v1/dashboard")
@RequiredArgsConstructor
public class DashboradController {
    private final DashBoardService dashBoardService;
    @GetMapping("")
    public ResponseEntity<Object> getClassLogSummary(@CurrentUser CUserDetails userDetails){
          return ResponseEntity.ok().body(dashBoardService.getClassLogSummary(userDetails.getUsername()));
    }
    @PostMapping("/weekSummary")
    public ResponseEntity<Object> getClassLogWeekSummary(@CurrentUser CUserDetails userDetails, @RequestBody ClassLogFillerReq req){
        System.out.println(req.toString());
         return ResponseEntity.ok().body(dashBoardService.getClassLogSummaryForDateOrWeek(userDetails.getUsername(),req.getStart(),req.getEnd()));
    }

}
