package com.vote.portal.controller;

import com.alibaba.fastjson.JSON;
import com.vote.common.dto.VoteDetail;
import com.vote.portal.dto.VoteParam;
import com.vote.portal.service.IVoteService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

/**
 *  用户投票 controller 单元测试
 * @author qinxuening
 * @date 2022/9/15 17:33
 */
@RunWith(PowerMockRunner.class)
@WebAppConfiguration
@SpringBootTest
public class VoteControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private VoteController voteController;

    @Mock
    private IVoteService iVoteService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(voteController).build();
    }

    @Test
    @SneakyThrows
    public void vote() {
        List<VoteDetail> voteDetailList = new ArrayList<>();
        VoteParam voteParam = new VoteParam();
        voteParam.setCandidateId(2);
        voteParam.setVotingTopicId(1);
        voteParam.setEmail("234234234@qq.com");
        voteParam.setIdNumber("A123456(0)");
        Mockito.when(iVoteService.vote(voteParam)).thenReturn(voteDetailList);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/vote-portal/vote")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(voteParam))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
