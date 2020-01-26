package com.ensaf.elearning.presentation.controllers;

import com.ensaf.elearning.persistence.entities.Instructor;
import com.ensaf.elearning.persistence.repositories.InstructorDAO;
import com.ensaf.elearning.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/Instructor")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @RequestMapping(value = "/Index")
    public String Index(){

        return "instructors";
    }

    @RequestMapping(value = "/Register",method = RequestMethod.GET)
    public String Create(Model model){
        model.addAttribute("instructor",new Instructor());
        return "InstructorRegister";
    }

    @RequestMapping(value = "/SaveInstructor",method = RequestMethod.POST)
    public String Save(Instructor ins){
        instructorService.addInstrucotr(ins);
        return "redirect:/Instructor/Register";
    }
}
