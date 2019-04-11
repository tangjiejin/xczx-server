package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * 自定义异常类
 */
public class CustomException extends RuntimeException {

    ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        super("code:" + resultCode.code() + ",message:" + resultCode.message());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {

        return this.resultCode;
    }
}
