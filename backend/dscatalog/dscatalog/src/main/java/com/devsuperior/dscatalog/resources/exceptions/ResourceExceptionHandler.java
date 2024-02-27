package com.devsuperior.dscatalog.resources.exceptions;


import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}
/**
 * Esta classe vai fazer o tratamento das excessões do controller
 * Handler vai ser o manipulador das excessões a nivel da camada do resource
 * O metodo ResponseEntity vai ser uma resposta da requisição onde o payload
 * requisição do tipo StandardError
 */
/**
 * O controllerAdvice vai permitir que essa classe intercept alguma excessão
 * na camada REST do resource
 */
/**
 * Deve ser importado o EntityNotFoundException do seu PROJETO não o import do JAKARTA
 */