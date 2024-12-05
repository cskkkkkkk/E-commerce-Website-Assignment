package org.example.mall.service;


import org.example.mall.DTO.OrderManageBody;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfOrderManage;
import org.springframework.web.bind.annotation.RequestBody;

//用户订单管理的服务层
public interface OrderService {
    Response<ReturnDataOfOrderManage> getOrderbyStatus(OrderManageBody orderManageBody);
}
