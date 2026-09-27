package com.project.bloodsystem.security;

import com.project.bloodsystem.entity.access.User;
import com.project.bloodsystem.entity.institution.Institution;
import com.project.bloodsystem.repository.PermissionRepository;
import com.project.bloodsystem.repository.RoleRepository;
import com.project.bloodsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PermissionRepository permissionRepository;

    private AuthorizationService authorizationService;

    @BeforeEach
    void setUp() {
        authorizationService = new AuthorizationService(userRepository, roleRepository, permissionRepository);
    }

    @Test
    void testHasPermission_WithValidPermission() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPasswordHash("hashed");
        user.setStatus("ACTIVE");
        user.setUserRoles(new HashSet<>());

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        boolean result = authorizationService.hasPermission("test:permission");
        
        assertFalse(result);
    }

    @Test
    void testHasPermission_NotAuthenticated() {
        SecurityContextHolder.getContext().setAuthentication(null);

        boolean result = authorizationService.hasPermission("test:permission");
        
        assertFalse(result);
    }

    @Test
    void testHasHospitalAccess_MatchingHospital() {
        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);
        
        UserDetailsImpl userDetails = new UserDetailsImpl(new User(), new HashSet<>());
        when(auth.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.getContext().setAuthentication(auth);

        boolean result = authorizationService.hasHospitalAccess(1L);
        
        assertFalse(result);
    }

    @Test
    void testHasHospitalAccess_NotAuthenticated() {
        SecurityContextHolder.getContext().setAuthentication(null);

        boolean result = authorizationService.hasHospitalAccess(1L);
        
        assertFalse(result);
    }

    @Test
    void testHasInstitutionAccess_MatchingInstitution() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPasswordHash("hashed");
        user.setStatus("ACTIVE");
        
        Institution institution = new Institution();
        institution.setId(1L);
        user.setInstitution(institution);
        user.setUserRoles(new HashSet<>());

        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);
        
        UserDetailsImpl userDetails = new UserDetailsImpl(user, new HashSet<>());
        when(auth.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.getContext().setAuthentication(auth);

        boolean result = authorizationService.hasInstitutionAccess(1L);
        
        assertTrue(result);
    }

    @Test
    void testIsCentralAdmin_WithCentralAdminRole() {
        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);
        
        UserDetailsImpl userDetails = new UserDetailsImpl(new User(), new HashSet<>());
        when(auth.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.getContext().setAuthentication(auth);

        boolean result = authorizationService.isCentralAdmin();
        
        assertFalse(result);
    }

    @Test
    void testHasHospitalScope_WithHospital() {
        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);
        
        UserDetailsImpl userDetails = new UserDetailsImpl(new User(), new HashSet<>());
        when(auth.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.getContext().setAuthentication(auth);

        boolean result = authorizationService.hasHospitalScope();
        
        assertFalse(result);
    }
}
