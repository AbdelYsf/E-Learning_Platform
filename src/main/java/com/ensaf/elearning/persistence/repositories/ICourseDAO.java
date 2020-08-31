package com.ensaf.elearning.persistence.repositories;

import com.ensaf.elearning.persistence.entities.Category;
import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Instructor;
import com.ensaf.elearning.persistence.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICourseDAO extends JpaRepository<Course,Integer> {


    List<Course> findCourseByInstructor(Instructor instructor);
    List<Course> findCourseByStudents(Student s);
    List<Course> findCourseById(int id);
    List<Course> findCourseByCategory(Category category);

    @Query(value = "SELECT c FROM Course c ")
    Page<Course> findCourse(PageRequest pageRequest);
    @Query(value = "SELECT c FROM Course c ")
    List<Course> findCourse();
    //@Query("SELECT c FROM Course c where c.approved =0")
    //List<Course> findCourseByApproved();

}
