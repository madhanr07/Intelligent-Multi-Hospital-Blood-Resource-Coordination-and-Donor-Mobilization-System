package com.project.bloodsystem.security;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;
    private String testSecret;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        testSecret = "ThisIsAVeryStrongSecretKeyForTestingJWTTokenGenerationAndValidationThatIsAtLeast256BitsLong";
        ReflectionTestUtils.setField(jwtService, "jwtSecret", testSecret);
        ReflectionTestUtils.setField(jwtService, "jwtExpirationMs", 28800000L);
    }

    @Test
    void testGenerateToken() {
        String token = jwtService.generateToken(1L, "testuser", List.of("ROLE_USER"), 1L, 2L);
        
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testExtractSubject() {
        String token = jwtService.generateToken(1L, "testuser", List.of("ROLE_USER"), 1L, 2L);
        String subject = jwtService.extractSubject(token);
        
        assertEquals("1", subject);
    }

    @Test
    void testExtractUsername() {
        String token = jwtService.generateToken(1L, "testuser", List.of("ROLE_USER"), 1L, 2L);
        String username = jwtService.extractUsername(token);
        
        assertEquals("testuser", username);
    }

    @Test
    void testExtractRoles() {
        List<String> roles = List.of("CENTRAL_ADMIN", "BLOOD_BANK_STAFF");
        String token = jwtService.generateToken(1L, "testuser", roles, 1L, 2L);
        List<String> extractedRoles = jwtService.extractRoles(token);
        
        assertEquals(roles, extractedRoles);
    }

    @Test
    void testExtractInstitutionId() {
        String token = jwtService.generateToken(1L, "testuser", List.of("ROLE_USER"), 1L, 2L);
        Long institutionId = jwtService.extractInstitutionId(token);
        
        assertEquals(1L, institutionId);
    }

    @Test
    void testExtractHospitalId() {
        String token = jwtService.generateToken(1L, "testuser", List.of("ROLE_USER"), 1L, 2L);
        Long hospitalId = jwtService.extractHospitalId(token);
        
        assertEquals(2L, hospitalId);
    }

    @Test
    void testValidateToken_Valid() {
        String token = jwtService.generateToken(1L, "testuser", List.of("ROLE_USER"), 1L, 2L);
        
        assertTrue(jwtService.validateToken(token));
    }

    @Test
    void testValidateToken_Invalid() {
        String invalidToken = "invalid.token.here";
        
        assertFalse(jwtService.validateToken(invalidToken));
    }

    @Test
    void testValidateToken_Expired() throws InterruptedException {
        ReflectionTestUtils.setField(jwtService, "jwtExpirationMs", 1L);
        String token = jwtService.generateToken(1L, "testuser", List.of("ROLE_USER"), 1L, 2L);
        Thread.sleep(10);
        
        assertFalse(jwtService.validateToken(token));
    }

    @Test
    void testTokenWithNullHospitalId() {
        String token = jwtService.generateToken(1L, "testuser", List.of("CENTRAL_ADMIN"), 1L, null);
        Long hospitalId = jwtService.extractHospitalId(token);
        
        assertNull(hospitalId);
    }
}
