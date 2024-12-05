package org.example.mall.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterBody {
    private String userName;
    private String password;
    private String email;
    private String accountType;
}
