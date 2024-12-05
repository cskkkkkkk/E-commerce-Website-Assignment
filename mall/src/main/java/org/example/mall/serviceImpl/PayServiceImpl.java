package org.example.mall.serviceImpl;

import org.example.mall.Component.EmailComponent;
import org.example.mall.DTO.CreateOrderBody;
import org.example.mall.DTO.PayOrderBody;
import org.example.mall.mapper.PayMapper;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfNull;
import org.example.mall.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayMapper payMapper;

    @Autowired
    private EmailComponent emailComponent;
    @Override
    public Response<Integer> createOrder(CreateOrderBody createOrderBody)
    {
        int row=payMapper.createOrder(createOrderBody);
        if(row==0)
        {
            return Response.failure("创建订单失败");
        }
        return Response.success(createOrderBody.getOrderNo());
    }
    @Override
    public  Response<ReturnDataOfNull>updateOrder(PayOrderBody payOrderBody){
        int userID=payMapper.selectUserId(payOrderBody.getOrderNo());
        String email=payMapper.selectMail(userID);
        emailComponent.sendGeneralEmail("商城订单通知","您的订单我们已经收到，尽快为您发货哦，谢谢您的光临。",email);
        payMapper.updateOrder(payOrderBody);
        return  Response.success("支付成功");
    }
}
