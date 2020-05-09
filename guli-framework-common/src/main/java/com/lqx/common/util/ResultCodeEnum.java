package com.lqx.common.util;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author: Bruce
 * @description:
 * @date: Created in 2020-04-23 19:48
 * @modified by:
 */
@Getter
public enum ResultCodeEnum implements Serializable {
    SUCCESS(true, 200, "成功"),
    BAD_Arithmetic(false, 5001, "算数计算错误"),
    PARAM_ERROR(false, 2001,"参数不正确"),
    UNKNOWN_REASON(false, 500, "未知错误");


    private Boolean success;
    private Integer code;
    private String message;

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.message = message;
        this.code = code;
    }
}
