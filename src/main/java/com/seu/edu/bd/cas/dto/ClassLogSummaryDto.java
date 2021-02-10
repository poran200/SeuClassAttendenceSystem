package com.seu.edu.bd.cas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
public class ClassLogSummaryDto {
    private String sectionId;
    private String courseTitle;
    private int scheduled;
    private int logged;
    private int durationToBeTaken;
    private int durationTaken;
    private double attendanceRate;

}
