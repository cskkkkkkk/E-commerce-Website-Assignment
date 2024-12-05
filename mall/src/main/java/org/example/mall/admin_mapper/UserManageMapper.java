package org.example.mall.admin_mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import org.example.mall.databaseEntity.User;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface UserManageMapper {

    @Select("select * from users")
    List<User> selectAll();

    @Select("select * from users where userID=#{id}")
    User getUserById(int id);
}
