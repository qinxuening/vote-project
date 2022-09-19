package com.vote.admin.controller;

import com.vote.admin.dto.VotingTopicParam;
import com.vote.admin.service.IVotingTopicService;
import com.vote.common.api.CommonResult;
import com.vote.entity.VotingTopic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 投票主题（场次） controller
 * @author qinxuening
 * @date 2022/9/19 16:11
 */
@RestController
@RequestMapping(path = "/vote-admin")
@Api(tags = "投票主题（场次）")
public class VotingTopicContoller {
    @Autowired
    private IVotingTopicService iVotingTopicService;

    @ApiOperation(value = "添加投票主题（场次）")
    @PostMapping(value = "/addVotingTopic")
    @ResponseBody
    public CommonResult<Boolean> candidateVoteList(@Valid @RequestBody VotingTopicParam votingTopicParam) {
        iVotingTopicService.addVotingTopic(votingTopicParam);
        return CommonResult.success();
    }

    @ApiOperation(value = "设置选举开始或者结束")
    @PutMapping(value = "/startOrend/{type}/{votingTopicId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1： 开始；2： 结束", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "votingTopicId", value = "场次id", dataType = "Integer", required = true)
    })
    @ResponseBody
    public CommonResult<Boolean> startOrend(@PathVariable("type") Integer type, @PathVariable("votingTopicId") Integer votingTopicId) throws InterruptedException {
        iVotingTopicService.startOrend(type, votingTopicId);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取所欲选举场次")
    @GetMapping(value = "/getAllVotingTopicList")
    @ResponseBody
    public CommonResult<List<VotingTopic>> getAllVotingTopicList() {
        return CommonResult.success(iVotingTopicService.getAllVotingTopicList());
    }






}
