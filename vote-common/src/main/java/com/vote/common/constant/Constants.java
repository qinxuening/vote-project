package com.vote.common.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 通用常量信息
 * @author qinxuening
 * @date 2022/9/13 23:31
 */
public class Constants {
    /**
     * 选举组配置标识常量
     */
    public static final String VOTE = "vote";

    /**
     * 操作选举开始或者结束标识常量
     */
    public static final String DO_ELECTION = "do_election";

    /**
     * 选举开始或者结束标识1，2
     */
    public static final List<Integer> DO_ELECTION_LIST = new ArrayList(Arrays.asList(new Integer[]{1,2}));

    /**
     * 候选人得票统计redis key 前缀
     */
    public static final String CANDIDATE_COUNT_PRE = "candidateCount:";

    /**
     * 选举开始/结束缓存key
     */
    public static final String DO_ELECTION_SYS_INFO = "do_election_sys_info";

    /**
     * 选举开始标识
     */
    public static final Integer VOTE_START = 1;

    /**
     * 选举结束标识
     */
    public static final Integer VOTE_END = 2;

    /**
     * 候选人信息，redis 对应key
     */
    public static final String CANDIDATE_INFO = "candidateInfo";

    /**
     * 邮件发送主题
     */
    public static final String SUBJECT = "第XXX竞选结果";

}
