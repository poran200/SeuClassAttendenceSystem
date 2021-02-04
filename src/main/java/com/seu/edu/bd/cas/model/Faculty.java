package com.seu.edu.bd.cas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Faculty {
    @Id
    private String initial;
    private String name;
    @JsonIgnore
    private String password;
    private String role;
    @OneToMany(mappedBy = "faculty")
    @JsonIgnoreProperties(value = {"faculty"})
    private Set<Section> sections;
}
