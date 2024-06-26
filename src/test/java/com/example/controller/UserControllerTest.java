package com.example.controller;

import com.example.exp.ResourceNotFoundException;
import com.example.model.User;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setUserId(1L);
        User user2 = new User();
        user2.setUserId(2L);

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        ResponseEntity<List<User>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById_UserFound() throws ResourceNotFoundException {
        User user = new User();
        user.setUserId(1L);

        when(userService.getUserById(1L)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testGetUserById_UserNotFound() throws ResourceNotFoundException {
        when(userService.getUserById(1L)).thenThrow(new ResourceNotFoundException("User not found"));

        assertThrows(ResourceNotFoundException.class, () -> {
            userController.getUserById(1L);
        });

        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testLogin_Success() throws ResourceNotFoundException {
        User user = new User();
        user.setUserId(1L);
        user.setEmailId("test@example.com");
        user.setPassword("password");

        when(userService.login("test@example.com", "password")).thenReturn(user);

        ResponseEntity<?> response = userController.login("test@example.com", "password");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody());
        verify(userService, times(1)).login("test@example.com", "password");
    }

    @Test
    public void testLogin_Failure() throws ResourceNotFoundException {
        when(userService.login("test@example.com", "password")).thenThrow(new ResourceNotFoundException("Invalid credentials"));

        ResponseEntity<?> response = userController.login("test@example.com", "password");
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid email or password", response.getBody());
        verify(userService, times(1)).login("test@example.com", "password");
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUserId(1L);

        when(userService.saveUser(user)).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).saveUser(user);
    }

   
   
}
