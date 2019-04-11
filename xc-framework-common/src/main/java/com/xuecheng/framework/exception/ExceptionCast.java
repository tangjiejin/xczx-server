package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @description: 异常抛出类
 * @author: Mr.Tang
 * @create: 2019-04-03 15:58
 **/
public class ExceptionCast {

    //使用此静态方法抛出自定义异常
    public static void cast(ResultCode resultCode){

        throw new CustomException(resultCode);
    }
}
