package com.zty.study.repo.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @author lsy
 * @since 2020-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User extends Model<User> {


    private static final long serialVersionUID=1L;

    /**
     * 赋予初值的方法   如果你不用这几个业务字段的话可以不加这个方法，但是记得再模板里把这个defv方法给删除
     * @return
     */
    public User defv() {
        setStatus(1);
        setType(1);
        setCreateTime(LocalDateTime.now());
        setUpdateTime(getCreateTime());
        return this;
    }

    @ApiModelProperty(value = "主键")
        @TableId(value = "id", type = IdType.AUTO)
        private Long id;

    @ApiModelProperty(value = "业务类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "逻辑删   0删除  1正常")
    @TableField("status")
    @TableLogic
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "登录密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户注册IP")
    @TableField("reg_ip")
    private String regIp;

    @ApiModelProperty(value = "用户登录IP")
    @TableField("login_ip")
    private String loginIp;

    @ApiModelProperty(value = "用户登录时间")
    @TableField("login_time")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "用户下线时间")
    @TableField("out_time")
    private LocalDateTime outTime;


    @Override
    protected Serializable pkVal(){
            return this.id;
        }

}
