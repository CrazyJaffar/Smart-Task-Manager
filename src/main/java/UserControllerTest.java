package org.example.userservice.controller;

import org.example.userservice.model.User;
import org.example.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encoded");
        when(userService.register("testuser", "password")).thenReturn(user);
        ResponseEntity<User> response = userController.register("testuser", "password");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("testuser", response.getBody().getUsername());
    }

    @Test
    void testGetCurrentUserFound() {
        User user = new User();
        user.setUsername("testuser");
        when(userService.findByUsername("testuser")).thenReturn(user);
        ResponseEntity<User> response = userController.getCurrentUser("testuser");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("testuser", response.getBody().getUsername());
    }

    @Test
    void testGetCurrentUserNotFound() {
        when(userService.findByUsername("unknown")).thenReturn(null);
        ResponseEntity<User> response = userController.getCurrentUser("unknown");
        assertEquals(404, response.getStatusCodeValue());
    }
}
package org.example.userservice.service;

import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        String username = "testuser";
        String password = "password";
        User user = new User();
        user.setUsername(username);
        user.setPassword("encoded");
        when(passwordEncoder.encode(password)).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User result = userService.register(username, password);
        assertEquals(username, result.getUsername());
        assertEquals("encoded", result.getPassword());
    }

    @Test
    void testFindByUsername() {
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(java.util.Optional.of(user));
        User result = userService.findByUsername(username);
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }
}

