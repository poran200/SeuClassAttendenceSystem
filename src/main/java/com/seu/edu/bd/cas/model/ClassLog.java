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
@NoArgsConstructor@Data@EqualsAndHashCode
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
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Attendance> attendances;
    @ManyToOne
    private Section section;

    public void addAttendance(Attendance attendance){
            if (this.attendances ==null){
                this.attendances = new HashSet<>();
            }
            attendances.add(attendance);
    }
    public void addAllAttendance(Set<Attendance> attendance){
        if (this.attendances ==null){
            this.attendances = new HashSet<>();
        }
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
