package com.ensaf.elearning.configuration;

import com.ensaf.elearning.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("abdel")
//                .password("abdel")
//                .roles("user");
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordencoder());
    }

    @Bean
    public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/Instructor").hasRole("instructor")
                .antMatchers("/courses/**").hasAnyAuthority("instructor")
                .antMatchers("/admin").hasRole("admin")
                .antMatchers("/","static/css","static/js","static/img","/signin","Instructor/Register").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")

                .and()
                .logout().permitAll().logoutSuccessUrl("/login")
                .and()
                .csrf().disable();

    }
}
