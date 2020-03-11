package com.ensaf.elearning.persistence.repositories;

import com.ensaf.elearning.persistence.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryDAO extends JpaRepository<Category,Integer> {

    Category findCategoryByCategoryName(String s);
}
