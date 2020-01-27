package com.ensaf.elearning.presentation.controllers;

import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Section;

import com.ensaf.elearning.services.CoursService;
import com.ensaf.elearning.services.SectionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/courses")
public class CourseController {

    @Autowired
    private CoursService coursService;
    @Autowired
    private SectionsService sectionsService;

    private Logger logger = LoggerFactory.getLogger(CourseController.class);

    @RequestMapping(value = "/index")
    public String Index(Model model){
        List<Course> crs=coursService.getAllCourses();
        model.addAttribute("Courses",crs);
        return "Courses";
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String Create(Model model){
        model.addAttribute("course",new Course());
        return "CreateCourse";
    }

    @RequestMapping(value = "/AddCourse",method = RequestMethod.POST)
    public String Add(Course course){
        coursService.addCourse(course);
        return "redirect:/courses/index";
    }
    @RequestMapping(value = "/coursDetails",method = RequestMethod.GET)
    public ModelAndView coursDetails(@RequestParam int id){
        String viewName = "courseDetails";

        ModelAndView modelAndView ;
        Course c =  coursService.getCoursebyId(id);
        List<Section> sections =sectionsService.getCoursSections(c);
        logger.info("found {} sections of courseId {}",sections.size(),c.getId());
        if(c!=null){
            HashMap<String,Object> model = new HashMap<>();
            model.put("cours",c);
            model.put("sections",sections);
            modelAndView = new ModelAndView(viewName,model);
        }
        else{
            modelAndView = new ModelAndView("errorpage");
        }
        return modelAndView;
    }

}
