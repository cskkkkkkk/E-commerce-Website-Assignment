package org.example.mall.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.mall.databaseEntity.User;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfNull;

@Mapper
public interface UserMapper {
    // 使用 @Select 注解编写 SQL 查询，注意使用占位符 ? 来传入参数
    @Select("SELECT * FROM users WHERE userName = #{userName}")
    User findByUsername(String userName);

    //插入一个新用户，如果操作失败会返回0
    @Insert("insert into users(userName,email,password) values (#{userName},#{email},#{password})")
    int insert(String userName, String password,String email);

    @Select("select count(*) from users where userName=#{userName};")
    int selectCountByUserName(String userName);

    @Select("select count(*) from users where email=#{email};")
    int selectCountByEmail(String email);

    @Update("update users set email=#{email},phone=#{phone},address=#{address} where userName=#{userName}")
    int updateUserImformation(String email,String phone,String address,String userName);

    @Select("select * from users where userID=#{id}")
    User selectById(int id);
    //插入查询记录
    @Insert("insert into user_browse_history(userID,goodsId,browseTime) values(#{userID},#{goodsId}, NOW())")
    int insertUserViewRecord(int userID,int goodsId);
}
