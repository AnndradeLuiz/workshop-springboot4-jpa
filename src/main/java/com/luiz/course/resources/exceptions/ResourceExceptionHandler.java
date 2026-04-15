package com.luiz.course.resources.exceptions;

import com.luiz.course.services.exceptions.DatabaseException;
import com.luiz.course.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartError> resourceNotFound(ReflectiveOperationException e, HttpServletRequest request) {
        String error = "Resource not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError err = new StandartError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandartError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database error.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError err = new StandartError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
