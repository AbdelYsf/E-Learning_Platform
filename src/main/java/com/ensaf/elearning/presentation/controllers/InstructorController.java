package com.ensaf.elearning.presentation.controllers;

import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Instructor;
import com.ensaf.elearning.persistence.repositories.InstructorDAO;
import com.ensaf.elearning.services.CoursService;
import com.ensaf.elearning.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/instructor")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @Autowired
    private  CoursService coursService;
    @RequestMapping(value = "/own-courses")
    public String allCourses(Model model){
        List<Course> crs=coursService.getCoursesOfPrincipal();
        model.addAttribute("Courses",crs);
        return "own-courses";
    }




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
    @RequestMapping(value = "/dashbord",method = RequestMethod.GET)
    public String dashbord(Instructor ins){
        return "instructor-dashbord";
    }


}
