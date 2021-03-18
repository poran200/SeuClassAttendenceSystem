package com.seu.edu.bd.cas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
    private Set<Registration> registerSections;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Registration... sections){
      this.name = name;
      if (registerSections == null){
          this.registerSections = new HashSet<>();
      }
      for (Registration registration : sections){
          registration.setStudent(this);
      }
      this.registerSections = Stream.of(sections).collect(Collectors.toSet());
    }
}
