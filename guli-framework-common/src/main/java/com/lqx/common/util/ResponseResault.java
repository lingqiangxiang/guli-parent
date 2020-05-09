package com.lqx.common.util;

/**
 * @author: Bruce
 * @description:
 * @date: Created in 2020-04-23 20:03
 * @modified by:
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
@ApiModel(value = "全局统一返回结果")
public class ResponseResault implements Serializable {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    /**
     * 成功
     *
     * @return ResponseResault
     */
    public static ResponseResault ok() {
        ResponseResault responseResault = new ResponseResault();
        responseResault.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        responseResault.setCode(ResultCodeEnum.SUCCESS.getCode());
        responseResault.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return responseResault;
    }

    /**
     * 失败
     *
     * @return ResponseResault
     */
    public static ResponseResault error() {
        ResponseResault responseResault = new ResponseResault();
        responseResault.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        responseResault.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        responseResault.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return responseResault;
    }

    /**
     * 设置返回结果
     *
     * @return ResponseResault
     */
    public static ResponseResault setResult(ResultCodeEnum resultCodeEnum) {
        ResponseResault responseResault = new ResponseResault();
        responseResault.setSuccess(resultCodeEnum.getSuccess());
        responseResault.setCode(resultCodeEnum.getCode());
        responseResault.setMessage(resultCodeEnum.getMessage());
        return responseResault;
    }

    public ResponseResault success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResponseResault message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResponseResault code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResponseResault data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResponseResault data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
