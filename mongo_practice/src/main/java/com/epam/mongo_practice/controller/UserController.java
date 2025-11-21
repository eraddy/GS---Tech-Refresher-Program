package com.epam.mongo_practice.controller;

import com.epam.mongo_practice.entity.User;
import com.epam.mongo_practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User findUserById(@PathVariable  String id)
    {
        return userService.findUserById(id);
    }

    @PostMapping("")
    public User saveUser(@RequestBody User user)
    {
        return userService.saveUser(user);
    }
}