package com.seu.edu.bd.cas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "sectionId")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    private int duration;

}
