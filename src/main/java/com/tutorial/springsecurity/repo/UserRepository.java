package com.tutorial.springsecurity.repo;

import com.tutorial.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    public User findByUsername(String userName);
}
