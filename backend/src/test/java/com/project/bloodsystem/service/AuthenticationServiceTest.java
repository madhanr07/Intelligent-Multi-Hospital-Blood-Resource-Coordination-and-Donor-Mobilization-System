package com.project.bloodsystem.service;

import com.project.bloodsystem.dto.LoginRequest;
import com.project.bloodsystem.dto.LoginResponse;
import com.project.bloodsystem.entity.access.User;
import com.project.bloodsystem.entity.institution.Hospital;
import com.project.bloodsystem.entity.institution.Institution;
import com.project.bloodsystem.repository.UserRepository;
import com.project.bloodsystem.security.JwtService;
import com.project.bloodsystem.security.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationService(userRepository, passwordEncoder, jwtService, userDetailsService);
    }

    @Test
    void testAuthenticate_ValidCredentials() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPasswordHash("hashedPassword");
        user.setEmail("test@example.com");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setStatus("ACTIVE");
        user.setUserRoles(new HashSet<>());

        Institution institution = new Institution();
        institution.setId(1L);
        user.setInstitution(institution);

        Hospital hospital = new Hospital();
        hospital.setId(2L);
        hospital.setName("Test Hospital");
        user.setHospital(hospital);

        LoginRequest request = new LoginRequest("testuser", "Valid@Pass123");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("Valid@Pass123", "hashedPassword")).thenReturn(true);

        UserDetailsImpl userDetails = new UserDetailsImpl(user, new HashSet<>());
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);

        when(jwtService.generateToken(anyLong(), anyString(), any(), anyLong(), anyLong()))
                .thenReturn("test.jwt.token");

        LoginResponse response = authenticationService.authenticate(request);

        assertNotNull(response);
        assertEquals("test.jwt.token", response.getAccessToken());
        assertEquals("Bearer", response.getTokenType());
        assertEquals(28800000L, response.getExpiresIn());
        assertNotNull(response.getUser());
        assertEquals("testuser", response.getUser().getUsername());
    }

    @Test
    void testAuthenticate_InvalidUsername() {
        LoginRequest request = new LoginRequest("nonexistent", "Valid@Pass123");

        when(userRepository.findByUsername("nonexistent")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authenticationService.authenticate(request));
    }

    @Test
    void testAuthenticate_InvalidPassword() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPasswordHash("hashedPassword");
        user.setStatus("ACTIVE");

        LoginRequest request = new LoginRequest("testuser", "WrongPassword");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("WrongPassword", "hashedPassword")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> authenticationService.authenticate(request));
    }

    @Test
    void testAuthenticate_InactiveAccount() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPasswordHash("hashedPassword");
        user.setStatus("INACTIVE");

        LoginRequest request = new LoginRequest("testuser", "Valid@Pass123");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("Valid@Pass123", "hashedPassword")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> authenticationService.authenticate(request));
    }
}
