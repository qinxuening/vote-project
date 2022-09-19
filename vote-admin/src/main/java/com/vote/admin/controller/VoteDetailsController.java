package com.vote.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.admin.service.IVoteDetailsService;
import com.vote.common.api.CommonPage;
import com.vote.common.api.CommonResult;
import com.vote.common.dto.VoteDetail;
import com.vote.common.service.ICommonCacheService;
import com.vote.entity.VoteDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 投票情况模块 controller
 * @author qinxuening
 * @date 2022/9/14 18:41
 */
@RestController
@RequestMapping(path = "/vote-admin")
@Api(tags = "投票情况模块")
public class VoteDetailsController {
    @Autowired
    private ICommonCacheService iCommonCacheService;

    @Autowired
    private IVoteDetailsService iVoteDetailsService;

    @ApiOperation(value = "获取实时得票情况")
    @GetMapping(value = "/candidateVoteList/{votingTopicId}")
    @ApiImplicitParam(name = "votingTopicId", value = "投票场次id", required = true, dataType = "Integer")
    @ResponseBody
    public CommonResult<List<VoteDetail>> candidateVoteList(@PathVariable(value = "votingTopicId") Integer votingTopicId) {
        return CommonResult.success(iCommonCacheService.getAllCandidateVoteResult(votingTopicId));
    }

    @ApiOperation("查看投给该候选人用户列表列表")
    @GetMapping(value = "/voteForCandidateUsersList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "candidateId", value = "候选人id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "votingTopicId", value = "选举场次id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "Integer", required = true, defaultValue = "10"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer", required = true, defaultValue = "1")
    })
    public CommonResult<CommonPage<VoteDetails>> voteForCandidateUsersList(@RequestParam() Integer candidateId,
                                                      @RequestParam() Integer votingTopicId,
                                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<VoteDetails> voteDetailsPage = iVoteDetailsService.voteList(candidateId, votingTopicId, pageSize, pageNum);
        return CommonResult.success(CommonPage.page(voteDetailsPage));
    }

    @ApiOperation(value = "查询选举最终结果")
    @GetMapping(value = "/VoteResult/{votingTopicId}")
    @ApiImplicitParam(name = "votingTopicId", value = "投票场次id", required = true, dataType = "Integer")
    @ResponseBody
    public CommonResult<List<VoteDetail>> voteResult(@PathVariable(value = "votingTopicId") Integer votingTopicId) {
        return CommonResult.success(iVoteDetailsService.getAllVoteResult(votingTopicId));
    }
}
