package org.example.mall.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mall.DTO.CreateOrderBody;
import org.example.mall.DTO.PayOrderBody;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfNull;
import org.example.mall.serviceImpl.PayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
public class Pay {

    @Autowired
    PayServiceImpl payService;

    @PostMapping("/successfulOrders")
    public Response<Integer>createOrder(@RequestBody CreateOrderBody createOrderBody,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        System.out.println(createOrderBody.getCartItemIds());
        return payService.createOrder(createOrderBody);
    }

    @PutMapping("/updateInformationOrder")
    public Response<ReturnDataOfNull>updateOrder(@RequestBody PayOrderBody payOrderBody,
                                                 HttpServletResponse response,
                                                 HttpServletRequest request) {
        return payService.updateOrder(payOrderBody);
    }

}
