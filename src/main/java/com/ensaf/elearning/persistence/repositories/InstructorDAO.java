package com.ensaf.elearning.persistence.repositories;

import com.ensaf.elearning.persistence.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorDAO extends JpaRepository<Instructor,Long> {
}
