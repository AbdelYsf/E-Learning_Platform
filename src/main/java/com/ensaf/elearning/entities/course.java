
package com.ensaf.elearning.entities;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class course   implements Serializable {

    private Integer id;
    private String title;
    private String description;
    private String prerequisites;
    private String level;
    private String estimatedTime;




}
