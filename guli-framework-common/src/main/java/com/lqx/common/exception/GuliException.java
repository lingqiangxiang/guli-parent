package com.lqx.common.exception;

/**
 * @author: Bruce
 * @description: 自定义异常类
 * @date: Created in 2020-04-26 00:04
 * @modified by:
 */

import com.lqx.common.util.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "自定义异常")
public class GuliException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;
        /**
         * 接受状态码和消息
         * @param code
         * @param message
         */
    public GuliException(Integer code, String message) {
        super(message);
        this.code=code;
    }
        /**
         * 接收枚举类型
         * @param resultCodeEnum
         */
    public GuliException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "GuliException{" +
        "message=" + this.getMessage() +
        ", code=" + code +
        '}';
    }
}
