package com.vote.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vote.admin.service.IVoteDetailsService;
import com.vote.common.api.ResultCode;
import com.vote.common.constant.Constants;
import com.vote.common.dto.VoteDetail;
import com.vote.common.exception.GlobalAsserts;
import com.vote.common.service.ICommonCacheService;
import com.vote.common.util.CommonUtilService;
import com.vote.entity.VoteDetails;
import com.vote.entity.VotingTopic;
import com.vote.mapper.VoteDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 投票详情 实现类
 * @author qinxuening
 * @date 2022/9/13 17:07
 */
@Service
public class VoteDetailsServiceImpl extends ServiceImpl<VoteDetailsMapper, VoteDetails> implements IVoteDetailsService {
    @Autowired
    private ICommonCacheService iCommonCacheService;

    @Override
    public Page<VoteDetails> voteList(Integer candidateId, Integer votingTopicId, Integer pageSize, Integer pageNum) {
        Page<VoteDetails> page = new Page<>(pageNum,pageSize);
        QueryWrapper<VoteDetails> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<VoteDetails> lambda = wrapper.lambda();
        lambda.eq(VoteDetails::getCandidateId, candidateId).eq(VoteDetails::getVotingTopicId, votingTopicId);
        return page(page,wrapper);
    }

    @Override
    public List<VoteDetail> getAllVoteResult(Integer votingTopicId) {
        VotingTopic votingTopicResult = CommonUtilService.findVotingTopicInfo(votingTopicId);
        if (votingTopicResult != null) {
            if (Objects.equals(votingTopicResult.getStatus(), Constants.VOTE_NOT_START)) {
                GlobalAsserts.fail(ResultCode.ELECTION_NOT_START);
            } else {
                return iCommonCacheService.getAllCandidateVoteResult(votingTopicId);
            }
        }
        return new ArrayList<>();
    }
}
