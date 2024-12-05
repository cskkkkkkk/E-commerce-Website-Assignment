package org.example.mall.admin_Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("admin")
public class Admin {
    @TableId
    int id;
    String nickName;
    String loginName;
    String password;
}
