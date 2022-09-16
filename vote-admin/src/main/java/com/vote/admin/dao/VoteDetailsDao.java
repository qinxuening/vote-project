package com.vote.admin.dao;

import com.vote.entity.VoteDetails;

import java.util.List;

/**
 * @author qinxuening
 * @date 2022/9/15 9:51
 */
public interface VoteDetailsDao {
    /**
     * 查询第一条投票记录
     * @return
     */
    VoteDetails getFirstVoteDetails();

    /**
     * 批次获取投票客户，用于发送邮件
     * @param LastMaxId
     * @return
     */
    List<VoteDetails> getVoteDetailsList(Integer LastMaxId);
}
