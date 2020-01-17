package com.ensaf.elearning.entities;


import lombok.*;

@Data  @AllArgsConstructor @NoArgsConstructor @ToString @Getter @Setter
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userName;
    private int age;
    private String city;
    private String country;
    private String gender;







}
