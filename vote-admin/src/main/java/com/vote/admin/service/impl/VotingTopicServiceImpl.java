package com.vote.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vote.admin.component.AsyncService;
import com.vote.admin.dto.VotingTopicParam;
import com.vote.admin.exception.AdminException;
import com.vote.admin.service.IVotingTopicService;
import com.vote.common.api.ResultCode;
import com.vote.common.constant.Constants;
import com.vote.common.exception.GlobalAsserts;
import com.vote.common.service.IRedisCacheService;
import com.vote.common.util.CommonUtilService;
import com.vote.entity.VotingTopic;
import com.vote.mapper.VotingTopicMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 投票场次 service 实现
 * @author qinxuening
 * @date 2022/9/19 16:14
 */
@Service
public class VotingTopicServiceImpl extends ServiceImpl<VotingTopicMapper, VotingTopic> implements IVotingTopicService {
    @Autowired
    private IRedisCacheService iRedisCacheService;

    @Autowired
    private AsyncService asyncService;

    @Override
    public void addVotingTopic(VotingTopicParam votingTopicParam) {
        VotingTopic votingTopic = new VotingTopic();
        BeanUtils.copyProperties(votingTopicParam, votingTopic);
        if (baseMapper.insert(votingTopic) <= 0) {
            GlobalAsserts.fail(AdminException.ADD_VOTING_TOPIC_FAILD);
        }
        // redis 存储场次信息
        iRedisCacheService.set(Constants.VOTING_TOCPIC_PRE + votingTopic.getId(), votingTopic);
    }

    @Override
    public void startOrend(Integer type, Integer votingTopicId) throws InterruptedException {
        // 参数校验
        if (!Constants.DO_ELECTION_LIST.contains(type)) {
            GlobalAsserts.fail(ResultCode.VALIDATE_FAILED);
        }

        VotingTopic votingTopicResult = CommonUtilService.findVotingTopicInfo(votingTopicId);
        // 检测投票场次是否存在
        if (votingTopicResult != null) {
            // 重新操作异常
            if (Objects.equals(votingTopicResult.getStatus(), Constants.VOTE_END)) {
                // 选举已经结束，不可重复操作
                GlobalAsserts.fail(AdminException.ELECTION_IS_OVER);
            } else if (Objects.equals(votingTopicResult.getStatus(), Constants.VOTE_START) && Objects.equals(type, Constants.VOTE_START)) {
                // 选举已经开始，不可重复操作
                GlobalAsserts.fail(AdminException.ELECTION_IS_DOING);
            }

            if (Objects.equals(type, Constants.VOTE_START)) {
                votingTopicResult.setStartTime(new Date());
            } else {
                votingTopicResult.setEndTime(new Date());
            }
            votingTopicResult.setStatus(type);
            if (!this.updateById(votingTopicResult))
                GlobalAsserts.fail(ResultCode.FAILED);
            // 更新开始/结束选举状态以及时间
            iRedisCacheService.set(Constants.VOTING_TOCPIC_PRE + votingTopicResult.getId(), votingTopicResult);
            // 选举结束，邮件发送
            if (Objects.equals(type, Constants.VOTE_END)) {
                // 异步执行邮件发送，使用线程池
                asyncService.sendEmail(votingTopicResult.getId());
            }
        } else {
            GlobalAsserts.fail(ResultCode.NOT_EXSIT_VOTING_TOPIC);
        }
    }

    @Override
    public List<VotingTopic> getAllVotingTopicList() {
        return baseMapper.selectList(null);
    }
}
