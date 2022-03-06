package com.zoki.SpringSecurity.repository;

import com.zoki.SpringSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, Id> {

    User findByUserName(String userName);

}
