package com.imooc.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ValidationException extends BaseException{
    private String detail;

    public ValidationException(String detail) {
        super(ResultEnum.PARAM_VALID_ERROR);
        this.detail = detail;
    }
}
