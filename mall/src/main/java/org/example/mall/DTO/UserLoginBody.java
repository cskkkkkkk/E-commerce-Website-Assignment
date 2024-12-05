package org.example.mall.DTO;

import com.mysql.cj.util.DnsSrv;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginBody {
    private String userName;
    private String password;
    private String accountType;
    public UserLoginBody() {
    }
}
