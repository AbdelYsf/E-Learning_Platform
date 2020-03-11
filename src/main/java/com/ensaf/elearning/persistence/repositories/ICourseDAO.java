package com.ensaf.elearning.persistence.repositories;

import com.ensaf.elearning.persistence.entities.Category;
import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Instructor;
import com.ensaf.elearning.persistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICourseDAO extends JpaRepository<Course,Integer> {


    List<Course> findCourseByInstructor(Instructor instructor);
    List<Course> findCourseByStudents(Student s);
    List<Course> findCourseById(int id);
    List<Course> findCourseByCategory(Category category);

    @Query("SELECT c FROM Course c ")
    List<Course> findCourseByApproved();


}
