package org.example.mall.admin_DTO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("admin")
public class LoginBody {
String userName;
String password;
}
