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
 * 投票详情表
 * </p>
 *
 * @author qinxuening
 * @since 2022-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("vote_details")
@ApiModel(value="VoteDetails对象", description="投票详情表")
public class VoteDetails implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "投票场次id")
    private Integer votingTopicId;

    @ApiModelProperty(value = "候选人id")
    private Integer candidateId;

    @ApiModelProperty(value = "投票者邮箱")
    private String email;

    @ApiModelProperty(value = "投票者身份证号")
    private String idNumber;

    @ApiModelProperty(value = "投票时间")
    @TableField(value = "vote_time",fill = FieldFill.INSERT)
    private Date voteTime;


}
