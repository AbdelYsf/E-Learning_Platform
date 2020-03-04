package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.Category;
import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Instructor;
import com.ensaf.elearning.persistence.entities.Person;
import com.ensaf.elearning.persistence.repositories.ICategoryDAO;
import com.ensaf.elearning.persistence.repositories.ICourseDAO;
import com.ensaf.elearning.persistence.repositories.IPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoursService {
    @Autowired
    private ICourseDAO courseDAO;
    @Autowired
    private ICategoryDAO categoryDAO;
    @Autowired
    private IPersonDAO personDAO;


    @Value("${dir.images}")
    private String imageDir;

    public List<Course> getCoursesOfPrincipal(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Person instructor =personDAO.findPersonByUsername(authentication.getName());

            return courseDAO.findCourseByInstructor((Instructor) instructor);

        }

        return  new ArrayList<>();
    }
    public List<Course> getActiveCourses(){


        return courseDAO.findCourseByApproved();
    }
    public void AddCourse(Course course, MultipartFile file){
        if(!(file.isEmpty())){course.imagePath=file.getOriginalFilename();}

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Person instructor =personDAO.findPersonByUsername(authentication.getName());
            course.setInstructor((Instructor) instructor);
            courseDAO.save(course);

        }
        if(!(file.isEmpty())){

            try {
                file.transferTo(new File(imageDir+course.id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Course getCoursebyId(int id){
        Optional<Course> course= courseDAO.findById(id);
        if(course.isPresent()){
            return course.get();
        }else{
            return null;
        }
    }
    public List<Category> getCategories(){
        return categoryDAO.findAll();
    }
    public void AddCategorie(Category category){
        categoryDAO.save(category);
    }
}
