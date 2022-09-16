package com.vote.admin.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vote.common.dto.VoteDetail;
import com.vote.entity.VoteDetails;

import java.util.List;

/**
 * 投票详情 service
 * @author qinxuening
 * @date 2022/9/13 16:37
 */
public interface IVoteDetailsService extends IService<VoteDetails>{
    /**
     * 查看投给该候选⼈的⽤⼾列表
     * @param candidateId 候选人id
     * @param pageSize 每页大小
     * @param pageNum 页码
     * @return
     */
    Page<VoteDetails> voteList(Integer candidateId, Integer pageSize, Integer pageNum);

    /**
     * 查询选举最终结果
     * @return
     */
    List<VoteDetail> getAllVoteResult();
}
