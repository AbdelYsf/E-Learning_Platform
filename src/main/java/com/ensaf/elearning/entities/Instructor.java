package com.ensaf.elearning.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Instructor  extends Person{


    private String speciality;
    private String bio;

    @OneToMany(mappedBy = "instructor",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Collection<Course> courses;
}
