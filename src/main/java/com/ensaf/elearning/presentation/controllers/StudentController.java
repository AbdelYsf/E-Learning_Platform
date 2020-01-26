package com.ensaf.elearning.presentation.controllers;

import com.ensaf.elearning.persistence.entities.Instructor;
import com.ensaf.elearning.persistence.entities.Student;
import com.ensaf.elearning.persistence.repositories.IPersonDAO;
import com.ensaf.elearning.persistence.repositories.IStudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/Student")
public class StudentController {
    @Autowired
    private IPersonDAO studentDAO;
    @RequestMapping(value = "/Register",method = RequestMethod.GET)
    public String Create(Model model){
        model.addAttribute("student",new Student());
        return "StudentRegister";
    }
    @RequestMapping(value = "/SaveStudent",method = RequestMethod.POST)
    public String Save(Student Std){
        studentDAO.save(Std);
        return "redirect:/Student/Register";
    }
}
