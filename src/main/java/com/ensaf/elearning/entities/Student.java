package com.ensaf.elearning.entities;

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
public class Student  extends Person {

    private String cne;
    private String major;
    private String lastobtaineddegree;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_taken_courses",
            joinColumns = @JoinColumn(name = "studnt_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"))
    private Collection<Course> takencourses;





}
