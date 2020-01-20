package com.ensaf.elearning;

import com.ensaf.elearning.persistence.entities.Category;
import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Student;
import com.ensaf.elearning.persistence.repositories.ICategoryDAO;
import com.ensaf.elearning.persistence.repositories.IPersonDAO;
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
    @Autowired
    ICategoryDAO categoryDAO;
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


        Category category = new Category();
        category.setCategoryName("aaa");


        Course c  = new Course();
        c.setDescription("bla bla");
        c.setEstimatedTime("2 weeks");
        c.setLevel("hard");
        c.setCategory(category);

        Course c2  = new Course();
        c2.setCategory(category);
        c2.setDescription("bla bla");
        c2.setEstimatedTime("2 weeks");
        c2.setLevel("hard");
        List<Course> courseList = new ArrayList();
        courseList.add(c);
        courseList.add(c2);
        category.setCourses(courseList);
        s.setTakencourses(courseList);
        categoryDAO.save(category);
        personDAO.save(s);


    }

}
