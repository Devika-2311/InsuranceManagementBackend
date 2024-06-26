package com.example.controller;

import com.example.exp.ResourceNotFoundException;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3003", allowCredentials = "true")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws ResourceNotFoundException {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam String emailId, @RequestParam String password) {
        try {
            User user = userService.login(emailId, password);
            return ResponseEntity.ok().body(user.getUserId());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

  
}
