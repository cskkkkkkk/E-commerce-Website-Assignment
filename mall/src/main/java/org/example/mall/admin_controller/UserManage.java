package org.example.mall.admin_controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mall.admin_Entity.ReturnDataOfUsers;
import org.example.mall.admin_serviceImpl.UsersManageImpl;
import org.example.mall.returnEntity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//admin端用户管理界面
@RestController
public class UserManage {

    @Autowired
    UsersManageImpl usersManage;
    @GetMapping("/users")
    public Response<ReturnDataOfUsers>getUsers(HttpServletRequest request,
                                               HttpServletResponse response) {
        return usersManage.getUsers();
    }

}
