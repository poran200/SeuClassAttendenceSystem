package com.seu.edu.bd.cas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DashBoardInfo {
    private int registerStudents;
    private int totalSections;
    private int classLogged;
    private int scheduled;
    private List<ClassLogSummaryDto> summary;
}
