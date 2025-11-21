package com.epam.mongo_practice.service;

import com.epam.mongo_practice.entity.User;
import com.epam.mongo_practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUserById(String id)
    {
        return userRepository.findById(id).get();
    }

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }
}
