package com.seu.edu.bd.cas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Semester {
    @Id
    private int  id;
    private String session;
    private LocalDate startDate;
    private LocalDate endDate;
    private String year;
}
