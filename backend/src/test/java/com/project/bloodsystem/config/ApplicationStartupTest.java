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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationStartupTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private InstitutionRepository institutionRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Environment environment;

    @Mock
    private PasswordValidator passwordValidator;

    private ApplicationStartup applicationStartup;

    @BeforeEach
    void setUp() {
        applicationStartup = new ApplicationStartup(
                userRepository,
                roleRepository,
                userRoleRepository,
                institutionRepository,
                passwordEncoder,
                environment,
                passwordValidator
        );
    }

    @Test
    void testBootstrap_UsersAlreadyExist_DoesNothing() {
        when(userRepository.count()).thenReturn(1L);

        applicationStartup.run();

        verify(userRepository, never()).save(any(User.class));
        verify(institutionRepository, never()).save(any(Institution.class));
    }

    @Test
    void testBootstrap_MissingInstitutionName_FailsSafely() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn(null);
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn("Valid@Pass123");

        applicationStartup.run();

        verify(userRepository, never()).save(any(User.class));
        verify(institutionRepository, never()).save(any(Institution.class));
    }

    @Test
    void testBootstrap_MissingInstitutionCode_FailsSafely() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("Test Institution");
        when(environment.getProperty("app.institution.code")).thenReturn(null);
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn("Valid@Pass123");

        applicationStartup.run();

        verify(userRepository, never()).save(any(User.class));
        verify(institutionRepository, never()).save(any(Institution.class));
    }

    @Test
    void testBootstrap_MissingAdminUsername_FailsSafely() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("Test Institution");
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn(null);
        when(environment.getProperty("app.admin.password")).thenReturn("Valid@Pass123");

        applicationStartup.run();

        verify(userRepository, never()).save(any(User.class));
        verify(institutionRepository, never()).save(any(Institution.class));
    }

    @Test
    void testBootstrap_MissingAdminPassword_FailsSafely() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("Test Institution");
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn(null);

        applicationStartup.run();

        verify(userRepository, never()).save(any(User.class));
        verify(institutionRepository, never()).save(any(Institution.class));
    }

    @Test
    void testBootstrap_InvalidPassword_FailsSafely() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("Test Institution");
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn("weak");
        when(passwordValidator.isValid("weak", null)).thenReturn(false);

        applicationStartup.run();

        verify(userRepository, never()).save(any(User.class));
        verify(institutionRepository, never()).save(any(Institution.class));
    }

    @Test
    void testBootstrap_FreshDatabase_CreatesInstitutionAndAdmin() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("Test Institution");
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn("Valid@Pass123");
        when(passwordValidator.isValid("Valid@Pass123", null)).thenReturn(true);
        when(institutionRepository.findByCode("TEST_CODE")).thenReturn(Optional.empty());
        when(institutionRepository.existsByCode("TEST_CODE")).thenReturn(false);
        
        Institution savedInstitution = new Institution();
        savedInstitution.setId(1L);
        savedInstitution.setName("Test Institution");
        savedInstitution.setCode("TEST_CODE");
        when(institutionRepository.save(any(Institution.class))).thenReturn(savedInstitution);

        Role centralAdminRole = new Role();
        centralAdminRole.setId(1L);
        centralAdminRole.setName("CENTRAL_ADMIN");
        when(roleRepository.findByName("CENTRAL_ADMIN")).thenReturn(Optional.of(centralAdminRole));

        User savedUser = new User();
        savedUser.setId(1L);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(passwordEncoder.encode("Valid@Pass123")).thenReturn("hashedPassword");

        applicationStartup.run();

        verify(institutionRepository).save(any(Institution.class));
        verify(userRepository).save(any(User.class));
        verify(userRoleRepository).save(any(UserRole.class));
    }

    @Test
    void testBootstrap_ExistingInstitution_ReusesInstitution() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("Test Institution");
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn("Valid@Pass123");
        when(passwordValidator.isValid("Valid@Pass123", null)).thenReturn(true);
        
        Institution existingInstitution = new Institution();
        existingInstitution.setId(1L);
        existingInstitution.setName("Test Institution");
        existingInstitution.setCode("TEST_CODE");
        when(institutionRepository.findByCode("TEST_CODE")).thenReturn(Optional.of(existingInstitution));

        Role centralAdminRole = new Role();
        centralAdminRole.setId(1L);
        centralAdminRole.setName("CENTRAL_ADMIN");
        when(roleRepository.findByName("CENTRAL_ADMIN")).thenReturn(Optional.of(centralAdminRole));

        User savedUser = new User();
        savedUser.setId(1L);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(passwordEncoder.encode("Valid@Pass123")).thenReturn("hashedPassword");

        applicationStartup.run();

        verify(institutionRepository, never()).save(any(Institution.class));
        verify(userRepository).save(any(User.class));
        verify(userRoleRepository).save(any(UserRole.class));
    }

    @Test
    void testBootstrap_DuplicateInstitutionCode_ReusesExisting() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("Test Institution");
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn("Valid@Pass123");
        when(passwordValidator.isValid("Valid@Pass123", null)).thenReturn(true);
        
        Institution existingInstitution = new Institution();
        existingInstitution.setId(1L);
        existingInstitution.setName("Existing Institution");
        existingInstitution.setCode("TEST_CODE");
        when(institutionRepository.findByCode("TEST_CODE")).thenReturn(Optional.of(existingInstitution));

        Role centralAdminRole = new Role();
        centralAdminRole.setId(1L);
        centralAdminRole.setName("CENTRAL_ADMIN");
        when(roleRepository.findByName("CENTRAL_ADMIN")).thenReturn(Optional.of(centralAdminRole));

        User savedUser = new User();
        savedUser.setId(1L);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(passwordEncoder.encode("Valid@Pass123")).thenReturn("hashedPassword");

        applicationStartup.run();

        verify(institutionRepository).findByCode("TEST_CODE");
        verify(institutionRepository, never()).save(any(Institution.class));
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testBootstrap_AdminHasInstitutionId() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("Test Institution");
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn("Valid@Pass123");
        when(passwordValidator.isValid("Valid@Pass123", null)).thenReturn(true);
        when(institutionRepository.findByCode("TEST_CODE")).thenReturn(Optional.empty());
        when(institutionRepository.existsByCode("TEST_CODE")).thenReturn(false);
        
        Institution savedInstitution = new Institution();
        savedInstitution.setId(1L);
        savedInstitution.setName("Test Institution");
        savedInstitution.setCode("TEST_CODE");
        when(institutionRepository.save(any(Institution.class))).thenReturn(savedInstitution);

        Role centralAdminRole = new Role();
        centralAdminRole.setId(1L);
        centralAdminRole.setName("CENTRAL_ADMIN");
        when(roleRepository.findByName("CENTRAL_ADMIN")).thenReturn(Optional.of(centralAdminRole));

        User savedUser = new User();
        savedUser.setId(1L);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            assertNotNull(user.getInstitution());
            assertEquals(1L, user.getInstitution().getId());
            return savedUser;
        });
        when(passwordEncoder.encode("Valid@Pass123")).thenReturn("hashedPassword");

        applicationStartup.run();
    }

    @Test
    void testBootstrap_AdminHospitalIdIsNull() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("Test Institution");
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn("Valid@Pass123");
        when(passwordValidator.isValid("Valid@Pass123", null)).thenReturn(true);
        when(institutionRepository.findByCode("TEST_CODE")).thenReturn(Optional.empty());
        when(institutionRepository.existsByCode("TEST_CODE")).thenReturn(false);
        
        Institution savedInstitution = new Institution();
        savedInstitution.setId(1L);
        savedInstitution.setName("Test Institution");
        savedInstitution.setCode("TEST_CODE");
        when(institutionRepository.save(any(Institution.class))).thenReturn(savedInstitution);

        Role centralAdminRole = new Role();
        centralAdminRole.setId(1L);
        centralAdminRole.setName("CENTRAL_ADMIN");
        when(roleRepository.findByName("CENTRAL_ADMIN")).thenReturn(Optional.of(centralAdminRole));

        User savedUser = new User();
        savedUser.setId(1L);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            assertNull(user.getHospital());
            return savedUser;
        });
        when(passwordEncoder.encode("Valid@Pass123")).thenReturn("hashedPassword");

        applicationStartup.run();
    }

    @Test
    void testBootstrap_PasswordIsHashed() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("Test Institution");
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn("Valid@Pass123");
        when(passwordValidator.isValid("Valid@Pass123", null)).thenReturn(true);
        when(institutionRepository.findByCode("TEST_CODE")).thenReturn(Optional.empty());
        when(institutionRepository.existsByCode("TEST_CODE")).thenReturn(false);
        
        Institution savedInstitution = new Institution();
        savedInstitution.setId(1L);
        savedInstitution.setName("Test Institution");
        savedInstitution.setCode("TEST_CODE");
        when(institutionRepository.save(any(Institution.class))).thenReturn(savedInstitution);

        Role centralAdminRole = new Role();
        centralAdminRole.setId(1L);
        centralAdminRole.setName("CENTRAL_ADMIN");
        when(roleRepository.findByName("CENTRAL_ADMIN")).thenReturn(Optional.of(centralAdminRole));

        User savedUser = new User();
        savedUser.setId(1L);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            assertNotEquals("Valid@Pass123", user.getPasswordHash());
            assertEquals("hashedPassword", user.getPasswordHash());
            return savedUser;
        });
        when(passwordEncoder.encode("Valid@Pass123")).thenReturn("hashedPassword");

        applicationStartup.run();

        verify(passwordEncoder).encode("Valid@Pass123");
    }

    @Test
    void testBootstrap_CentralAdminRoleAssigned() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("Test Institution");
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn("Valid@Pass123");
        when(passwordValidator.isValid("Valid@Pass123", null)).thenReturn(true);
        when(institutionRepository.findByCode("TEST_CODE")).thenReturn(Optional.empty());
        when(institutionRepository.existsByCode("TEST_CODE")).thenReturn(false);
        
        Institution savedInstitution = new Institution();
        savedInstitution.setId(1L);
        savedInstitution.setName("Test Institution");
        savedInstitution.setCode("TEST_CODE");
        when(institutionRepository.save(any(Institution.class))).thenReturn(savedInstitution);

        Role centralAdminRole = new Role();
        centralAdminRole.setId(1L);
        centralAdminRole.setName("CENTRAL_ADMIN");
        when(roleRepository.findByName("CENTRAL_ADMIN")).thenReturn(Optional.of(centralAdminRole));

        User savedUser = new User();
        savedUser.setId(1L);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(passwordEncoder.encode("Valid@Pass123")).thenReturn("hashedPassword");

        applicationStartup.run();

        verify(userRoleRepository).save(argThat(userRole -> 
                userRole.getRole().getName().equals("CENTRAL_ADMIN")
        ));
    }

    @Test
    void testBootstrap_MissingCentralAdminRole_FailsSafely() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("Test Institution");
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn("Valid@Pass123");
        when(passwordValidator.isValid("Valid@Pass123", null)).thenReturn(true);

        when(roleRepository.findByName("CENTRAL_ADMIN")).thenReturn(Optional.empty());

        applicationStartup.run();

        verify(institutionRepository, never()).save(any(Institution.class));
        verify(userRepository, never()).save(any(User.class));
        verify(userRoleRepository, never()).save(any(UserRole.class));
    }

    @Test
    void testBootstrap_EmptyStringConfiguration_FailsSafely() {
        when(userRepository.count()).thenReturn(0L);
        when(environment.getProperty("app.institution.name")).thenReturn("   ");
        when(environment.getProperty("app.institution.code")).thenReturn("TEST_CODE");
        when(environment.getProperty("app.admin.username")).thenReturn("admin");
        when(environment.getProperty("app.admin.password")).thenReturn("Valid@Pass123");

        applicationStartup.run();

        verify(userRepository, never()).save(any(User.class));
        verify(institutionRepository, never()).save(any(Institution.class));
    }
}
