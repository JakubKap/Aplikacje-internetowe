package com.airline.test.controller;

import com.airline.test.dao.User;
import com.airline.test.service.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/getUsers")
    public Iterable<User> getUsers(){
        return userDao.findAll();
    }
}
