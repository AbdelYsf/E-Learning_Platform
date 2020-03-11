package com.ensaf.elearning.presentation.controllers;

import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Instructor;
import com.ensaf.elearning.persistence.entities.Section;
import com.ensaf.elearning.persistence.entities.Student;
import com.ensaf.elearning.persistence.repositories.IPersonDAO;
import com.ensaf.elearning.persistence.repositories.IStudentDAO;
import com.ensaf.elearning.services.CoursService;
import com.ensaf.elearning.services.SectionsService;
import com.ensaf.elearning.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CoursService coursService;

    @Autowired
    private SectionsService sectionsService;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String Create(Model model){
        model.addAttribute("student",new Student());
        return "StudentRegister";
    }

    @RequestMapping(value = "/saveStudent",method = RequestMethod.POST)
    public String Save(Student Std){
        studentService.addStudent(Std);
        return "redirect:/student/register";
    }

    @RequestMapping(value = "/achter",method = RequestMethod.GET)
    public String achter(int courseId){

           coursService.acheterCourse(courseId);
        return "redirect:/student/student-courses";
    }
    @RequestMapping(value = "/student-courses",method = RequestMethod.GET)
    public String myCourses(Model model){
        List<Course> crs=coursService.getStudentCourses();
        model.addAttribute("Courses",crs);
        return "studentcourses";
    }

    @RequestMapping(value = "/studentCoursDetails",method = RequestMethod.GET)
    public ModelAndView studentCoursDetails(@RequestParam int courseId){
        String viewName = "courseDetailsStudent";
        ModelAndView modelAndView ;
        Course c =  coursService.getCoursebyId(courseId);
        List<Section> sections =sectionsService.getCoursSections(c);
        if(c!=null){
            HashMap<String,Object> model = new HashMap<>();
            model.put("cours",c);
            model.put("Sections",sections);
            modelAndView = new ModelAndView(viewName,model);
        }
        else{
            modelAndView = new ModelAndView("errorpage");
        }
        return modelAndView;
    }

}
