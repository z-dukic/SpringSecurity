package com.zoki.SpringSecurity.config;

import com.zoki.SpringSecurity.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserService userService;


    //Removes login on start of the application
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        //Allow all that goes through here
        //http.authorizeRequests().anyRequest().permitAll();

        //403 error
        http.authorizeRequests().anyRequest().authenticated();

        //you have to login
        http.formLogin();

        //basic auth
        http.httpBasic();
    }

    //Create username for you to login
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //In-memory auth
        auth.inMemoryAuthentication().withUser("zoki").password(passwordEncoder().encode("zoki"))
            .authorities("USER", "ADMIN");

        //Database auth
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    //If you don't declare this it won't run bcs it has to be encoded
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


