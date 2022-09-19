package com.vote.admin.dto;

import com.vote.common.validator.HkIDValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author qinxuening
 * @date 2022/9/13 17:46
 */
@ApiModel(value = "添加候选人参数")
@Data
public class CandidateParam {
    @NotNull(message = "投票场次id不能为空")
    @ApiModelProperty(value = "投票场次id" , required = true)
    private Integer votingTopicId;

    @NotBlank(message = "候选人全名不能为空")
    @ApiModelProperty(value = "候选人全名" , required = true)
    private String candidateFullName;

    @HkIDValidator(message = "身份证号格式不正确")
    @ApiModelProperty(value = "候选人身份证号", required = true)
    private String idNumber;

    @ApiModelProperty(value = "候选人昵称")
    private String candidateNickname;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别(1男，2女)")
    private Integer gender;

    @ApiModelProperty(value = "竞选口号")
    private String campaignSlogan;
}
