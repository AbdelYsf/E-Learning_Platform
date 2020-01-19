package com.ensaf.elearning.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Category  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  CategoryName;

    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Collection<Course> courses ;
}
