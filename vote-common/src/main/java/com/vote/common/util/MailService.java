package com.vote.common.util;

import com.vote.common.constant.Constants;
import com.vote.entity.VoteDetails;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮箱发送工具类
 * @author qinxuening
 * @date 2022/9/14 22:35
 */
@Service
public class MailService {
    @Resource
    private JavaMailSender javaMailSender;

    /**
     * 单条邮件发送
     * @param fromMail 邮件发送者
     * @param to 接收邮箱地址
     * @param subject 主题
     * @param content 邮件内容
     */
    public void sendSimpleMail(String fromMail, String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }

    /**
     * 批量邮件发送
     * @param fromMail 邮件发送者
     * @param voteDetailsList 批量数据
     * @param content 邮件内容
     */
    public void sendBatchMail(String fromMail, List<VoteDetails> voteDetailsList, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setSubject(Constants.SUBJECT);
        message.setText(content);
        List<String> emailList = new ArrayList();
        voteDetailsList.forEach(voteDetails -> emailList.add(voteDetails.getEmail()));
        String[] emailArray = emailList.stream().toArray(String[]::new);
        message.setTo(emailArray);
        javaMailSender.send(message);
    }

}
