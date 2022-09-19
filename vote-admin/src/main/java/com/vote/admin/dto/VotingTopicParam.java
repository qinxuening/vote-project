package com.vote.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qinxuening
 * @date 2022/9/19 16:19
 */
@ApiModel(value = "投票场次参数")
@Data
public class VotingTopicParam {
    @NotBlank(message = "场次主题名称不能为空")
    @ApiModelProperty(value = "场次主题名称")
    private String topicName;

    @ApiModelProperty(value = "场次主题描述")
    private String topicDescribe;
}
