package org.example.mall.admin_service;

import org.example.mall.admin_Entity.ReturnDataOfUsers;
import org.example.mall.returnEntity.Response;

public interface UsersManage {
    Response<ReturnDataOfUsers>getUsers();
}
