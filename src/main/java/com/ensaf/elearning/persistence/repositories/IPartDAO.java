package com.ensaf.elearning.persistence.repositories;

import com.ensaf.elearning.persistence.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPartDAO extends JpaRepository<Part,Long> {
}
