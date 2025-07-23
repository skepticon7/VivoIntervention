package org.example.backend.Exceptions;

import org.example.backend.Utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.MalformedParametersException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String , String> fieldMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            fieldMap.put(fieldError.getField() , fieldError.getDefaultMessage());
        });
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(String.valueOf(HttpStatus.BAD_REQUEST))
                .message(fieldMap.toString())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(String.valueOf(HttpStatus.NOT_FOUND))
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(String.valueOf(HttpStatus.BAD_REQUEST))
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse , HttpStatus.BAD_REQUEST);
    }

}
