package com.project.bloodsystem.security;

import com.project.bloodsystem.entity.access.Permission;
import com.project.bloodsystem.entity.access.Role;
import com.project.bloodsystem.entity.access.User;
import com.project.bloodsystem.repository.PermissionRepository;
import com.project.bloodsystem.repository.RoleRepository;
import com.project.bloodsystem.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public AuthorizationService(UserRepository userRepository, 
                                RoleRepository roleRepository,
                                PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public boolean hasPermission(String permissionName) {
        Authentication authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return false;
        }

        Set<String> userPermissions = user.getUserRoles().stream()
                .flatMap(userRole -> userRole.getRole().getRolePermissions().stream())
                .map(rolePermission -> rolePermission.getPermission().getName())
                .collect(Collectors.toSet());

        return userPermissions.contains(permissionName);
    }

    public boolean hasHospitalAccess(Long requestedHospitalId) {
        Authentication authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            if (userDetails.getHospitalId() == null) {
                return false;
            }
            return userDetails.getHospitalId().equals(requestedHospitalId);
        }

        return false;
    }

    public boolean hasInstitutionAccess(Long requestedInstitutionId) {
        Authentication authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails.getInstitutionId().equals(requestedInstitutionId);
        }

        return false;
    }

    public boolean isCentralAdmin() {
        Authentication authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails.getRoles().contains("CENTRAL_ADMIN");
        }

        return false;
    }

    public boolean hasHospitalScope() {
        Authentication authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails.getHospitalId() != null;
        }

        return false;
    }
}
