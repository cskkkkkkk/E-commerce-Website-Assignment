package org.example.mall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.mall.databaseEntity.Order;
import org.example.mall.databaseEntity.OrderManageTmp;

import java.util.List;

//用户订单管理的mapper
@Mapper
public interface OrderMapper {

    //获取符合的订单数量
    @Select("select count(*) from orders where userID=#{userID} and orderStatus=#{orderStatus}")
    int totalPage(int userID,int orderStatus);


    //获取所有符合的订单
    @Select("select orderStatus,cartItemIds,createTime from orders where userID=#{userID} and orderStatus=#{orderStatus}")
    List<OrderManageTmp> getCartItemIds(int userID, int orderStatus);

    @Select("select * from orders LIMIT #{pageNumber}, #{pageSize}")
    List<Order>allOrders(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

    @Select("select count(*) from orders")
    int total();


    @Select("select * from orders where orderNo=#{id}")
    Order selectById(int id);

    @Select("select * from orders where orderStatus=#{ orderStatus} LIMIT #{pageNumber}, #{pageSize}")
    List<Order>allOrdersByStatus(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize,@Param("orderStatus") int orderStatus);

    // 更新订单状态
    @Update("UPDATE orders SET orderStatus = #{status} WHERE orderNo = #{orderId}")
    void updateOrderStatus(@Param("orderId") int orderId, @Param("status") int status);
}
