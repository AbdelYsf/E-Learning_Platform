package com.ensaf.elearning.services;

import com.ensaf.elearning.persistence.entities.Person;
import com.ensaf.elearning.persistence.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;


public class MyUserPrincipal  implements UserDetails {

    private Person user;

    public MyUserPrincipal(Person user){

        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<String> roleName = new ArrayList<>();
        for(Role  r:user.roles){
            roleName.add(r.getRoleName());
        }

        String roles= StringUtils.collectionToCommaDelimitedString(roleName);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public String getPassword() {
        System.out.println(user.roles.size());
        for(Role  r:user.roles){
            System.out.println(r.getRoleName());
        }
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
