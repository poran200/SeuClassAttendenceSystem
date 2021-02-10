package com.seu.edu.bd.cas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "initial")
public class Faculty {
    @Id
    private String initial;
    private String name;
    @JsonIgnore
    private String password;
    private String role;
    @OneToMany(mappedBy = "faculty" ,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Section> sections;
}
