package com.project.bloodsystem.service;

import com.project.bloodsystem.dto.LoginRequest;
import com.project.bloodsystem.dto.LoginResponse;
import com.project.bloodsystem.dto.UserResponse;
import com.project.bloodsystem.entity.access.User;
import com.project.bloodsystem.repository.UserRepository;
import com.project.bloodsystem.security.JwtService;
import com.project.bloodsystem.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthenticationService(UserRepository userRepository,
                                  PasswordEncoder passwordEncoder,
                                  JwtService jwtService,
                                  UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public LoginResponse authenticate(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid username or password");
        }

        if (!"ACTIVE".equals(user.getStatus())) {
            throw new RuntimeException("Account is inactive. Please contact administrator.");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(user.getUsername());

        List<String> roles = userDetails.getRoles().stream().toList();
        Long institutionId = userDetails.getInstitutionId();
        Long hospitalId = userDetails.getHospitalId();

        String token = jwtService.generateToken(
                userDetails.getUserId(),
                userDetails.getUsername(),
                roles,
                institutionId,
                hospitalId
        );

        String hospitalName = user.getHospital() != null ? user.getHospital().getName() : null;

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                userDetails.getRoles(),
                institutionId,
                hospitalId,
                hospitalName
        );

        return new LoginResponse(token, "Bearer", 28800000L, userResponse);
    }
}
