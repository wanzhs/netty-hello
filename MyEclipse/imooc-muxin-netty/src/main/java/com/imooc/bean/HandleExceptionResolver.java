package com.imooc.bean;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleExceptionResolver {
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e) {
        if (e instanceof ValidationException) {
            ValidationException validException = (ValidationException) e;
            return Result.custom().code(validException.getCode())
                    .message(validException.getDetail() + "," + validException.getMessage());
        } else if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            return Result.custom().code(businessException.getCode())
                    .message(businessException.getMessage() + "," + businessException.getDetail());
        }
        return Result.unkonw().message(e.getMessage());
    }
}
