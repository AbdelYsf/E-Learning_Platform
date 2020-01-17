package com.ensaf.elearning.entities;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Section  implements Serializable {
    private Long id;
    private String sectionName;
    private String description;

}
