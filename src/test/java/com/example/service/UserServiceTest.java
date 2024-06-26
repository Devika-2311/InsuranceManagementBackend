package com.example.service;

import com.example.model.User;
import com.example.repo.UserRepo;
import com.example.exp.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setUserId(1L);
        User user2 = new User();
        user2.setUserId(2L);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById_UserFound() throws ResourceNotFoundException {
        User user = new User();
        user.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);
        assertEquals(user, foundUser);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserById(1L);
        });

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUserId(1L);

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);
        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testLogin_UserFound() throws ResourceNotFoundException {
        User user = new User();
        user.setEmailId("test@example.com");
        user.setPassword("password");

        when(userRepository.findByEmailIdAndPassword("test@example.com", "password")).thenReturn(Optional.of(user));

        User loggedInUser = userService.login("test@example.com", "password");
        assertEquals(user, loggedInUser);
        verify(userRepository, times(1)).findByEmailIdAndPassword("test@example.com", "password");
    }

    @Test
    public void testLogin_UserNotFound() {
        when(userRepository.findByEmailIdAndPassword("test@example.com", "password")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.login("test@example.com", "password");
        });

        verify(userRepository, times(1)).findByEmailIdAndPassword("test@example.com", "password");
    }
}
