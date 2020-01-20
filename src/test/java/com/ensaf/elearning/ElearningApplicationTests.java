package com.ensaf.elearning;

import com.ensaf.elearning.entities.Category;
import com.ensaf.elearning.entities.Course;
import com.ensaf.elearning.entities.Student;
import com.ensaf.elearning.repositories.IPersonDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ElearningApplicationTests {

    @Autowired
    IPersonDAO personDAO;
    @Test
    void contextLoads() {
    }

    @Test
    void testStudent() {
        Student s = new Student();
        s.setFirstname("abdel");
        s.setEmail("abdel.ysf@gmail");
        s.setLastname("ysf");
        s.setDeteofinscreption(new Date());
        personDAO.save(s);



        Course c  = new Course();
        c.setDescription("bla bla");
        c.setEstimatedTime("2 weeks");
        c.setLevel("hard");
        Course c2  = new Course();
        c2.setDescription("bla bla");
        c2.setEstimatedTime("2 weeks");
        c2.setLevel("hard");
        List<Course> courseList = new ArrayList();
        courseList.add(c);
        courseList.add(c2);
        s.setTakencourses(courseList);
        personDAO.save(s);


    }

}
