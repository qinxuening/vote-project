package com.vote.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vote.common.dto.VoteDetail;
import com.vote.entity.VoteDetails;
import com.vote.portal.dto.VoteParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 投票 service
 * @author qinxuening
 * @date 2022/9/14 11:39
 */
public interface IVoteService extends IService<VoteDetails> {

    /**
     * 用户投票
     * @param voteParam
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    List<VoteDetail> vote(VoteParam voteParam);
}
