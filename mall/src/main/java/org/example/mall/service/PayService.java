package org.example.mall.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.example.mall.DTO.CreateOrderBody;
import org.example.mall.DTO.PayOrderBody;
import org.example.mall.databaseEntity.CartItems;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfNull;

//用于用户支付订单服务，创建订单和更新订单
public interface PayService {
    Response<Integer> createOrder(CreateOrderBody createOrderBody);
    Response<ReturnDataOfNull>updateOrder(PayOrderBody payOrderBody);
}
