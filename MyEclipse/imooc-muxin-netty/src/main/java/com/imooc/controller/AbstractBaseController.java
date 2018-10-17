package com.imooc.controller;

import com.imooc.bean.ValidationException;
import org.springframework.validation.BindingResult;

public abstract class AbstractBaseController {
    protected void checkParams(BindingResult result){
        if (result.hasErrors()){
            throw new ValidationException(result.getFieldError().getDefaultMessage());
        }
    }
}
