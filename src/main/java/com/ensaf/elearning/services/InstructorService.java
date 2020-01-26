package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.Instructor;
import com.ensaf.elearning.persistence.repositories.IPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {
    @Autowired
    private IPersonDAO personDAO;

    public  void addInstrucotr(Instructor instructor){
        personDAO.save(instructor);
    }
}
