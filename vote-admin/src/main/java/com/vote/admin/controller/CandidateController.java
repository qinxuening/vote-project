package com.vote.admin.controller;

import com.vote.admin.dto.CandidateParam;
import com.vote.admin.service.ICandidateService;
import com.vote.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 候选人模块 controller
 * @author qinxuening
 * @date 2022/9/13 17:12
 */
@RestController
@RequestMapping(path = "/vote-admin")
@Api(tags = "候选人模块")
public class CandidateController {
    @Autowired
    private ICandidateService iCandidateService;

    @ApiOperation(value = "添加候选人")
    @PostMapping(value = "/addCandidate")
    @ResponseBody
    public CommonResult<Boolean> addCandidate(@Valid @RequestBody CandidateParam candidateParam) {
        iCandidateService.addCandidate(candidateParam);
        return CommonResult.success();
    }
}
