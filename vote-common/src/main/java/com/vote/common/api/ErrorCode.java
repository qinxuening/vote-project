package com.vote.common.api;

/**
 * API错误码
 * @author qinxuening
 * @date 2022/9/12 17:24
 */
public interface ErrorCode {
    long getCode();

    String getMessage();
}
