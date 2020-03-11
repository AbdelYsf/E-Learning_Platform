package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.Role;
import com.ensaf.elearning.persistence.entities.Student;
import com.ensaf.elearning.persistence.repositories.IPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private IPersonDAO personDAO;

    public void addStudent(Student student){


        Role role=new Role();
        role.setId(2l);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        student.setRoles(roles);
        student.setPassword( new BCryptPasswordEncoder().encode(student.getPassword()));
        personDAO.save(student);
    }
}
