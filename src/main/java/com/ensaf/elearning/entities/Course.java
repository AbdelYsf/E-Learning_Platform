
package com.ensaf.elearning.entities;

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
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String prerequisites;
    private String level;
    private String estimatedTime;

   @ManyToOne
    @JoinColumn(name="categoy_id")
    private Category category;

   @ManyToOne
   @JoinColumn(name = "inctructor_id")
   private Instructor instructor;

   @ManyToMany
   private Collection<Student>  students;

   @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.EAGER)

   private Collection<Section> sections;





}
