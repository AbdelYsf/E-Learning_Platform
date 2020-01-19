package com.ensaf.elearning.entities;

import lombok.*;

import javax.persistence.Entity;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Admin extends Person {


    private boolean issuperadmin;

}
