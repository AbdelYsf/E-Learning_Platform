package com.ensaf.elearning.persistence.repositories;

import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISectionDAO extends JpaRepository<Section,Long> {

    public List<Section> findAllByCourse(Course course);
}
