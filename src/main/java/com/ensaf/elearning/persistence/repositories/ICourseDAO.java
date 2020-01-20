package com.ensaf.elearning.persistence.repositories;

import com.ensaf.elearning.persistence.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseDAO extends JpaRepository<Course,Integer> {
}
