package com.ensaf.elearning.presentation.controllers;

import com.ensaf.elearning.persistence.entities.Category;
import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Part;
import com.ensaf.elearning.persistence.entities.Section;
import com.ensaf.elearning.persistence.repositories.ICategoryDAO;
import com.ensaf.elearning.persistence.repositories.InstructorDAO;
import com.ensaf.elearning.services.CoursService;
import com.ensaf.elearning.services.PartService;
import com.ensaf.elearning.services.SectionsService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value ="/courses")
public class CourseController {

    @Autowired
    private CoursService coursService;
    @Autowired
    private CoursService CoursService;
    @Autowired
    private SectionsService sectionsService;
    @Autowired
    private PartService partService;
    @Autowired
    private ICategoryDAO iCategoryDAO;
    @Autowired
    private InstructorDAO instructorDAO;
    @Value("${dir.images}")
    private String imageDir;
    @Value("${dir.videos}")
    private String videoDir;
    private Logger logger = LoggerFactory.getLogger(CourseController.class);




    @RequestMapping(value = "/addCategorie",method = RequestMethod.POST)
    public String AddCategorie(Category category){
        CoursService.AddCategorie(category);
        return "redirect:/courses/create";
    }
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String Create(Model model){
        model.addAttribute("course",new Course());
        model.addAttribute("categories",CoursService.getCategories());
        model.addAttribute("category",new Category());
        return "CreateCourse";
    }


    //getCategories

    @RequestMapping(value = "/AddCourse",method = RequestMethod.POST)
    public String Add(Course course, @RequestParam(name = "picture")MultipartFile file){
        CoursService.AddCourse(course,file);
        return "redirect:/courses/home";
    }

    @RequestMapping(value = "/getPhoto",produces={MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public byte[] getPhoto(int id) throws Exception{
        File f=new File(imageDir+id);
        return IOUtils.toByteArray(new FileInputStream(f));

    }
    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(name = "page",defaultValue = "0") int page,
                       @RequestParam(name = "size",defaultValue = "4")  int size ){
        Page<Course> crs=coursService.getActiveCourses(PageRequest.of(page,size));
        model.addAttribute("PageCourses",crs);
        model.addAttribute("pages",new int[crs.getTotalPages()]);
        model.addAttribute("currentPage",page);
        List<Category> categories = iCategoryDAO.findAll();
        model.addAttribute("categorie",categories);
        model.addAttribute("profs",instructorDAO.findAll());
        return "allcourses";
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
    public ModelAndView addSection(Section section, @RequestParam(name = "id") Integer courseId){

        //CoursService.addSectionForCourse(section,courseId);
        sectionsService.addSectionForCourse(courseId,section);
        return new ModelAndView("redirect:/courses/coursDetails?id="+courseId);
    }

    @RequestMapping(value = "/addpart",method = RequestMethod.POST)
    public ModelAndView addPart(Part part ,Long sectionid, int courseid, @RequestParam(name = "video")MultipartFile file){
        sectionsService.addPartSection(part , sectionid);
        partService.AddVideo(part,file);
        return new ModelAndView("redirect:/courses/coursDetails?id="+courseid);
    }

    @RequestMapping(value = "/getPhoto2",produces={MediaType.APPLICATION_STREAM_JSON_VALUE})
    @ResponseBody
    public byte[] getPhoto2(int id) throws Exception{
        File f=new File(videoDir+id);
        return IOUtils.toByteArray(new FileInputStream(f));

    }


    @RequestMapping(value = "/getFile",produces={MediaType.APPLICATION_PDF_VALUE})
    @ResponseBody
    public byte[] getFile(int id) throws Exception{
        File f=new File(videoDir+id);
        return IOUtils.toByteArray(new FileInputStream(f));

    }


    @RequestMapping(value = "/section",method = RequestMethod.GET)
    public ModelAndView addPart(Long sectionid,Integer courseid){

        HashMap<String,Object> model = new HashMap<>();
        model.put("newPart", new Part());
        model.put("sectionid",sectionid);
        model.put("courseid",courseid);
        return new ModelAndView("addpart",model);
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search(String type,String keyword ,Model model,
                         @RequestParam(name = "page",defaultValue = "0") int page,
                         @RequestParam(name = "size",defaultValue = "1")  int size){

        List<Course> pageCourses=null;
        if(type.equals("category")){
             pageCourses = coursService.getCoursesByCategory(keyword );
            model.addAttribute("PageCourses",new PageImpl<>(pageCourses));
        }if(type.equals("keyword")){
            pageCourses= coursService.getCoursesByKeyWord(keyword);
            model.addAttribute("PageCourses",new PageImpl<>(pageCourses));
        }  if(type.equals("prof")){
            pageCourses = coursService.getCoursesByInstructor(keyword);
            model.addAttribute("PageCourses",new PageImpl<>(pageCourses));
        }

        model.addAttribute("profs",instructorDAO.findAll());
        List<Category> categories = iCategoryDAO.findAll();
        model.addAttribute("pages",new int[1]);
        model.addAttribute("currentPage",page);
        model.addAttribute("categorie",categories);
        return "allcourses";
    }


}
