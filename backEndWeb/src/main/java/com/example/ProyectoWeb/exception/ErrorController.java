package com.example.ProyectoWeb.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(CorreoRegistradoException.class)
    public ResponseEntity<String> handleCorreoRegistradoException(CorreoRegistradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CamposInvalidosException.class)
    public ResponseEntity<String> handleCamposInvalidosException(CamposInvalidosException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Error interno del servidor. Por favor, inténtelo más tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

