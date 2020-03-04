package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.Instructor;
import com.ensaf.elearning.persistence.entities.Role;
import com.ensaf.elearning.persistence.repositories.IPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorService {
    @Autowired
    private IPersonDAO personDAO;

    public  void addInstrucotr(Instructor instructor){
        Role role=new Role();
        role.setId(1l);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        instructor.setRoles(roles);
         instructor.setPassword( new BCryptPasswordEncoder().encode(instructor.getPassword()));
        personDAO.save(instructor);
    }
}
