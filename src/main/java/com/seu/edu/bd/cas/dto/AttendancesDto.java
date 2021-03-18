package com.seu.edu.bd.cas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seu.edu.bd.cas.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendancesDto {
    @JsonIgnoreProperties({"registerSections"})
    private Student student ;
    private double attendances;
}
