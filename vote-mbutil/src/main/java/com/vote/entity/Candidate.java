package com.vote.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 候选人表
 * </p>
 *
 * @author qinxuening
 * @since 2022-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("candidate")
@ApiModel(value="Candidate对象", description="候选人表")
public class Candidate implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "候选人全名")
    private String candidateFullName;

    @ApiModelProperty(value = "候选人身份证号")
    private String idNumber;

    @ApiModelProperty(value = "候选人昵称")
    private String candidateNickname;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别(1男，2女)")
    private Integer gender;

    @ApiModelProperty(value = "竞选口号")
    private String campaignSlogan;

    @ApiModelProperty(value = "添加候选人时间")
    @TableField(value = "add_time",fill = FieldFill.INSERT)
    private Date addTime;


}
