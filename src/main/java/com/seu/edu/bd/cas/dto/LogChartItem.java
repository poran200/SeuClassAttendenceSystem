package com.seu.edu.bd.cas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogChartItem {
    private String courseCode;
    private int totalScheduled;
    private int totalLogged;
}
