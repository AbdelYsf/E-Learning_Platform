package com.ensaf.elearning.persistence.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data  @AllArgsConstructor @NoArgsConstructor @ToString @Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private String username;
    private int age;
    private String city;
    private String country;
    private String gender;

    @Temporal(TemporalType.DATE)
    private Date deteofinscreption;







}
