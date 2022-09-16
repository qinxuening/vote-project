package com.vote.portal.controller;

import com.vote.common.api.CommonResult;
import com.vote.common.dto.VoteDetail;
import com.vote.portal.dto.VoteParam;
import com.vote.portal.service.IVoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户投票 controller
 * @author qinxuening
 * @date 2022/9/14 11:44
 */
@RestController
@RequestMapping(path = "/vote-portal")
@Api(tags = "普通用户投票")
public class VoteController {
    @Autowired
    private IVoteService iVoteService;

    @ApiOperation(value = "给候选人投票")
    @PostMapping(value = "/vote")
    @ResponseBody
    public CommonResult<List<VoteDetail>> vote(@Validated @RequestBody VoteParam voteParam) {
        return CommonResult.success(iVoteService.vote(voteParam));
    }
}
