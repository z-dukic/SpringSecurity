package com.zoki.SpringSecurity;

import com.zoki.SpringSecurity.entity.Authority;
import com.zoki.SpringSecurity.entity.User;
import com.zoki.SpringSecurity.repository.UserDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringSecurityApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
        System.out.println("Everything is running ok");
	}

    @PostConstruct
    protected void init(){

        List<Authority> authorityList= new ArrayList<>();

        authorityList.add(createAuthority("USER","User role"));
        authorityList.add(createAuthority("ADMIN","Admin role"));

        User user=new User();

        user.setUserName("marko");
        user.setFirstName("markan");
        user.setLastName("mariola");

        user.setPassword(passwordEncoder.encode("matija"));
        user.setEnabled(true);
        user.setAuthorities(authorityList);

        userDetailsRepository.save(user);

    }

    private Authority createAuthority(String roleCode,String roleDescription) {
        Authority authority=new Authority();
        authority.setRoleCode(roleCode);
        authority.setRoleDescription(roleDescription);
        return authority;
    }

//Radi, ali svaki put napravi usera, ako radi istog usera baci error
    //37:29


}
