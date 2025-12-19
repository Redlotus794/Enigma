package com.rdlts.enigma.ddd.spring.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EnigmaDDDSpringRuntimeExceptionTest
 *
 * @author wangjialong
 * @since 2025/12/4 10:45
 */
class EnigmaDDDSpringRuntimeExceptionTest {

    @Test
    public void testDefaultConstructor() {
        // When
        EnigmaDDDSpringRuntimeException exception = new EnigmaDDDSpringRuntimeException();

        // Then
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        // Given
        String message = "Test exception message";

        // When
        EnigmaDDDSpringRuntimeException exception = new EnigmaDDDSpringRuntimeException(message);

        // Then
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        // Given
        Throwable cause = new IllegalArgumentException("Cause exception");

        // When
        EnigmaDDDSpringRuntimeException exception = new EnigmaDDDSpringRuntimeException(cause);

        // Then
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        // Given
        String message = "Test exception message";
        Throwable cause = new IllegalArgumentException("Cause exception");

        // When
        EnigmaDDDSpringRuntimeException exception = new EnigmaDDDSpringRuntimeException(message, cause);

        // Then
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithAllParameters() {
        // Given
        String message = "Test exception message";
        Throwable cause = new IllegalArgumentException("Cause exception");
        boolean enableSuppression = false;
        boolean writableStackTrace = false;

        // When
        EnigmaDDDSpringRuntimeException exception = new EnigmaDDDSpringRuntimeException(
                message, cause, enableSuppression, writableStackTrace);

        // Then
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}