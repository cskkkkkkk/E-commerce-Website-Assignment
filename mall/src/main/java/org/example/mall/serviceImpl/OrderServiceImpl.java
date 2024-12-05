package org.example.mall.serviceImpl;

import org.example.mall.DTO.OrderManageBody;
import org.example.mall.databaseEntity.Goods;
import org.example.mall.databaseEntity.OrderManageTmp;
import org.example.mall.mapper.OrderMapper;
import org.example.mall.mapper.ProductMapper;
import org.example.mall.returnEntity.OrderManageSon;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfOrderManage;
import org.example.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "pagination")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    private int numberPerPage;

    @Autowired
    ProductMapper productMapper;

    @Override
    public Response<ReturnDataOfOrderManage> getOrderbyStatus(OrderManageBody orderManageBody){
        ReturnDataOfOrderManage ans= new ReturnDataOfOrderManage();
        int userID=orderManageBody.getUserID();
        int status=Integer.parseInt(orderManageBody.getStatus());
        //设置第一个返回的属性
        ans.setTotalPage(orderMapper.totalPage(userID,status));
        //根据status和userID将cartItemIds给取出来
        List<OrderManageTmp> list=new ArrayList<>();
        list.addAll(orderMapper.getCartItemIds(userID,status));

        List<OrderManageSon> per_order=new ArrayList<>();
        //遍历列表，转化成商品id列表
        for(OrderManageTmp item:list)
        {
            String[] ids = item.getCartItemIds().split(",");
            OrderManageSon order=new OrderManageSon();
            order.setCreateTime(item.getCreateTime());
            order.setOrderStatusString(item.getOrderStatus());
            List<Goods> goods=new ArrayList<>();
            for(String idStr:ids)
            {   Goods good=new Goods();
                Integer id=Integer.parseInt(idStr);
                good=productMapper.selectGood(id);
                goods.add(good);
            }
            order.setGoodsInOrder(goods);
            //一个订单的数据保存完毕
            per_order.add(order);
        }
        ans.setList(per_order);
        return Response.success(ans);
    }
}
