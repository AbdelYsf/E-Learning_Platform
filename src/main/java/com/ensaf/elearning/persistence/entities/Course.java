
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
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String title;
    public String description;
    public String prerequisites;
    public String level;
    public String estimatedTime;
    public  boolean isApproved;
    public String imagePath;
    public String Price;
   @ManyToOne
    @JoinColumn(name="categoy_id")
    public Category category;

   @ManyToOne
   @JoinColumn(name = "inctructor_id")
   private Instructor instructor;

   @ManyToMany
   private Collection<Student>  students;

   @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.EAGER)

   private Collection<Section> sections;





}
