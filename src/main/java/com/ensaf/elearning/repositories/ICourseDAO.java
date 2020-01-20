package com.ensaf.elearning.repositories;

import com.ensaf.elearning.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseDAO extends JpaRepository<Course,Integer> {
}
