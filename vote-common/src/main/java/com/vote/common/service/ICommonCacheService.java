package com.vote.common.service;

import com.vote.common.dto.VoteDetail;
import com.vote.entity.SysSettings;

import java.util.List;

/**
 * @author qinxuening
 * @date 2022/9/14 14:50
 */
public interface ICommonCacheService {
    /**
     * 获取选举开始结束缓存
     * @return
     */
    SysSettings getDoElectionInfo();

    /**
     * 获取所有候选人得票情况
     * @return
     */
    List<VoteDetail> getAllCandidateVoteResult();
}
