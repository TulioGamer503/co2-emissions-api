package com.emissions.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.MDC;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ---------- 404 (recurso de tu dominio) ----------
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(
            ResourceNotFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), req, null);
    }

    // ---------- 400 (validaciones de tu dominio) ----------
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            ValidationException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req, null);
    }

    // ---------- 400 (Bean Validation en @RequestBody) ----------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleBeanValidation(
            MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fe -> fe.getDefaultMessage() == null ? "Invalid value" : fe.getDefaultMessage(),
                        (a, b) -> a)); // evita claves duplicadas
        return build(HttpStatus.BAD_REQUEST, "Validación fallida", req, fieldErrors);
    }

    // ---------- 400 (Bean Validation en @PathVariable / @RequestParam) ----------
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(
            ConstraintViolationException ex, HttpServletRequest req) {
        Map<String, String> fieldErrors = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        v -> v.getMessage() == null ? "Invalid value" : v.getMessage(),
                        (a, b) -> a));
        return build(HttpStatus.BAD_REQUEST, "Validación de parámetros fallida", req, fieldErrors);
    }

    // ---------- 400 (JSON mal formado o tipos incompatibles en el body) ----------
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleNotReadable(
            HttpMessageNotReadableException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, "Cuerpo de la solicitud inválido o mal formado", req, null);
    }

    // ---------- 400 (falta un parámetro requerido) ----------
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> handleMissingParam(
            MissingServletRequestParameterException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, "Falta el parámetro requerido: " + ex.getParameterName(), req, null);
    }

    // ---------- 400 (tipo de parámetro incorrecto, ej: id=abc donde Long era esperado) ----------
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpServletRequest req) {
        String msg = "Tipo de parámetro inválido: " + ex.getName() +
                " (esperado: " + (ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido") + ")";
        return build(HttpStatus.BAD_REQUEST, msg, req, null);
    }

    // ---------- 405 (método no permitido) ----------
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpServletRequest req) {
        return build(HttpStatus.METHOD_NOT_ALLOWED, "Método HTTP no permitido", req, null);
    }

    // ---------- 415 (media type no soportado) ----------
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex, HttpServletRequest req) {
        return build(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Tipo de contenido no soportado", req, null);
    }

    // ---------- 409 / 422 (violaciones de integridad / negocio) ----------
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrity(
            DataIntegrityViolationException ex, HttpServletRequest req) {
        return build(HttpStatus.CONFLICT, "Violación de integridad de datos", req, null);
    }

    // ---------- 500 (errores de acceso a datos genéricos) ----------
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccess(
            DataAccessException ex, HttpServletRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Error de acceso a datos", req, null);
    }

    // ---------- 404 (ruta no existente) ----------
    // Requiere en application.properties:
    // spring.mvc.throw-exception-if-no-handler-found=true
    // spring.web.resources.add-mappings=false
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoHandler(
            NoHandlerFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, "Ruta no encontrada", req, null);
    }

    // ---------- 400 (argumento ilegal en lógica de negocio) ----------
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(
            IllegalArgumentException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req, null);
    }

    // ---------- 500 (genérico) ----------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(
            Exception ex, HttpServletRequest req) {
        // Aquí podrías loggear ex con stacktrace (logger.error)
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", req, null);
    }

    // ----------------------- helpers -----------------------

    private ResponseEntity<Map<String, Object>> build(HttpStatus status,
                                                      String message,
                                                      HttpServletRequest req,
                                                      Map<String, String> fieldErrors) {
        Map<String, Object> body = base(status, message, req);
        if (fieldErrors != null && !fieldErrors.isEmpty()) {
            body.put("errors", fieldErrors);
        }
        return ResponseEntity.status(status).body(body);
    }

    private Map<String, Object> base(HttpStatus status, String message, HttpServletRequest req) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("timestamp", Instant.now().toString());
        m.put("status", status.value());
        m.put("error", status.getReasonPhrase());
        m.put("message", message);
        m.put("path", req != null ? req.getRequestURI() : "");
        // Si usas MDC con un filtro, podrás correlacionar logs/errores:
        String traceId = MDC.get("traceId");
        if (traceId != null) m.put("traceId", traceId);
        return m;
    }
}
