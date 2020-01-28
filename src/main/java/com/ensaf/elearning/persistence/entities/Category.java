package com.ensaf.elearning.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

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
    public Long id;
    public String  CategoryName;

    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Collection<Course> courses ;
}
