package org.example.mall.admin_Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("users")
public class User {
    @TableField("userName")
    private String loginName;

    @TableField("nickName")
    private String nickName;

    @TableField("createdAt")
    private String createTime ;
}
