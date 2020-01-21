package com.ensaf.elearning.persistence.repositories;

import com.ensaf.elearning.persistence.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileDAO extends JpaRepository<File,Long> {
}
