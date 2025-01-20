package io.github.materialcontrol.ms_loan.controllers.exception;

import io.github.materialcontrol.ms_loan.exception.FeingRequestException;
import io.github.materialcontrol.ms_loan.exception.ItemUnavailableException;
import io.github.materialcontrol.ms_loan.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(HttpServletRequest request, BindingResult br) {
        String message = "field validation error";

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new StandardError(request, HttpStatus.UNPROCESSABLE_ENTITY, message, br));
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFoundException(RuntimeException e, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new StandardError(request, HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(FeingRequestException.class)
    public ResponseEntity<StandardError> feingRequestException(RuntimeException e, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new StandardError(request, HttpStatus.SERVICE_UNAVAILABLE, e.getMessage()));
    }

    @ExceptionHandler(ItemUnavailableException.class)
    public ResponseEntity<StandardError> itemUnavailableException(RuntimeException e, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new StandardError(request, HttpStatus.BAD_REQUEST, e.getMessage()));
    }
}
