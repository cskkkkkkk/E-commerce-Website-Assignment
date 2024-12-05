package org.example.mall.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.mall.config.JwtProperties;
import org.example.mall.databaseEntity.User;
import org.example.mall.mapper.UserMapper;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfNull;
import org.example.mall.returnEntity.ReturnDataOfUserLogin;
import org.example.mall.returnEntity.ReturnDataOfUserRegister;
import org.example.mall.service.UserService;
import org.example.mall.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public Response<ReturnDataOfUserLogin> login(String userName, String password)
    {
        User user=userMapper.findByUsername(userName);
        if(user==null)//说明没有这个人
        {
            return Response.failure("用户未注册");
        }
        else if(user.getPassword().equals(password))//判断密码是否相等
        {//如果相等那就生成一个jwt一起返回
            //登录成功后，生成jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("userID",user.getUserID());
            String jwt = JwtUtil.generateToken(
                    jwtProperties.getAdminSecretKey(),
                    jwtProperties.getAdminTtl(),
                    claims);

//            String jwt= JwtUtil.generateToken(user.getUserID());
            ReturnDataOfUserLogin obj=new ReturnDataOfUserLogin(
                    jwt,
                    user.getUserID(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getAddress());
            return Response.success(obj);
        }
        return Response.failure("密码输入错误");
    }

    @Override
    public Response<ReturnDataOfUserRegister> register(String userName, String password, String email){
        int row=userMapper.selectCountByUserName(userName);
        int row1=userMapper.selectCountByEmail(email);
        if(row>0)
        {
            return Response.failure("用户已存在，注册失败","0");
        }
        if(row1>0) {
            return Response.failure("邮箱已存在，注册失败");
        }

        userMapper.insert(userName,password,email);
        return Response.success();
    }

    @Override
    public Response<ReturnDataOfNull>updateUserImformation(String email,String phone,String address,String userName)
    {
        userMapper.updateUserImformation(email,phone,address,userName);
        return Response.success();
    }


    @Override
    public Response<ReturnDataOfNull>userViewRecord(int userID,int goodsId)
    {
        int row=userMapper.insertUserViewRecord(userID,goodsId);
        if(row>0)
        {
            return Response.success();
        }
        return Response.failure("服务器操作失败");
    }
}
