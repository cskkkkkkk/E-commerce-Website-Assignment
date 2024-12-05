package org.example.mall.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpdateImfomationBody {
    private String userName;
    private String email;
    private String phone;
    private String address;
}
