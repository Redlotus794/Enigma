package com.rdlts.enigma.ddd.core.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EnigmaDDDRuntimeExceptionTest
 *
 * @author wangjialong
 * @since 2025/12/1 15:39
 */
public class EnigmaDDDRuntimeExceptionTest {

    @Test
    public void testDefaultConstructor() {
        // When
        EnigmaDDDRuntimeException exception = new EnigmaDDDRuntimeException();

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
        EnigmaDDDRuntimeException exception = new EnigmaDDDRuntimeException(message);

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
        EnigmaDDDRuntimeException exception = new EnigmaDDDRuntimeException(cause);

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
        EnigmaDDDRuntimeException exception = new EnigmaDDDRuntimeException(message, cause);

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
        EnigmaDDDRuntimeException exception = new EnigmaDDDRuntimeException(
                message, cause, enableSuppression, writableStackTrace);

        // Then
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}