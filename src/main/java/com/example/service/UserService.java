package com.example.service;

import com.example.model.User;
import com.example.repo.UserRepo;
import org.springframework.stereotype.Service;
import com.example.exp.ResourceNotFoundException;

import java.util.List;


@Service
public class UserService {

    private final UserRepo userRepository;

    public UserService(UserRepo userRepo) {
        this.userRepository = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User login(String emailId, String password) throws ResourceNotFoundException {
        return userRepository.findByEmailIdAndPassword(emailId, password)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid credentials"));
    }

	
}
