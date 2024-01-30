package com.poscodx.odc.ampro015.config.exceptionhandler;

import org.apache.http.auth.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.FORBIDDEN.value());
        body.put("message", ex.getMessage());
        body.put("path", request.getServletPath());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.BAD_REQUEST.value());
        body.put("message", ex.getMessage());
        body.put("path", request.getServletPath());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value());
        body.put("message", ex.getMessage());
        body.put("path", request.getServletPath());
        return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body(body);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.BAD_REQUEST.value());
        body.put("message", ex.getMessage());
        body.put("path", request.getServletPath());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
//        Map<String, String> listMessage = new LinkedHashMap<>();
        List<String> errorMessages = new LinkedList<>();

        body.put("code", HttpStatus.BAD_REQUEST.value());
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            listMessage.put(fieldName, errorMessage);
//        });

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errorMessages.add(errorMessage);
        });

        body.put("message", errorMessages.get(0)); // get message error first
        body.put("path", request.getServletPath());


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.BAD_REQUEST.value());
        body.put("message", ex.getMessage());
        body.put("path", request.getServletPath());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity <Map<String, String>> handleValidationExceptions( MethodArgumentNotValidException ex){
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((errors)->{
//            String mail = ((String)).g
//        });
//        return ResponseEntity.badRequest().body(errors);
//    }
}
