package com.vote.common.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 通用常量信息
 * @author qinxuening
 * @date 2022/9/13 23:31
 */
public class Constants {
    private Constants() {

    }

    /**
     * 选举开始或者结束标识1，2
     */
    public static final List<Integer> DO_ELECTION_LIST = Arrays.asList(1,2);

    /**
     * 候选人得票统计redis key 前缀
     */
    public static final String CANDIDATE_COUNT_PRE = "candidateCountPre:";

    /**
     * 选举开始标识
     */
    public static final Integer VOTE_NOT_START = 0;

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
    public static final String CANDIDATE_INFO = "candidateInfoPre:";

    /**
     * 邮件发送主题
     */
    public static final String SUBJECT = "第XXX竞选结果";

    /**
     * redis 场次前缀
     */
    public static final String VOTING_TOCPIC_PRE = "votingTopicPre:";

}
