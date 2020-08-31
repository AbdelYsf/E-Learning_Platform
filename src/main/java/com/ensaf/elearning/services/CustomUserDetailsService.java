package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.Person;

import com.ensaf.elearning.persistence.repositories.IPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    IPersonDAO personDAO;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Person p =personDAO.findPersonByUsername(s);
        if(p==null){
            throw new UsernameNotFoundException("No user present with username: "+s);
        }
            return new MyUserPrincipal(p);
    }
}
