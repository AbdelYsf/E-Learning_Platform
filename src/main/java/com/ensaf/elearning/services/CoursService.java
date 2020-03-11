package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.*;
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
import java.util.Collection;
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
    public List<Course> getCourses(){
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

    public void acheterCourse(int id){
        Optional<Course> c= courseDAO.findById(id);
        if(c.isPresent()){
            Course  course= c.get();
            Collection<Student> students =course.getStudents();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                Person student =personDAO.findPersonByUsername(authentication.getName());

                students.add((Student) student);
                course.setStudents(students);
                courseDAO.save(course);

            }
        }else{
            System.out.println("no course found!!!");
        }


    }

    public List<Course> getStudentCourses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Person student =personDAO.findPersonByUsername(authentication.getName());

            return courseDAO.findCourseByStudents((Student) student);

        }
        else{
            return new ArrayList<>();
        }
    }
    public List<Course> getCoursesByCategory(String categoryName){
        Category category = categoryDAO.findCategoryByCategoryName(categoryName);
        return courseDAO.findCourseByCategory(category);


    }

    public List<Course> getCoursesByKeyWord(String keyword){
        List<Course> all = courseDAO.findAll();
        List<Course> resultCourses= new ArrayList<>();
        for(Course c: all){
            if(c.getTitle().contains(keyword)){
                resultCourses.add(c);
            }

        }
        return resultCourses;
    }

    public List<Course> getCoursesByInstructor(String keyword) {
        Person personByUsername = personDAO.findPersonByUsername(keyword);
        return courseDAO.findCourseByInstructor((Instructor) personByUsername);

    }
}
