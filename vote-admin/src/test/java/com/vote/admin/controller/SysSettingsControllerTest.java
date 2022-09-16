package com.vote.admin.controller;

import com.vote.admin.service.ISysSettingsService;
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
 * 系统设置模块 controller 单元测试
 * @author qinxuening
 * @date 2022/9/15 16:49
 */
@RunWith(PowerMockRunner.class)
@WebAppConfiguration
@SpringBootTest
public class SysSettingsControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private SysSettingsController sysSettingsController;

    @Mock
    private ISysSettingsService iSysSettingsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sysSettingsController).build();
    }

    @Test
    @SneakyThrows
    public void startOrend() {
        Mockito.doNothing().when(iSysSettingsService).startOrend(1);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/vote-admin/startOrend/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

















