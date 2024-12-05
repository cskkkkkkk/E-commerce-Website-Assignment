package org.example.mall.admin_serviceImpl;

import org.example.mall.admin_Entity.AdminOrderData;
import org.example.mall.databaseEntity.Order;
import org.example.mall.mapper.OrderMapper;
import org.example.mall.returnEntity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    public Response<AdminOrderData>getOrders(int pageNumber,int pageSize,String orderNo,int orderStatus)
    {   AdminOrderData ans=new AdminOrderData();
        if(orderNo==null||orderNo.equals(""))
        {
            int offset=(pageNumber-1)*pageSize;
            List<Order>list_tmp=new ArrayList<>();
            int total=0;
            if(orderStatus==-6)//代表全部
            {
                list_tmp=orderMapper.allOrders(offset,pageSize);
                total=orderMapper.total();
            }
            else {
                list_tmp = orderMapper.allOrdersByStatus(offset, pageSize, orderStatus);
                total=list_tmp.size();
            }
            ans.setTotalCount(total);
            ans.setList(list_tmp);
            return Response.success(ans);
        }

        Order order=orderMapper.selectById(Integer.parseInt(orderNo));
        if(order==null)
        {
            return Response.failure("不存在此订单号");
        }
        List<Order>list_tmp=new ArrayList<Order>();
        list_tmp.add(order);
        ans.setList(list_tmp);
        ans.setTotalCount(1);
        return Response.success(ans);
    }

    // 确认订单
    public void confirmOrder(List<Integer> orderIds) {
        for (int orderId : orderIds) {
            orderMapper.updateOrderStatus(orderId, 2); // 假设 2 是确认订单的状态
        }
    }

    // 确认发货
    public void confirmDelivery(List<Integer> orderIds) {
        for (int orderId : orderIds) {
            orderMapper.updateOrderStatus(orderId, 3); // 假设 3 是发货状态
        }
    }

    // 完成订单
    public void completeOrder(List<Integer> orderIds) {
        for (int orderId : orderIds) {
            orderMapper.updateOrderStatus(orderId, 4); // 假设 4 是订单完成的状态
        }
    }
}
