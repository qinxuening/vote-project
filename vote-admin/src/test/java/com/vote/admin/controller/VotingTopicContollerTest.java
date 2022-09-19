package com.vote.admin.controller;

import com.alibaba.fastjson.JSON;
import com.vote.admin.dto.VotingTopicParam;
import com.vote.admin.service.IVotingTopicService;
import com.vote.entity.VotingTopic;
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

/**
 * 投票主题（场次） controller 单元测试
 * @author qinxuening
 * @date 2022/9/19 21:46
 */
@RunWith(PowerMockRunner.class)
@WebAppConfiguration
@SpringBootTest
public class VotingTopicContollerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private VotingTopicContoller votingTopicContoller;

    @Mock
    private IVotingTopicService iVotingTopicService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(votingTopicContoller).build();
    }

    @Test
    @SneakyThrows
    public void addVotingTopic() {
        VotingTopicParam votingTopicParam = new VotingTopicParam();
        votingTopicParam.setTopicName("场次主题名称");
        votingTopicParam.setTopicDescribe("场次主题名称描述");
        Mockito.doNothing().when(iVotingTopicService).addVotingTopic(votingTopicParam);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/vote-admin/addVotingTopic")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(votingTopicParam))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
