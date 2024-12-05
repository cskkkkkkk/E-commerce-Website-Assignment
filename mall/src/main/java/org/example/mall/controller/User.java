package org.example.mall.controller;


import org.example.mall.DTO.UserLoginBody;
import org.example.mall.DTO.UserRegisterBody;
import org.example.mall.DTO.UserUpdateImfomationBody;
import org.example.mall.DTO.UserViewBody;
import org.example.mall.returnEntity.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mall.returnEntity.ReturnDataOfNull;
import org.example.mall.returnEntity.ReturnDataOfUserLogin;
import org.example.mall.returnEntity.ReturnDataOfUserRegister;
import org.example.mall.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class User {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    public Response<ReturnDataOfUserLogin> login(@RequestBody UserLoginBody userLoginBody,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) {
        String userName=userLoginBody.getUserName();
        String password=userLoginBody.getPassword();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() != null) {
//            return Response.success();
//        }
        //如果没有jwt信息，那就调用服务
        return userService.login(userName,password);
    }

    @PostMapping("/register")
    public Response<ReturnDataOfUserRegister> register(@RequestBody UserRegisterBody userRegisterBody,
                                                       HttpServletRequest request,
                                                       HttpServletResponse response
    ) {
        String userName=userRegisterBody.getUserName();
        String password=userRegisterBody.getPassword();
        String email=userRegisterBody.getEmail();
        //因为前端已经验证过了，所以我这里不做验证。

        return userService.register(userName,password,email);

    }

    @PutMapping("/update_imfomation")
    public Response<ReturnDataOfNull> update_imfomation(@RequestBody UserUpdateImfomationBody userUpdateImfomationBody)
    {
        return userService.updateUserImformation(userUpdateImfomationBody.getEmail(),
                userUpdateImfomationBody.getPhone(),
                userUpdateImfomationBody.getAddress(),
                userUpdateImfomationBody.getUserName());
    }


    //下面这个接口是记录用户浏览记录的，只要点击了商品都会被记录下来
    @PostMapping("/view/record")
    public Response<ReturnDataOfNull>userViewRecord(@RequestBody UserViewBody userViewBody
                                                    , HttpServletResponse response,
                                                    HttpServletRequest request)
    {
        return userService.userViewRecord(userViewBody.getUserID(),userViewBody.getGoodsId());
    }
}
