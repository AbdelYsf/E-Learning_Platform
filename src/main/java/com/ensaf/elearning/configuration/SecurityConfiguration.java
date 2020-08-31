package com.ensaf.elearning.configuration;

import com.ensaf.elearning.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    Securityhandler securityhandler;

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
    public void configure(WebSecurity web) throws Exception {
       // super.configure(web);
         web.ignoring()
                .antMatchers( "/static/**", "/css/**", "/js/**", "/img/**","signin","instructor/Register");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        http.sessionManagement().maximumSessions(1);
        http.authorizeRequests()
                .antMatchers("/instructor/onwn-courses").hasAuthority("instructor")
                .antMatchers("/instructor/dashbord").hasAuthority("instructor")
                .antMatchers("/courses/**").hasAnyAuthority("instructor","student","admin")
                .antMatchers("/admin").hasRole("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").successHandler(securityhandler)
                .defaultSuccessUrl("/courses/home")
                .and()
                .logout().permitAll().logoutSuccessUrl("/login")
                .and()
                .csrf().disable();



    }
}
