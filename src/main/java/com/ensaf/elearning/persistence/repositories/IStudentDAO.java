package com.ensaf.elearning.persistence.repositories;

import com.ensaf.elearning.persistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentDAO extends JpaRepository<Student,Long> {
}
