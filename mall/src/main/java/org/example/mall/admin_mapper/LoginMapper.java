package org.example.mall.admin_mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.mall.admin_Entity.Admin;

@Mapper
public interface LoginMapper extends BaseMapper<Admin> {


    // 使用 MyBatis-Plus 提供的条件查询功能
    //使用 default 可以让接口不强制要求实现类实现该方法。
    //，只有在需要自定义 SQL 查询时才会添加 default 方法
    default Admin selectByUsername(String username){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginName", username); // 使用等于条件查询
        return this.selectOne(queryWrapper); // 返回查询结果
    }

    @Select("select nickName,loginName,password,id from admin where loginName=#{username}")
    Admin selectByuesrname(String username);

    @Select("select nickName,loginName,password,id from admin where id=#{id}")
    Admin selectByid(int id);
}
