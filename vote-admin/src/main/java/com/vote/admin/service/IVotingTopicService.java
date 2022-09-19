package com.vote.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vote.admin.dto.VotingTopicParam;
import com.vote.entity.VotingTopic;

import java.util.List;

/**
 * 投票场次 service
 * @author qinxuening
 * @date 2022/9/19 16:13
 */
public interface IVotingTopicService extends IService<VotingTopic> {
    /**
     * 添加投票场次
     * @param votingTopicParam
     */
    void addVotingTopic(VotingTopicParam votingTopicParam);

    /**
     * 设置选举开始或者结束
     * @param type
     * @param votingTopicId
     */
    void startOrend(Integer type, Integer votingTopicId) throws InterruptedException;


    /**
     * 获取所有选举场次
     * @return
     */
    List<VotingTopic> getAllVotingTopicList();
}
