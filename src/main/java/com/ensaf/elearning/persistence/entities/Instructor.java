package com.ensaf.elearning.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Instructor  extends Person{


    public String speciality;
    public String bio;

    @OneToMany(mappedBy = "instructor",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Collection<Course> courses;
}
