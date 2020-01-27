package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Section;
import com.ensaf.elearning.persistence.repositories.ISectionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionsService {

    @Autowired
    private ISectionDAO sectionDAO;

    public List<Section> getCoursSections(Course course){
        return sectionDAO.findAllByCourse(course);
    }
}
