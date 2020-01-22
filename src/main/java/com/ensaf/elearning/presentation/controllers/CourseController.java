package com.ensaf.elearning.presentation.controllers;

import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.repositories.ICourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/Course")
public class CourseController {
    @Autowired
    private ICourseDAO courseDAO;
    @RequestMapping(value = "/Index")
    public String Index(Model model){
        List<Course> crs=courseDAO.findAll();
        model.addAttribute("Courses",crs);
        return "Courses";
    }
    @RequestMapping(value = "/Create",method = RequestMethod.GET)
    public String Create(Model model){
        model.addAttribute("course",new Course());
        return "CreateCourse";
    }
    @RequestMapping(value = "/AddCourse",method = RequestMethod.POST)
    public String Add(Course course){
        courseDAO.save(course);
        return "redirect:/Course/Index";
    }
}
