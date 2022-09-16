package com.vote.portal.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vote.common.api.ResultCode;
import com.vote.common.constant.Constants;
import com.vote.common.dto.VoteDetail;
import com.vote.common.exception.GlobalAsserts;
import com.vote.common.service.ICommonCacheService;
import com.vote.common.service.IRedisCacheService;
import com.vote.entity.Candidate;
import com.vote.entity.SysSettings;
import com.vote.entity.VoteDetails;
import com.vote.mapper.VoteDetailsMapper;
import com.vote.portal.dto.VoteParam;
import com.vote.portal.exception.PortalException;
import com.vote.portal.service.IVoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 投票 service 实现类
 * @author qinxuening
 * @date 2022/9/14 11:40
 */
@Service
public class VoteServiceImpl  extends ServiceImpl<VoteDetailsMapper, VoteDetails> implements IVoteService {
    @Autowired
    private ICommonCacheService iCommonCacheService;

    @Autowired
    private IRedisCacheService iRedisCacheService;

    @Override
    public List<VoteDetail> vote(VoteParam voteParam) {
        SysSettings doElectionInfo = iCommonCacheService.getDoElectionInfo();
        // 判断选举是否开始
        if (StrUtil.isBlank(doElectionInfo.getFieldValue())) {
            GlobalAsserts.fail(ResultCode.ELECTION_NOT_START);
        }
        // 判断候选人是否存在
        if (getAllCandidateIds() !=null && !getAllCandidateIds().contains(voteParam.getCandidateId())) {
            GlobalAsserts.fail(PortalException.CANDIDATE_NOT_EXIST);
        }
        // 选举已结束，不可再投票
        if (Objects.equals(Integer.valueOf(doElectionInfo.getFieldValue()), Constants.VOTE_END) ) {
            GlobalAsserts.fail(ResultCode.ELECTION_END);
        }
        // 已经投票过，不可重复投票
        VoteDetails result = this.getOne(Wrappers.<VoteDetails>lambdaQuery().eq(VoteDetails::getIdNumber, voteParam.getIdNumber()));
        if (result != null) {
            GlobalAsserts.fail(PortalException.YOU_HAD_VOTED);
        }
        VoteDetails voteDetails = new VoteDetails();
        BeanUtils.copyProperties(voteParam, voteDetails);
        if (!this.save(voteDetails)) {
            GlobalAsserts.fail(PortalException.VOTE_FAIL);
        }
        // 累加候选人得票 + 1
        iRedisCacheService.incr(Constants.CANDIDATE_COUNT_PRE + voteParam.getCandidateId(), 1);

        // 获取所有候选人得票情况
        return iCommonCacheService.getAllCandidateVoteResult();
    }

    public List<Integer> getAllCandidateIds() {
        Set<Object> candidateSet = iRedisCacheService.sMembers(Constants.CANDIDATE_INFO);
        List<Integer> voteIdList = new ArrayList<>();
        candidateSet.forEach(b -> voteIdList.add(((Candidate) b).getId()));
        return voteIdList;
    }
}
