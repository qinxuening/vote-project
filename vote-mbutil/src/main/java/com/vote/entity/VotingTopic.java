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
 * 投票主题（场次）表
 * </p>
 *
 * @author qinxuening
 * @since 2022-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("voting_topic")
@ApiModel(value="VotingTopic对象", description="投票主题（场次）表")
public class VotingTopic implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "场次主题名称")
    private String topicName;

    @ApiModelProperty(value = "场次主题描述")
    private String topicDescribe;

    @ApiModelProperty(value = "状态（0未开始，1启用，2关闭），默认0")
    private Integer status;

    @ApiModelProperty(value = "投票开始时间")
    private Date startTime;

    @ApiModelProperty(value = "投票结束时间")
    private Date endTime;

    @ApiModelProperty(value = "添加场次时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;


}
