package org.example.mall.databaseEntity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("users")  // 这里的 "user" 是数据库中的表名
@Getter
@Setter
public class User {
    @TableId
    private String userID;
    private String email;
    private String phone;
    private String password;
    private String address;
    private String userName;
    private String nickName;
    private int lockedFlag;
    private int isDeleted;
    private String createdAt;
    private String updatedAt;


}
