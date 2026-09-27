package com.project.bloodsystem.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    private PasswordValidator passwordValidator;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    void testValidPassword() {
        assertTrue(passwordValidator.isValid("Valid@Pass123", null));
    }

    @Test
    void testNullPassword() {
        assertFalse(passwordValidator.isValid(null, null));
    }

    @Test
    void testTooShort() {
        assertFalse(passwordValidator.isValid("Short1@", null));
    }

    @Test
    void testTooLong() {
        String longPassword = "A".repeat(129) + "1@";
        assertFalse(passwordValidator.isValid(longPassword, null));
    }

    @Test
    void testMissingUppercase() {
        assertFalse(passwordValidator.isValid("lowercase@123", null));
    }

    @Test
    void testMissingLowercase() {
        assertFalse(passwordValidator.isValid("UPPERCASE@123", null));
    }

    @Test
    void testMissingDigit() {
        assertFalse(passwordValidator.isValid("NoDigits@Here", null));
    }

    @Test
    void testMissingSpecialCharacter() {
        assertFalse(passwordValidator.isValid("NoSpecial123", null));
    }

    @Test
    void testMinimumLength() {
        assertTrue(passwordValidator.isValid("Min@1Aaa", null));
    }

    @Test
    void testMaximumLength() {
        String maxPassword = "A".repeat(124) + "1@a";
        assertTrue(passwordValidator.isValid(maxPassword, null));
    }

    @Test
    void testComplexValidPassword() {
        assertTrue(passwordValidator.isValid("Complex!P@ssw0rd#2024", null));
    }
}
