package com.revature.user.controller;

import com.revature.user.model.User;
import com.revature.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for getAllUsers (Success)
    @Test
    public void testGetAllUsers_Success() {
        // Prepare mock data
        List<User> mockUsers = Arrays.asList(new User(), new User());

        // Mock service behavior
        when(userService.getAllUsers()).thenReturn(mockUsers);

        // Call the method
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Verify and assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsers, response.getBody());
        verify(userService, times(1)).getAllUsers();
    }

    // Test for getAllUsers (Failure)
    @Test
    public void testGetAllUsers_Failure() {
        // Mock service behavior to throw an exception
        when(userService.getAllUsers()).thenThrow(new RuntimeException("Database error"));

        // Call the method and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userController.getAllUsers();
        });

        // Assert exception message
        assertEquals("Database error", exception.getMessage());
    }

    // Test for getUserById (Success)
    @Test
    public void testGetUserById_Success() {
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);

        // Mock service behavior
        when(userService.getUserById(userId)).thenReturn(Optional.of(mockUser));

        // Call the method
        ResponseEntity<User> response = userController.getUserById(userId);

        // Verify and assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUser, response.getBody());
        verify(userService, times(1)).getUserById(userId);
    }

    // Test for getUserById (Failure)
    @Test
    public void testGetUserById_Failure() {
        Long userId = 1L;

        // Mock service behavior to return empty Optional
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        // Call the method
        ResponseEntity<User> response = userController.getUserById(userId);

        // Verify and assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).getUserById(userId);
    }

    // Test for createUser (Success)
    @Test
    public void testCreateUser_Success() {
        User mockUser = new User();
        mockUser.setEmail("test@example.com");

        // Mock service behavior
        when(userService.saveUser(mockUser)).thenReturn(mockUser);

        // Call the method
        ResponseEntity<User> response = userController.createUser(mockUser);

        // Verify and assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUser, response.getBody());
        verify(userService, times(1)).saveUser(mockUser);
    }

    // Test for createUser (Failure)
    @Test
    public void testCreateUser_Failure() {
        User mockUser = new User();
        mockUser.setEmail("test@example.com");

        // Mock service behavior to throw an exception
        when(userService.saveUser(mockUser)).thenThrow(new RuntimeException("Failed to save user"));

        // Call the method and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userController.createUser(mockUser);
        });

        // Assert exception message
        assertEquals("Failed to save user", exception.getMessage());
    }

    // Test for deleteUser (Success)
    @Test
    public void testDeleteUser_Success() {
        Long userId = 1L;

        // Mock service behavior
        doNothing().when(userService).deleteUser(userId);

        // Call the method
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Verify and assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

    // Test for deleteUser (Failure)
    @Test
    public void testDeleteUser_Failure() {
        Long userId = 1L;

        // Mock service behavior to throw an exception
        doThrow(new RuntimeException("Failed to delete user")).when(userService).deleteUser(userId);

        // Call the method and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userController.deleteUser(userId);
        });

        // Assert exception message
        assertEquals("Failed to delete user", exception.getMessage());
    }

    // Test for addRoleToUser (Success)
    @Test
    public void testAddRoleToUser_Success() {
        Long userId = 1L;
        User.Role role = User.Role.ADMIN;

        // Mock service behavior
        doNothing().when(userService).addRoleToUser(userId, role);

        // Call the method
        ResponseEntity<Void> response = userController.addRoleToUser(userId, role);

        // Verify and assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).addRoleToUser(userId, role);
    }

    // Test for addRoleToUser (Failure)
    @Test
    public void testAddRoleToUser_Failure() {
        Long userId = 1L;
        User.Role role = User.Role.ADMIN;

        // Mock service behavior to throw an exception
        doThrow(new RuntimeException("Failed to add role to user")).when(userService).addRoleToUser(userId, role);

        // Call the method and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userController.addRoleToUser(userId, role);
        });

        // Assert exception message
        assertEquals("Failed to add role to user", exception.getMessage());
    }
}
