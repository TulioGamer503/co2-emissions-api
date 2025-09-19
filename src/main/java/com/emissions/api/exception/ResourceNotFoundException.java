package com.emissions.api.exception;

/**
 * Excepción personalizada para manejar casos en que un recurso no es encontrado.
 * Extiende RuntimeException para poder lanzarse sin necesidad de declararla.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor que recibe un mensaje personalizado de error.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor que genera automáticamente un mensaje de error
     * indicando el recurso, el campo y el valor no encontrado.
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s no encontrado con %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
