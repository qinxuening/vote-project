package com.vote.common.service;

import com.vote.common.dto.VoteDetail;

import java.util.List;

/**
 * @author qinxuening
 * @date 2022/9/14 14:50
 */
public interface ICommonCacheService {
    /**
     * 获取所有候选人得票情况
     * @param votingTopicId 投票场次id
     * @return
     */
    List<VoteDetail> getAllCandidateVoteResult(Integer votingTopicId);
}
