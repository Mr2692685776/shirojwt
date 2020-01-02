package com.newheart.shirojwt.shirojwt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author hanjie
 * @date 2019/12/30 16:47
 */
@TableName("user")
@Data
public class LoginUser {

    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;
}
