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
 * 
 * </p>
 *
 * @author qinxuening
 * @since 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("email_send_error")
@ApiModel(value="EmailSendError对象", description="")
public class EmailSendError implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "失败原因")
    private String responseMessage;

    @ApiModelProperty(value = "发送邮箱数据")
    private String sendEmailJson;

    @ApiModelProperty(value = "发送时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;


}
