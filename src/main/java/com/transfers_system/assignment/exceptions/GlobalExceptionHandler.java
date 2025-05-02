package com.transfers_system.assignment.exceptions;
/*
 * Created by: Shyam Gupta
 * Date: 01/05/25
 * Project: assignment
 */

import com.transfers_system.assignment.exceptions.custom.*;
import com.transfers_system.assignment.pojos.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                sb.append(error.getDefaultMessage()));

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", errors);

        return new ResponseEntity<>(getErrorResponse(sb.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAccountExists(AccountAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(getErrorResponse(ex.getLocalizedMessage()));
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseException(DatabaseException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(getErrorResponse(ex.getLocalizedMessage()));
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorResponse(ex.getLocalizedMessage()));
    }

    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseOperationException(DatabaseOperationException ex) {
        return new ResponseEntity<>(getErrorResponse(ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccountDoeNotExistException.class)
    public ResponseEntity<ErrorResponse> handleAccountDoeNotExistException(AccountDoeNotExistException ex) {
        return new ResponseEntity<>(getErrorResponse(ex.getLocalizedMessage()), HttpStatus.OK);
    }


    @ExceptionHandler(InSufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInSufficientBalanceException(InSufficientBalanceException ex) {
        return new ResponseEntity<>(getErrorResponse(ex.getLocalizedMessage()), HttpStatus.OK);
    }

    private ErrorResponse getErrorResponse(String message){
        return new ErrorResponse(false, message);
    }
}
