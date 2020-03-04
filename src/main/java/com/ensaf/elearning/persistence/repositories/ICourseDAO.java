package com.ensaf.elearning.persistence.repositories;

import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICourseDAO extends JpaRepository<Course,Integer> {


    List<Course> findCourseByInstructor(Instructor instructor);

    @Query("SELECT c FROM Course c ")
    List<Course> findCourseByApproved();


}
