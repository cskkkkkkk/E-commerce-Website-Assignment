package org.example.mall.admin_serviceImpl;

import org.example.mall.admin_Entity.Admin;
import org.example.mall.admin_mapper.LoginMapper;
import org.example.mall.admin_service.LoginService;
import org.example.mall.config.JwtProperties;
import org.example.mall.returnEntity.Response;
import org.example.mall.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    //一些配置
    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public Response<String> login(String username, String password){
            Admin admin=loginMapper.selectByuesrname(username);
            if(admin==null){
                //无
                return Response.failure("登录失败");
            }
            if(admin.getPassword().equals(password)){
                Map<String, Object> claims = new HashMap<>();
                claims.put("adinID",admin.getId());
                String jwt = JwtUtil.generateToken(
                        jwtProperties.getAdminSecretKey(),
                        jwtProperties.getAdminTtl(),
                        claims);
                System.out.println(jwt);
                return Response.success(jwt,"200");
            }
            return Response.failure("登录失败");
    }

    @Override
    public  Response<Admin>getProfile()
    {
        Admin admin=loginMapper.selectByid(1);
        if(admin==null){
            return Response.failure("失败");
        }
        return Response.success(admin);
    }
}
