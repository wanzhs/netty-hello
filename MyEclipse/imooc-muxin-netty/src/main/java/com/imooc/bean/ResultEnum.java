package com.imooc.bean;

/**
 * @author:wanzhongsu
 * @description: 返回结果枚举
 * @date:2018/10/16 10:25
 */
public enum ResultEnum {
    //未知错误 code可以自定义
    UNKONW_ERROR(-1, "未知错误"),
    //成功
    SUCCESS(0, "成功"),
    //逻辑业务失败
    FAIL(1, "失败"),
    //参数验证错误
    PARAM_VALID_ERROR(101, "参数验证错误"),
    TOKEN_LOSE_ERROR(102, "token失效"),
    RSA_ERROR(103, "参数RSA错误"),
    USER_FREEZE(104, "用户账号冻结，请联系客服");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
