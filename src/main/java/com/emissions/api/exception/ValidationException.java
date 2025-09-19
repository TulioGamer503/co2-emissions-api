package com.emissions.api.exception;

/**
 * Excepción personalizada para errores de validación de negocio o entrada.
 * Extiende RuntimeException para permitir su lanzamiento sin declaración checked.
 */
public class ValidationException extends RuntimeException {

    /** Constructor que acepta un mensaje descriptivo del error de validación. */
    public ValidationException(String message) {
        super(message);
    }

    /** Constructor que agrega la causa original (encadenamiento de excepciones). */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
