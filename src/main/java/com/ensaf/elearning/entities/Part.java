package com.ensaf.elearning.entities;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Part implements Serializable {
    private Long id;
    private String partName;
    private String description;
    private String path;

}
