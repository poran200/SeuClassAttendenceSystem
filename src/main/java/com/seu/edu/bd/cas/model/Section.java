package com.seu.edu.bd.cas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class Section implements Serializable {
    @Id
    private String sectionId;
    private int seatLimit;
    @ManyToOne
    private Semester semester;
    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties(value = {"section"})
    private Set<Registration> registerStudents;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JsonIgnoreProperties(value = "sections")
    private Faculty faculty;
    private String location;
    private int classPerWeek;

}
