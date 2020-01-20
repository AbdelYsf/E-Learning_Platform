package com.ensaf.elearning.repositories;

import com.ensaf.elearning.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPersonDAO extends JpaRepository<Person,Long> {
}
