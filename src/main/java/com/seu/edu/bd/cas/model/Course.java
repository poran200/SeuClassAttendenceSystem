package com.seu.edu.bd.cas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Course {
    @Id
    private String courseCode;
    private String title;
    private int credit;
}
