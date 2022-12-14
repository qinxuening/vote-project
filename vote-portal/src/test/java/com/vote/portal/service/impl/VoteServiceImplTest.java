package com.vote.portal.service.impl;

import com.vote.common.dto.VoteDetail;
import com.vote.common.exception.ApiException;
import com.vote.common.service.IRedisCacheService;
import com.vote.common.util.CommonUtilService;
import com.vote.entity.VoteDetails;
import com.vote.entity.VotingTopic;
import com.vote.portal.dto.VoteParam;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * 投票 service 实现类 单元测试
 * @author qinxuening
 * @date 2022/9/15 18:17
 */
@SpringBootTest
@WebAppConfiguration
@RunWith(PowerMockRunner.class)
@PrepareForTest({CommonUtilService.class})
public class VoteServiceImplTest {
    @InjectMocks
    private VoteServiceImpl voteService;

    @Mock
    private IRedisCacheService iRedisCacheService;

    @Before
    public void setUp() {
        //初始化Mockito或者使用@RunWith(MockitoJunitRunner.class)
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ApiException.class)
    public void vote() {
        PowerMockito.mockStatic(CommonUtilService.class);
        Mockito.when(CommonUtilService.findVotingTopicInfo(Mockito.anyInt())).thenReturn(new VotingTopic());
        VoteDetails voteDetails = new VoteDetails();
        voteDetails.setCandidateId(2);
        voteDetails.setEmail("22424555@qq.com");
        voteDetails.setId(3);
        voteDetails.setIdNumber("A123456(7)");
        Mockito.when(iRedisCacheService.incr(Mockito.anyString(), Mockito.anyInt())).thenReturn(1L);
        List<VoteDetail> result = voteService.vote(new VoteParam());
        assertNotNull(result);
    }
}
