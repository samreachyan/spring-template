package com.sakcode.service;

import com.sakcode.entity.User;
import com.sakcode.repository.UserRepository;
import com.sakcode.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        User optionalUser = userRepository.save(user);
        return optionalUser;
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }
}
