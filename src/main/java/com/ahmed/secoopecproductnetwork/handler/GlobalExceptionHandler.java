package com.ahmed.secoopecproductnetwork.handler;

import com.ahmed.secoopecproductnetwork.exception.OperationNotPermit;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.management.BadBinaryOpValueExpException;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler (BadBinaryOpValueExpException.class)
    public ResponseEntity<ExceptionResponse>handleBadBinaryexe (BadBinaryOpValueExpException exep){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionResponse.builder().businessEroorCode(BusinessErrorCode.BAD_CREDENTIALS.getCode()).businessExceptionDescription(BusinessErrorCode.BAD_CREDENTIALS.getDescription()).error(exep.getMessage()).build());
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException exp) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .error(exp.getMessage())
                                .build()
                );
    }
    @ExceptionHandler(OperationNotPermit.class)
    public ResponseEntity<ExceptionResponse> handleExceptionnotpermit(MessagingException exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    //var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .validationErrors(errors)
                                .build()
                );
    }


        @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exp) {
        exp.printStackTrace();
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessExceptionDescription("Internal error, please contact the admin")
                                .error(exp.getMessage())
                                .build()
                );
    }










    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse>handleDisbaledExeption(DisabledException exep){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionResponse.builder().businessEroorCode(BusinessErrorCode.ACCOUNT_DISABLED.getCode()).businessExceptionDescription(BusinessErrorCode.ACCOUNT_DISABLED.getDescription()).error(exep.getMessage()).build()); }


@ExceptionHandler(EntityNotFoundException.class)
public ResponseEntity<ExceptionResponse>handlenotfoundException(EntityNotFoundException exem){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ExceptionResponse.builder().businessEroorCode(BusinessErrorCode.NOT_FOUND.getCode()).businessExceptionDescription(BusinessErrorCode.NOT_FOUND.getDescription()).error(exem.getMessage()).build());
}



@ExceptionHandler(LockedException.class)
//we we catch this execption we return thus response
    public ResponseEntity<ExceptionResponse> handleLocked (LockedException exp){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionResponse.builder().businessEroorCode(BusinessErrorCode.ACCOUNT_LOCKED.getCode()).businessExceptionDescription(BusinessErrorCode.ACCOUNT_LOCKED.getDescription()).error(exp.getMessage()).build());
    }
}
