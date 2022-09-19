package com.vote.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vote.common.constant.Constants;
import com.vote.common.dto.VoteDetail;
import com.vote.common.service.ICommonCacheService;
import com.vote.common.service.IRedisCacheService;
import com.vote.entity.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author qinxuening
 * @date 2022/9/14 14:51
 */
@Service
public class ICommonCacheServiceImpl implements ICommonCacheService {

    @Autowired
    private IRedisCacheService iRedisCacheService;

    @Override
    public List<VoteDetail> getAllCandidateVoteResult(Integer votingTopicId) {
        Set<Object> candidateSet = iRedisCacheService.sMembers(Constants.CANDIDATE_INFO + votingTopicId);
        List<VoteDetail> voteDetailList = new ArrayList<>();
        candidateSet.forEach(b -> {
            Candidate candidate = (Candidate) b;
            VoteDetail voteDetail = new VoteDetail();
            voteDetail.setId(candidate.getId());
            voteDetail.setVotingTopicId(votingTopicId);
            voteDetail.setCandidateFullName(candidate.getCandidateFullName());
            voteDetail.setVotesCount((Integer) iRedisCacheService.get(Constants.CANDIDATE_COUNT_PRE + votingTopicId + candidate.getId()));
            voteDetailList.add(voteDetail);
        });
        // 排序,排名
        Collections.sort(voteDetailList, (e1, e2) -> {
            if (e1.getVotesCount().equals(e2.getVotesCount())) {
                return 0;
            } else if(e1.getVotesCount() > e2.getVotesCount()) {
                return -1;
            } else {
                return 1;
            }
        });
        AtomicReference<Integer> i = new AtomicReference<>(1);
        voteDetailList.forEach(voteDetail -> voteDetail.setRank(i.getAndSet(i.get() + 1)));
        return voteDetailList;
    }
}
