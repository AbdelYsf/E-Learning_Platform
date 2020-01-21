package com.ensaf.elearning.persistence.repositories;

import com.ensaf.elearning.persistence.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISectionDAO extends JpaRepository<Section,Long> {
}
