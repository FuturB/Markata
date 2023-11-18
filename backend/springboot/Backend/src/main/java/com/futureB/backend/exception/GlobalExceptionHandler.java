package com.futureB.backend.exception;

import com.futureB.backend.password.ResetPasswordResponse;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.UnsupportedEncodingException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResetPasswordResponse> handleUserNotFoundException(UserNotFoundException ex){
        ResetPasswordResponse response = new ResetPasswordResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ResetPasswordResponse> handleEmailSendingException(MessagingException ex){
        ResetPasswordResponse response = new ResetPasswordResponse("Error Sending Email");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UnsupportedEncodingException.class)
    public ResponseEntity<ResetPasswordResponse> handleEmailSendingException(UnsupportedEncodingException ex){
        ResetPasswordResponse response = new ResetPasswordResponse("Error Sending Email");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ResetPasswordResponse> handleInvalidTokenException(InvalidTokenException ex){
        ResetPasswordResponse response = new ResetPasswordResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
