package org.example.mall.admin_controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mall.admin_DTO.LoginBody;
import org.example.mall.admin_serviceImpl.LoginServiceImpl;
import org.example.mall.returnEntity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//后台管理登录
@RequestMapping("/adminUser")
@RestController
public class Login {

    @Autowired
    LoginServiceImpl loginService;

    @PostMapping("/login")
    public Response<String> login(@RequestBody LoginBody loginBody,HttpServletRequest request,
                                  HttpServletResponse response) {
        System.out.println("11111111111111111111111111111");
        System.out.println(loginBody);
        return loginService.login(loginBody.getUserName(),loginBody.getPassword());
    }
}
