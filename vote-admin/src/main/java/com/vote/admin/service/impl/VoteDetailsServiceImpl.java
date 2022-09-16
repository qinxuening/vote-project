package com.vote.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vote.admin.service.IVoteDetailsService;
import com.vote.common.api.ResultCode;
import com.vote.common.dto.VoteDetail;
import com.vote.common.exception.GlobalAsserts;
import com.vote.common.service.ICommonCacheService;
import com.vote.entity.SysSettings;
import com.vote.entity.VoteDetails;
import com.vote.mapper.VoteDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Page<VoteDetails> voteList(Integer candidateId, Integer pageSize, Integer pageNum) {
        Page<VoteDetails> page = new Page<>(pageNum,pageSize);
        QueryWrapper<VoteDetails> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<VoteDetails> lambda = wrapper.lambda();
        if(candidateId !=null){
            lambda.eq(VoteDetails::getCandidateId, candidateId);
        }
        return page(page,wrapper);
    }

    @Override
    public List<VoteDetail> getAllVoteResult() {
        SysSettings doElectionInfo = iCommonCacheService.getDoElectionInfo();
        if (StrUtil.isBlank(doElectionInfo.getFieldValue())) {
            GlobalAsserts.fail(ResultCode.ELECTION_NOT_START);
        }
        return iCommonCacheService.getAllCandidateVoteResult();
    }
}
