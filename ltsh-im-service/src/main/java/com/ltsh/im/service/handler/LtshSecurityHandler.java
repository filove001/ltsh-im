package com.ltsh.im.service.handler;


import com.ltsh.im.common.entity.Result;

/**
 * 安全校验,接收到参数调用
 * @param <T>
 */
public interface LtshSecurityHandler<T> extends ServiceBaseHandler {
    public Result verify(T reqMsg);
}
