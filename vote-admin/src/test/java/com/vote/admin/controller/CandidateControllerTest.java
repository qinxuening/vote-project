package com.vote.admin.controller;

import com.alibaba.fastjson.JSON;
import com.vote.admin.dto.CandidateParam;
import com.vote.admin.service.ICandidateService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * 候选人模块 controller 单元测试
 * @author qinxuening
 * @date 2022/9/15 14:23
 */
@RunWith(PowerMockRunner.class)
@WebAppConfiguration
@SpringBootTest
public class CandidateControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private CandidateController candidateController;

    @Mock
    private ICandidateService iCandidateService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(candidateController).build();
    }

    @Test
    @SneakyThrows
    public void addCandidate() {
        Mockito.doNothing().when(iCandidateService).addCandidate(Mockito.any());
        CandidateParam candidateParam = new CandidateParam();
        candidateParam.setCandidateFullName("qinxuening");
        candidateParam.setCandidateNickname("qinxuening");
        candidateParam.setIdNumber("A123456(7)");
        candidateParam.setAge(30);
        candidateParam.setVotingTopicId(1);
        candidateParam.setCampaignSlogan("实现中华民族伟大复兴");
        candidateParam.setGender(1);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/vote-admin/addCandidate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(candidateParam))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
