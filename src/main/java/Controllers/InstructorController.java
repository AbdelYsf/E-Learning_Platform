package Controllers;

import com.ensaf.elearning.persistence.repositories.InstructorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/Instructor")
public class InstructorController {
    @Autowired
    private InstructorDAO instructorDAO;
    @RequestMapping(value = "/Index")
    public String Index(){
        return "Instructors";
    }
}
