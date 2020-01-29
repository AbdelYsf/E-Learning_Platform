
package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Part;
import com.ensaf.elearning.persistence.entities.Section;
import com.ensaf.elearning.persistence.repositories.IPartDAO;
import com.ensaf.elearning.persistence.repositories.ISectionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionsService {

    @Autowired
    private ISectionDAO sectionDAO;
    @Autowired
    private IPartDAO partDAO;

    public List<Section> getCoursSections(Course course){
        return sectionDAO.findAllByCourse(course);
    }

    public void addSectionForCourse(int courseId, Section section){
        Course course = new Course();
        course.setId(courseId);
        section.setId(0l);
        section.setCourse(course);
        sectionDAO.save(section);
    }

    public void addPartSection(Part part, Long sectionId) {
        Section section = new Section();
        section.setId(sectionId);
        part.setSection(section);
        partDAO.save(part);

    }
}
