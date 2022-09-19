package com.vote.admin.controller;

import com.vote.common.dto.VoteDetail;
import com.vote.common.service.ICommonCacheService;
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
 * 投票情况模块 controller 单元测试
 * @author qinxuening
 * @date 2022/9/15 17:10
 */
@RunWith(PowerMockRunner.class)
@WebAppConfiguration
@SpringBootTest
public class VoteDetailsControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private VoteDetailsController voteDetailsController;

    @Mock
    private ICommonCacheService iCommonCacheService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(voteDetailsController).build();
    }

    @Test
    @SneakyThrows
    public void candidateVoteList() {
        List<VoteDetail> voteDetailList = new ArrayList<>();
        Mockito.when(iCommonCacheService.getAllCandidateVoteResult(Mockito.anyInt())).thenReturn(voteDetailList);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/vote-admin/candidateVoteList/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
