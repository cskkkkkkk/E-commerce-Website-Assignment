package org.example.mall.admin_controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mall.admin_Entity.Admin;
import org.example.mall.admin_service.LoginService;
import org.example.mall.admin_serviceImpl.LoginServiceImpl;
import org.example.mall.returnEntity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminUser")
public class AdminUser {
    @Autowired
    LoginServiceImpl loginService;
    @GetMapping("/profile")
    public Response<Admin>getProfile(HttpServletRequest request,
                                     HttpServletResponse response)
    {
        return loginService.getProfile();
    }
}
