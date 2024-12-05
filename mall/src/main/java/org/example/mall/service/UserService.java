package org.example.mall.service;

import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfNull;
import org.example.mall.returnEntity.ReturnDataOfUserLogin;
import org.example.mall.returnEntity.ReturnDataOfUserRegister;

public interface UserService {
    Response<ReturnDataOfUserLogin> login(String username, String password);
    //实现注册方法
    Response<ReturnDataOfUserRegister> register(String userName, String password,String email);

    Response<ReturnDataOfNull>updateUserImformation(String email,String phone,String address,String userName);

    //记录浏览记录
    Response<ReturnDataOfNull>userViewRecord(int userID,int goodsId);
}
