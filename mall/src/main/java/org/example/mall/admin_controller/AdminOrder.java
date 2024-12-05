package org.example.mall.admin_controller;

import org.example.mall.admin_Entity.AdminOrderData;
import org.example.mall.admin_Entity.OrderRequest;
import org.example.mall.admin_serviceImpl.OrderService;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class AdminOrder {

    @Autowired
    private OrderService orderService;
    @GetMapping
    public Response<AdminOrderData>getOrderList(@RequestParam int pageNumber,
                                                @RequestParam int pageSize,
                                                @RequestParam String orderNo,
                                                @RequestParam int orderStatus){
        return orderService.getOrders(pageNumber,pageSize,orderNo,orderStatus);
    }

    // 确认订单
    @PutMapping("/checkDone")
    public Response<ReturnDataOfNull> confirmOrder(@RequestBody OrderRequest request) {
        orderService.confirmOrder(request.getIds());
        return Response.success();
    }

    // 确认发货
    @PutMapping("/checkOut")
    public Response<ReturnDataOfNull> confirmDelivery(@RequestBody OrderRequest request) {
        orderService.confirmDelivery(request.getIds());
        return Response.success();
    }

    // 订单完成
    @PutMapping("/close")
    public Response<ReturnDataOfNull> completeOrder(@RequestBody OrderRequest request) {
        orderService.completeOrder(request.getIds());
        return Response.success();
    }
}
