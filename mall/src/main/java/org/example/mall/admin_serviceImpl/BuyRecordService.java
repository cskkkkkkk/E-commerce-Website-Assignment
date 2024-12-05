package org.example.mall.admin_serviceImpl;

import org.example.mall.databaseEntity.Goods;
import org.example.mall.databaseEntity.Order;
import org.example.mall.databaseEntity.User;
import org.example.mall.mapper.OrderMapper;
import org.example.mall.mapper.UserMapper;
import org.example.mall.returnEntity.AdminRecordBuy;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfAdminRecordBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyRecordService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserMapper userMapper;


    public Response<ReturnDataOfAdminRecordBuy> buy(int pageNumber, int pageSize){
        int offset = (pageNumber - 1) * pageSize;
        orderMapper.allOrders(offset,pageSize);
        ReturnDataOfAdminRecordBuy ans = new ReturnDataOfAdminRecordBuy();
        ans.setTotalCount(orderMapper.total());
        List<AdminRecordBuy> list_tmp=new ArrayList<>();
        List<Order>goods=orderMapper.allOrders(offset,pageSize);
        for(Order order:goods){
            AdminRecordBuy buy=new AdminRecordBuy();
            User user=userMapper.selectById(order.getUserID());
            buy.setLoginName(user.getUserName());
            buy.setNickName(user.getNickName());
            buy.setCreateTime(order.getCreateTime());
            buy.setOrderNo(order.getOrderNo());
            buy.setTotalPrice(order.getTotalPrice());
            list_tmp.add(buy);
        }
        ans.setList(list_tmp);
        return Response.success(ans);
    }
}
