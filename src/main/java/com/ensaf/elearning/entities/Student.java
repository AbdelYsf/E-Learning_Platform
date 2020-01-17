package com.ensaf.elearning.entities;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Student  implements Serializable {
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
    private String cne;
    private String major;
    private String lastObtainedDegree;





}
