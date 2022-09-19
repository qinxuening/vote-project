package com.vote.admin.component;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.vote.admin.dao.VoteDetailsDao;
import com.vote.common.constant.Constants;
import com.vote.common.service.ICommonCacheService;
import com.vote.common.util.MailService;
import com.vote.entity.EmailSendError;
import com.vote.entity.VoteDetails;
import com.vote.mapper.EmailSendErrorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qinxuening
 * @date 2022/9/15 0:40
 */
@Slf4j
@Component
public class AsyncService {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private ICommonCacheService iCommonCacheService;

    @Autowired
    private MailService mailService;

    @Autowired
    private VoteDetailsDao voteDetailsDao;

    @Autowired
    private EmailSendErrorMapper emailSendErrorMapper;
    /**
     * 异步发送邮件,指定线程池
     */
    @Async("threadPoolTaskExecutor")
    public void sendEmail(Integer votingTopicId) throws InterruptedException {
        log.info("===========================开始发送选举投票结果邮件===============================");
        // 获取第一条数据，取第一个id
        VoteDetails voteDetails = voteDetailsDao.getFirstVoteDetails(votingTopicId);
        if (voteDetails != null && voteDetails.getId() != null) {
            // 发送第一条数据
            try {
                mailService.sendSimpleMail(from, voteDetails.getEmail(), Constants.SUBJECT, JSON.toJSONString(iCommonCacheService.getAllCandidateVoteResult(votingTopicId)));
            } catch (Exception e) {
                log.error(String.format("发送邮件异常：%s , 未发送成功数据：%s , 场次 %s ", e.getMessage(), JSON.toJSONString(voteDetails), votingTopicId));
                saveSendErrorInfo(e.getMessage(), votingTopicId, JSON.toJSONString(voteDetails));
            }
            Integer lastMaxId = voteDetails.getId();
            while (lastMaxId != null) {
                Thread.sleep(1000);
                // 批量获取投票者
                List<VoteDetails> voteDetailsList = voteDetailsDao.getVoteDetailsList(lastMaxId, votingTopicId);
                if (CollUtil.isNotEmpty(voteDetailsList)) {
                    lastMaxId = voteDetailsList.get(voteDetailsList.size() - 1).getId();
                    try {
                        // 批量发送
                        mailService.sendBatchMail(from, voteDetailsList, JSON.toJSONString(iCommonCacheService.getAllCandidateVoteResult(votingTopicId)));
                    } catch (Exception e) {
                        saveSendErrorInfo(e.getMessage(), votingTopicId, JSON.toJSONString(voteDetailsList));
                        log.error(String.format("批量发送邮件异常：%s , 未发送成功数据：%s , 场次 %s ", e.getMessage(), JSON.toJSONString(voteDetailsList), votingTopicId));
                    }
                } else {
                    lastMaxId = null;
                }
                log.info(String.format("当前发送lastMaxId： %s ,场次 %s ", lastMaxId, votingTopicId));
            }

        }
        log.info("===========================发送选举投票结果邮件结束===============================");
    }

    /**
     * 保存邮件发送失败数据信息，方便人工处理或者后续脚本重新发送
     * @param errorMessage 发送失败信息
     * @param sendData 发送邮件
     */
    void saveSendErrorInfo(String errorMessage, Integer votingTopicId,  String sendData) {
        EmailSendError emailSendError = new EmailSendError();
        emailSendError.setResponseMessage(errorMessage);
        emailSendError.setVotingTopicId(votingTopicId);
        emailSendError.setSendEmailJson(sendData);
        emailSendErrorMapper.insert(emailSendError);
    }
}
