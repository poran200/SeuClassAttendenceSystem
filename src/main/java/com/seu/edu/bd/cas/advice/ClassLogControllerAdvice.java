package com.seu.edu.bd.cas.advice;

import com.seu.edu.bd.cas.exeption.ResourceNotFoundExption;
import com.seu.edu.bd.cas.exeption.SectionNOtFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ClassLogControllerAdvice {


    @ExceptionHandler(value = SectionNOtFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleRecourseNotFoundAception(SectionNOtFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }
    @ExceptionHandler(value = ResourceNotFoundExption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleRecourseNotFoundException(ResourceNotFoundExption ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }
}
