package com.vote.common.exception;

import com.vote.common.api.ErrorCode;

/**
 * 全局断言处理类，用于抛出各种API异常
 * @author qinxuening
 * @date 2022/9/12 23:05
 */
public class GlobalAsserts {
    private GlobalAsserts() {

    }
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(ErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
