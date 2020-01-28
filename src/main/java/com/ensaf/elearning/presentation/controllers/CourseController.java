package com.ensaf.elearning.presentation.controllers;

import com.ensaf.elearning.persistence.entities.Category;
import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Section;
import com.ensaf.elearning.services.CoursService;
import com.ensaf.elearning.services.SectionsService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {

    @Autowired
    private CoursService CoursService;
    @Autowired
    private SectionsService sectionsService;
    @Value("${dir.images}")
    private String imageDir;

    private Logger logger = LoggerFactory.getLogger(CourseController.class);

    @RequestMapping(value = "/index")
    public String Index(Model model){
        List<Course> crs=CoursService.getCourses();
        model.addAttribute("Courses",crs);
        return "Courses";
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String Create(Model model){
        model.addAttribute("course",new Course());
        model.addAttribute("categories",CoursService.getCategories());
        return "CreateCourse";
    }
    //getCategories

    @RequestMapping(value = "/AddCourse",method = RequestMethod.POST)
    public String Add(Course course, @RequestParam(name = "picture")MultipartFile file){
        CoursService.AddCourse(course,file);
        return "redirect:/courses/index";
    }
    @RequestMapping(value = "/getPhoto",produces={MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public byte[] getPhoto(int id) throws Exception{
        File f=new File(imageDir+id);
        return IOUtils.toByteArray(new FileInputStream(f));

    }

    @RequestMapping(value = "/coursDetails",method = RequestMethod.GET)
    public ModelAndView coursDetails(@RequestParam int id){
        String viewName = "courseDetails";

        ModelAndView modelAndView ;
        Course c =  CoursService.getCoursebyId(id);
        List<Section> sections =sectionsService.getCoursSections(c);
        logger.info("found {} sections of courseId {}",sections.size(),c.id);
        if(c!=null){
            HashMap<String,Object> model = new HashMap<>();
            model.put("cours",c);
            model.put("Sections",sections);
            model.put("newsection",new Section());
            modelAndView = new ModelAndView(viewName,model);
        }
        else{
            modelAndView = new ModelAndView("errorpage");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/addsection",method = RequestMethod.POST)
    public ModelAndView addSection(Section section, @RequestParam(name = "id") int courseId){

        //CoursService.addSectionForCourse(section,courseId);
        sectionsService.addSectionForCourse(courseId,section);
        return new ModelAndView("redirect:/courses/coursDetails?id="+courseId);
    }

}
