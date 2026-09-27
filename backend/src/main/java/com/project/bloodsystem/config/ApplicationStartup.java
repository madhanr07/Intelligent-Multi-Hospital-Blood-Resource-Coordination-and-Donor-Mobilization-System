package com.project.bloodsystem.config;

import com.project.bloodsystem.entity.access.Role;
import com.project.bloodsystem.entity.access.User;
import com.project.bloodsystem.entity.access.UserRole;
import com.project.bloodsystem.entity.institution.Institution;
import com.project.bloodsystem.repository.InstitutionRepository;
import com.project.bloodsystem.repository.RoleRepository;
import com.project.bloodsystem.repository.UserRepository;
import com.project.bloodsystem.repository.UserRoleRepository;
import com.project.bloodsystem.validation.PasswordValidator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final InstitutionRepository institutionRepository;
    private final PasswordEncoder passwordEncoder;
    private final Environment environment;
    private final PasswordValidator passwordValidator;

    public ApplicationStartup(UserRepository userRepository,
                              RoleRepository roleRepository,
                              UserRoleRepository userRoleRepository,
                              InstitutionRepository institutionRepository,
                              PasswordEncoder passwordEncoder,
                              Environment environment,
                              PasswordValidator passwordValidator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.institutionRepository = institutionRepository;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
        this.passwordValidator = passwordValidator;
    }

    @Override
    @Transactional
    public void run(String... args) {
        long userCount = userRepository.count();
        
        if (userCount > 0) {
            return;
        }

        String institutionName = environment.getProperty("app.institution.name");
        String institutionCode = environment.getProperty("app.institution.code");
        String adminUsername = environment.getProperty("app.admin.username");
        String adminPassword = environment.getProperty("app.admin.password");

        if (institutionName == null || institutionName.trim().isEmpty() ||
            institutionCode == null || institutionCode.trim().isEmpty() ||
            adminUsername == null || adminUsername.trim().isEmpty() ||
            adminPassword == null || adminPassword.trim().isEmpty()) {
            System.out.println("Bootstrap: Missing required environment variables. Skipping bootstrap.");
            System.out.println("Required: INITIAL_INSTITUTION_NAME, INITIAL_INSTITUTION_CODE, INITIAL_ADMIN_USERNAME, INITIAL_ADMIN_PASSWORD");
            return;
        }

        if (!passwordValidator.isValid(adminPassword, null)) {
            System.out.println("Bootstrap: Password does not meet security requirements. Skipping bootstrap.");
            return;
        }

        try {
            Optional<Role> centralAdminRoleOpt = roleRepository.findByName("CENTRAL_ADMIN");
            if (centralAdminRoleOpt.isEmpty()) {
                System.out.println("Bootstrap: CENTRAL_ADMIN role not found. Skipping bootstrap.");
                return;
            }

            Role centralAdminRole = centralAdminRoleOpt.get();

            Institution institution = institutionRepository.findByCode(institutionCode.trim())
                    .orElseGet(() -> createInstitution(institutionName.trim(), institutionCode.trim()));

            if (institution == null) {
                System.out.println("Bootstrap: Failed to create or find institution. Skipping bootstrap.");
                return;
            }

            User adminUser = new User();
            adminUser.setUsername(adminUsername.trim());
            adminUser.setPasswordHash(passwordEncoder.encode(adminPassword));
            adminUser.setEmail(adminUsername.trim() + "@hemonexus.com");
            adminUser.setFirstName("System");
            adminUser.setLastName("Administrator");
            adminUser.setInstitution(institution);
            adminUser.setHospital(null);
            adminUser.setStatus("ACTIVE");

            User savedUser = userRepository.save(adminUser);

            UserRole userRole = new UserRole();
            userRole.setUser(savedUser);
            userRole.setRole(centralAdminRole);
            userRoleRepository.save(userRole);

            System.out.println("Bootstrap: Initial Central Admin created successfully.");
            System.out.println("Username: " + adminUsername);
            System.out.println("Institution: " + institution.getName() + " (" + institution.getCode() + ")");

        } catch (Exception e) {
            System.out.println("Bootstrap: Error during bootstrap: " + e.getMessage());
            throw e;
        }
    }

    private Institution createInstitution(String name, String code) {
        if (institutionRepository.existsByCode(code)) {
            return institutionRepository.findByCode(code).orElse(null);
        }

        Institution institution = new Institution();
        institution.setName(name);
        institution.setCode(code);
        institution.setStatus("ACTIVE");
        
        return institutionRepository.save(institution);
    }
}
