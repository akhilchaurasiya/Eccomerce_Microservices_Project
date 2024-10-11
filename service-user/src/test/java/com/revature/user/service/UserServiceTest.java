package com.revature.user.service;

import com.revature.user.model.User;
import com.revature.user.model.User.Role;
import com.revature.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for saveUser (Success)
    @Test
    public void testSaveUser_Success() {
        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password");

        // Mock password encoding
        when(passwordEncoder.encode(mockUser.getPassword())).thenReturn("encodedPassword");

        // Mock saving user in the repository
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        // Call the method
        User savedUser = userService.saveUser(mockUser);

        // Verify and assert
        assertNotNull(savedUser);
        assertEquals("encodedPassword", savedUser.getPassword());
        verify(userRepository, times(1)).save(mockUser);
    }

    // Test for saveUser (Failure)
    @Test
    public void testSaveUser_Failure() {
        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password");

        // Mock password encoding
        when(passwordEncoder.encode(mockUser.getPassword())).thenReturn("encodedPassword");

        // Simulate a failure when saving the user
        when(userRepository.save(mockUser)).thenThrow(new RuntimeException("Database error"));

        // Call the method and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.saveUser(mockUser);
        });

        // Assert exception message
        assertEquals("Failed to save user.", exception.getMessage());
    }

    // Test for getUserById (Success)
    @Test
    public void testGetUserById_Success() {
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);

        // Mock finding user by ID
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // Call the method
        Optional<User> fetchedUser = userService.getUserById(userId);

        // Verify and assert
        assertTrue(fetchedUser.isPresent());
        assertEquals(userId, fetchedUser.get().getId());
        verify(userRepository, times(1)).findById(userId);
    }

    // Test for getUserById (Failure)
    @Test
    public void testGetUserById_Failure() {
        Long userId = 1L;

        // Mock finding user by ID to return empty
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Call the method and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        });

        // Assert exception message
        assertEquals("User not found", exception.getMessage());
    }

    // Test for getAllUsers (Success)
    @Test
    public void testGetAllUsers_Success() {
        List<User> mockUsers = Arrays.asList(new User(), new User());

        // Mock finding all users
        when(userRepository.findAll()).thenReturn(mockUsers);

        // Call the method
        List<User> users = userService.getAllUsers();

        // Verify and assert
        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    // Test for getAllUsers (Failure)
    @Test
    public void testGetAllUsers_Failure() {
        // Mock repository to throw an exception
        when(userRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // Call the method and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getAllUsers();
        });

        // Assert exception message
        assertEquals("Failed to fetch users.", exception.getMessage());
    }

    // Test for deleteUser (Success)
    @Test
    public void testDeleteUser_Success() {
        Long userId = 1L;

        // Mock deleting user by ID
        doNothing().when(userRepository).deleteById(userId);

        // Call the method
        userService.deleteUser(userId);

        // Verify
        verify(userRepository, times(1)).deleteById(userId);
    }

    // Test for deleteUser (Failure)
    @Test
    public void testDeleteUser_Failure() {
        Long userId = 1L;

        // Mock repository to throw an exception
        doThrow(new RuntimeException("Database error")).when(userRepository).deleteById(userId);

        // Call the method and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.deleteUser(userId);
        });

        // Assert exception message
        assertEquals("Failed to delete user.", exception.getMessage());
    }

    // Test for addRoleToUser (Success)
    @Test
    public void testAddRoleToUser_Success() {
        Long userId = 1L;
        Role role = Role.ADMIN;
        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setRoles(new HashSet<>());

        // Mock finding user by ID and saving the updated user
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        // Call the method
        userService.addRoleToUser(userId, role);

        // Verify
        assertTrue(mockUser.getRoles().contains(role));
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(mockUser);
    }
}
