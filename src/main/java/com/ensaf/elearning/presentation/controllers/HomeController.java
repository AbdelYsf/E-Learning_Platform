package com.ensaf.elearning.presentation.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;

@Controller
public class HomeController{

    @GetMapping("/login")
    public ModelAndView login(){
            String viewName= "login";
            return  new ModelAndView(viewName,new HashMap<>());
    }



}
