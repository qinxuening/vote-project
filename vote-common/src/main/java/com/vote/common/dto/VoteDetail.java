package com.vote.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 投票结果
 * @author qinxuening
 * @date 2022/9/14 11:53
 */
@Data
public class VoteDetail {
    @ApiModelProperty(value = "候选人编号")
    private Integer id;

    @ApiModelProperty(value = "投票场次")
    private Integer votingTopicId;

    @ApiModelProperty(value = "候选人")
    private String candidateFullName;

    @ApiModelProperty(value = "得票数")
    private Integer votesCount;

    @ApiModelProperty(value = "排名")
    private Integer rank;
}