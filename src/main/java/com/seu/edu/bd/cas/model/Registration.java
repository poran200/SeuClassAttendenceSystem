package com.seu.edu.bd.cas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Registration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sutdent_id")
    @JsonIgnoreProperties(value = "registerSections")
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "section_id")
    @JsonIgnoreProperties(value = "registerStudents")
    private Section section;

    public Registration(Student student, Section section) {
        this.student = student;
        this.section = section;
    }

}
