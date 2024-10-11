package com.revature.user.service;

import com.revature.user.model.User;
import com.revature.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for loadUserByUsername (Success)
    @Test
    public void testLoadUserByUsername_Success() {
        String email = "test@example.com";
        String password = "password";
        Set<User.Role> roles = new HashSet<>();
        roles.add(User.Role.BUYER);

        User mockUser = new User();
        mockUser.setEmail(email);
        mockUser.setPassword(password);
        mockUser.setRoles(roles);

        // Mocking repository behavior
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        // Call the method
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Verify and assert
        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());

        // Verify that authorities contain the appropriate role
        assertTrue(userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_BUYER")));

        verify(userRepository, times(1)).findByEmail(email);
    }

    // Test for loadUserByUsername (Failure)
    @Test
    public void testLoadUserByUsername_Failure() {
        String email = "nonexistent@example.com";

        // Mocking repository behavior to return empty Optional
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Call the method and expect UsernameNotFoundException
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(email);
        });

        // Assert the exception message
        assertEquals("User not found with email: " + email, exception.getMessage());

        verify(userRepository, times(1)).findByEmail(email);
    }
}
