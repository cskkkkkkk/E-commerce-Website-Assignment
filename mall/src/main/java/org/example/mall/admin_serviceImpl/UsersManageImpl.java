package org.example.mall.admin_serviceImpl;

import org.example.mall.admin_Entity.ReturnDataOfUsers;

import org.example.mall.admin_mapper.UserManageMapper;
import org.example.mall.admin_service.UsersManage;
import org.example.mall.databaseEntity.User;
import org.example.mall.returnEntity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersManageImpl implements UsersManage {

    @Autowired
    UserManageMapper userManageMapper;

    @Override
    public Response<ReturnDataOfUsers> getUsers()
    {
        List<User> list=userManageMapper.selectAll();
        ReturnDataOfUsers returnDataOfUsers = new ReturnDataOfUsers();
        returnDataOfUsers.setTotalCount(list.size());
        returnDataOfUsers.setList(list);
        return Response.success(returnDataOfUsers);
    }
}
