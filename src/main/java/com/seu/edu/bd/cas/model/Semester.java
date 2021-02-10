package com.seu.edu.bd.cas.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
//@Data
@Getter
@Setter
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
