package com.zoki.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


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
        auth.inMemoryAuthentication().withUser("zoki").password(passwordEncoder().encode("zoki"))
            .authorities("USER", "ADMIN");
    }

    //If you don't declare this it won't run bcs it has to be encoded
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


