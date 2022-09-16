package com.vote.admin.exception;

import com.vote.common.api.ErrorCode;

/**
 * Admin 异常枚举
 * @author qinxuening
 * @date 2022/9/13 21:25
 */
public enum AdminException implements ErrorCode {
    ADD_CANDIDATE_FAIL(1000, "添加候选人失败"),
    EXIST_THIS_CANDIDATE(1001, "候选人已存在，不可重复添加"),
    ELECTION_IS_OVER(1002, "选举已经结束，不可重复操作"),
    ELECTION_IS_DOING(1003, "选举已经开始，不可重复操作");

    private long code;
    private String message;

    private AdminException(long code, String message) {
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
