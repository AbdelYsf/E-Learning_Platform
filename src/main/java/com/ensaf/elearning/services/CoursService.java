package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.Category;
import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.repositories.ICategoryDAO;
import com.ensaf.elearning.persistence.repositories.ICourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CoursService {
    @Autowired
    private ICourseDAO courseDAO;
    @Autowired
    private ICategoryDAO categoryDAO;
    @Value("${dir.images}")
    private String imageDir;
    public List<Course> getCourses(){
        List<Course> crs=courseDAO.findAll();
        return  crs;
    }
    public void AddCourse(Course course, MultipartFile file){
        if(!(file.isEmpty())){course.imagePath=file.getOriginalFilename();}
        courseDAO.save(course);
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
}
