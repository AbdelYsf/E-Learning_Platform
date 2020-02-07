package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.Course;
import com.ensaf.elearning.persistence.entities.Part;
import com.ensaf.elearning.persistence.repositories.IPartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class PartService {
    @Autowired
    private IPartDAO partDAO;
    @Value("${dir.videos}")
    private String imageDir;
    public void AddVideo(Part part, MultipartFile file){
        if(!(file.isEmpty())){part.path=file.getOriginalFilename();}
        partDAO.save(part);
        if(!(file.isEmpty())){
            try {
                file.transferTo(new File(imageDir+part.id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
