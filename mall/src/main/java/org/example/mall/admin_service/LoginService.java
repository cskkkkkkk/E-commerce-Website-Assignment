package org.example.mall.admin_service;

import org.example.mall.admin_Entity.Admin;
import org.example.mall.returnEntity.Response;

public interface LoginService {
    Response<String>login(String username, String password);

    //获取用户基本信息
    Response<Admin>getProfile();
}
