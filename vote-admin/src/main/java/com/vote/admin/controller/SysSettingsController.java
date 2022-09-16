package com.vote.admin.controller;

import com.vote.admin.service.ISysSettingsService;
import com.vote.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 系统设置模块 controller
 * @author qinxuening
 * @date 2022/9/13 17:17
 */
@RestController
@RequestMapping(path = "/vote-admin")
@Api(tags = "系统选举设置模块")
public class SysSettingsController {
    @Autowired
    private ISysSettingsService iSysSettingsService;

    @ApiOperation(value = "设置选举开始或者结束")
    @PutMapping(value = "/startOrend/{type}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "type", value = "1： 开始；2： 结束", dataType = "Integer", required = true)
    })
    public CommonResult<Boolean> startOrend(@PathVariable("type") Integer type) throws InterruptedException {
        iSysSettingsService.startOrend(type);
        return CommonResult.success();
    }

}
