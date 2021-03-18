package com.seu.edu.bd.cas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor@Data
@EqualsAndHashCode(of = "id")
public class ClassLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int duration;
    private  int totalAttend;

    @Column(updatable = false)
    @Temporal(value = TemporalType.DATE)
    private  Date createdAt;

    @Temporal(value = TemporalType.DATE)
    private  Date updatedAt;
    @Temporal(value = TemporalType.DATE)
    private  Date conductAt;
    private  String status;
    @OneToMany( mappedBy = "classLog",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Attendance> attendances;
    @ManyToOne
    private Section section;

    public void addAttendance(Attendance attendance){
            if (this.attendances ==null){
                this.attendances = new HashSet<>();
            }
             this.attendances.add(attendance);
             attendance.setClassLog(this);
    }
    public void addAllAttendance(Set<Attendance> attendance){
        if (this.attendances ==null){
            this.attendances = new HashSet<>();
        }
        attendances.forEach(attendance1 -> attendance1.setClassLog(this));
        attendances.addAll(attendance);
    }
    @PrePersist
    public void setPreInsertData(){
        this.createdAt = new Date();
    }
    @PreUpdate
    public void setPostUpdateData(){
        this.updatedAt = new Date();
    }

}
