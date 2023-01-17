package com.wiki.backend.Exceptions;

import com.wiki.backend.Exceptions.Model.ApiErros;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleCustomException(CustomException ex){
        String err = ex.getMessage();
        return new ApiErros(err);
    }
}
