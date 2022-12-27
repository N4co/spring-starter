package com.felixweb.projeto.resources.exceptions;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ResourceExceptionHandler extends RuntimeException {


    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardErrors> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        StandardErrors err = new StandardErrors( HttpStatus.NOT_FOUND.value(), "Não Encontrado", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardErrors> dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request) {

        StandardErrors err = new StandardErrors(HttpStatus.BAD_REQUEST.value(), "não é possivel deletar uma categoria que contem produtos", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrors> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());

        for(FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }
}
