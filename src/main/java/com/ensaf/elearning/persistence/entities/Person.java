package com.ensaf.elearning.persistence.entities;


import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data  @AllArgsConstructor @NoArgsConstructor @ToString @Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  class Person implements Serializable {

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
    public String password;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Role> roles;

    @Temporal(TemporalType.DATE)
    private Date deteofinscreption;



}
