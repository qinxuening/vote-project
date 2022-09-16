package com.vote.portal.exception;

import com.vote.common.api.ErrorCode;

/**
 * portal 异常枚举
 * @author qinxuening
 * @date 2022/9/14 14:39
 */
public enum PortalException implements ErrorCode {
    YOU_HAD_VOTED(1004, "您已经投票过，不可重复投票"),
    VOTE_FAIL(1006, "投票失败，请稍后再试！");

    private long code;
    private String message;

    private PortalException(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
