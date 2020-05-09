package com.lqx.common.handler;

import com.lqx.common.exception.GuliException;
import com.lqx.common.util.ExceptionUtil;
import com.lqx.common.util.ResponseResault;
import com.lqx.common.util.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Bruce
 * @description: 统一异常处理类
 * @date: Created in 2020-04-25 23:19
 * @modified by:
 */
@ControllerAdvice
@Slf4j //异常信息记录到日志 配合util下的ExceptionUtil使用
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResault error(Exception e){
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return ResponseResault.error();
    }

    /**
     * 特定异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResponseResault error(ArithmeticException e){
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return ResponseResault.setResult(ResultCodeEnum.BAD_Arithmetic);
    }

    /**
     * 自定义异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public ResponseResault error(GuliException e){
        e.printStackTrace();
        return ResponseResault.error().message(e.getMessage()).code(e.getCode());
    }
}
