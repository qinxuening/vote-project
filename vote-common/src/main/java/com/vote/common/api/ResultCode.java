package com.vote.common.api;

/**
 * @author qinxuening
 * @date 2022/9/12 17:24
 */
public enum ResultCode implements ErrorCode{
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(1008, "参数检验失败"),
    NOT_FOUND(404, "资源，服务未找到"),
    ELECTION_NOT_START(1005, "选举未开始"),
    ELECTION_END(1007, "选举已经结束，无法再投票");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
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
