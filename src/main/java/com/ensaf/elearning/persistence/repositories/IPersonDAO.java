package com.ensaf.elearning.persistence.repositories;

import com.ensaf.elearning.persistence.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPersonDAO extends JpaRepository<Person,Long> {
}
