package com.ensaf.elearning.presentation.controllers;

import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value ="/admin")
public class AdminController {
    @Autowired
    private CoursService coursService;

    @RequestMapping(value = "courses-to-approve",method = RequestMethod.GET)
    public String listAllCourses(Model model){
       List<Course> notApprovedCourses = coursService.getNotApprovedCourses();
       model.addAttribute("courses",notApprovedCourses);
        return "admin.html";
    }
    @RequestMapping(value = "accept",method = RequestMethod.GET)
    public String acceptCourse(Model model,int id){
        coursService.acceptCourse(id);
        List<Course> notApprovedCourses = coursService.getNotApprovedCourses();
        model.addAttribute("courses",notApprovedCourses);
        return "redirect:/admin/courses-to-approve";
    }



}
