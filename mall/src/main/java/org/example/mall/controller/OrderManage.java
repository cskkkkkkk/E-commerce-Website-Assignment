package org.example.mall.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mall.DTO.OrderManageBody;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfOrderManage;
import org.example.mall.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//用户界面订单管理接口层
@RestController
@RequestMapping("/orderManage")
public class OrderManage {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/getOrderbyStatus")
    public Response<ReturnDataOfOrderManage>getOrderbyStatus(@RequestBody OrderManageBody orderManageBody,
                                                             HttpServletRequest request,
                                                             HttpServletResponse response) {
        return orderService.getOrderbyStatus(orderManageBody);
    }
}
