package com.seu.edu.bd.cas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JsonIgnoreProperties(value = {"registerSections"})
    private Student student;
    private boolean attend;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classLog_Id")
    @JsonIgnore
    private ClassLog classLog;

    public Attendance(Student student, boolean attend) {
        this.student = student;
        this.attend = attend;
    }

    public Attendance(Student student, boolean attend, ClassLog classLog) {
        this.student = student;
        this.attend = attend;
        this.classLog = classLog;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", student=" + student +
                ", attend=" + attend +
                '}';
    }
}
