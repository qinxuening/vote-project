package com.vote.common.util;

import com.vote.common.constant.Constants;
import com.vote.common.service.IRedisCacheService;
import com.vote.entity.VotingTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author qinxuening
 * @date 2022/9/19 17:44
 */
@Component
public class CommonUtilService {
    @Autowired
    private IRedisCacheService iRedisCacheService;

    private static CommonUtilService commonUtil;

    @PostConstruct
    public void init() {
        initStatic(this);
    }

    private static void initStatic(CommonUtilService commonUtil){
        CommonUtilService.commonUtil = commonUtil;
    }

    /**
     * 查询缓存投票场次
     * @param id
     * @return
     */
    public static VotingTopic findVotingTopicInfo(Integer id) {
        return (VotingTopic) commonUtil.iRedisCacheService.get(Constants.VOTING_TOCPIC_PRE + id);
    }
}
