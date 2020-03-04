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
public class Role {

    @Id
    private Long id;
    private String roleName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_taken_courses",
            joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    private Collection<Person> users;


}
