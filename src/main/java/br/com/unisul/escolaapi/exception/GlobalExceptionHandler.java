package br.com.unisul.escolaapi.exception;

import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> illegalArgumentException(IllegalArgumentException e) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("status", HttpStatus.BAD_REQUEST.value());
        erro.put("mensagem", e.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> constraintViolationException(ConstraintViolationException e) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("status", HttpStatus.BAD_REQUEST.value());
        StringBuilder violacoes = new StringBuilder();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            violacoes.append("O atributo '").append(violation.getPropertyPath().toString())
                    .append("' apresentou o seguinte erro: ").append(violation.getMessage());
        }
        erro.put("mensagem", violacoes.toString());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Map<String, Object>> noResultException(Exception e) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("status", HttpStatus.NOT_FOUND.value());
        erro.put("mensagem", e.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }
}
