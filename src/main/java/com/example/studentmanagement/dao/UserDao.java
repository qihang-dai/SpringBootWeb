package com.example.studentmanagement.dao;

import com.example.studentmanagement.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,Long> {

    public User findUserByUsername(String username);

}
