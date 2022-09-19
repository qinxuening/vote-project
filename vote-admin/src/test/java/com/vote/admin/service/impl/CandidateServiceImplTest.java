package com.vote.admin.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vote.admin.dto.CandidateParam;
import com.vote.common.exception.ApiException;
import com.vote.common.util.CommonUtilService;
import com.vote.entity.Candidate;
import com.vote.entity.VotingTopic;
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

/**
 * 候选人 service 实现类 单元测试
 * @author qinxuening
 * @date 2022/9/15 17:50
 */
@RunWith(PowerMockRunner.class)
@WebAppConfiguration
@SpringBootTest
@PrepareForTest({CommonUtilService.class})
public class CandidateServiceImplTest {
    @InjectMocks
    private CandidateServiceImpl candidateService;

    @Mock
    private BaseMapper baseMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ApiException.class)
    public void addCandidate() {
        PowerMockito.mockStatic(CommonUtilService.class);
        Mockito.when(candidateService.getOne(Mockito.any())).thenReturn(new Candidate());
        Mockito.when(baseMapper.insert(new Candidate())).thenReturn(1);
        Mockito.when(CommonUtilService.findVotingTopicInfo(1)).thenReturn(new VotingTopic());
        candidateService.addCandidate(new CandidateParam());
    }





}
