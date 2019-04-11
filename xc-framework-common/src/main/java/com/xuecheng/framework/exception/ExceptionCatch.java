package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @description: 异常捕捉类
 * @author: Mr.Tang
 * @create: 2019-04-03 15:59
 **/
@ControllerAdvice
public class ExceptionCatch {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionCatch.class);


    /**
     * 自定义异常捕获
     *
     * @param ce
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException ce) {

        ResultCode resultCode = ce.getResultCode();

        logger.error("错误编码:" + resultCode.code() + ",错误信息:" + resultCode.message());

        return new ResponseResult(resultCode);

    }


    /**
     * 未知异常捕获
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception exception) {
        //记录日志
        logger.error("未知异常:" + exception.getMessage(), exception);
        return new ResponseResult(new ResultCode() {
            @Override
            public boolean success() {
                return false;
            }

            @Override
            public int code() {
                return 99999;
            }

            @Override
            public String message() {
                return "未知异常";
            }
        });
    }


}
