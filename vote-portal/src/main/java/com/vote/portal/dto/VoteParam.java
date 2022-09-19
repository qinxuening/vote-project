package com.vote.portal.dto;

import com.vote.common.validator.HkIDValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author qinxuening
 * @date 2022/9/14 13:56
 */
@ApiModel(value = "投票填写参数")
@Data
public class VoteParam {
    @NotNull(message = "投票场次id不能为空")
    @ApiModelProperty(value = "投票场次id" , required = true)
    private Integer votingTopicId;

    @NotNull(message = "候选人id")
    @ApiModelProperty(value = "候选人id")
    private Integer candidateId;

    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "投票者邮箱")
    private String email;

    @HkIDValidator(message = "身份证号格式不正确")
    @ApiModelProperty(value = "投票者身份证号")
    private String idNumber;
}
