package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.repositories.ICourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursService {
    @Autowired
    private ICourseDAO courseDAO;

    public List<Course> getAllCourses(){
        return courseDAO.findAll();
    }
    public Course getCoursebyId(int id){
       Optional<Course> course= courseDAO.findById(id);
       if(course.isPresent()){
           return course.get();
       }else{
           return null;
       }
    }

    public void  addCourse(Course course){
        courseDAO.save(course);
    }

}
