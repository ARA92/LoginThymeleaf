package com.login.login.repository;


import com.login.login.entity.UserForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserForm,Integer> {
    UserForm findByUsernameAndPassword(String username, String password);
}
