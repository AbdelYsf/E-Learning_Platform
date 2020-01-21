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
    public Long id;
    public String firstname;
    public String lastname;
    public String email;
    public String phonenumber;
    public String username;
    public int age;
    public String city;
    public String country;
    public String gender;

    @Temporal(TemporalType.DATE)
    private Date deteofinscreption;







}
