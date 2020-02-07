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
public class Section  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String sectionName;
    public String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    public Course course;

    @OneToMany(mappedBy = "section")
    public Collection<Part> part;

}
