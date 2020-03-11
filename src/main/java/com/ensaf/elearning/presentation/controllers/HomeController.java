package com.ensaf.elearning.presentation.controllers;

import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController{

    @GetMapping("/login")
    public ModelAndView login(){
            String viewName= "login";
            return  new ModelAndView(viewName,new HashMap<>());
    }



}
