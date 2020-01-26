package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.Student;
import com.ensaf.elearning.persistence.repositories.IPersonDAO;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private IPersonDAO personDAO;

    public void addStudent(Student student){

        personDAO.save(student);
    }
}
