package org.example.mall.mapper;

import org.apache.ibatis.annotations.*;
import org.example.mall.DTO.CreateOrderBody;
import org.example.mall.DTO.PayOrderBody;

@Mapper
public interface PayMapper {
    // 插入订单
    @Insert("INSERT INTO orders (userID, orderStatus, totalPrice, cartItemIds) " +
            "VALUES (#{userID}, #{orderStatusString}, #{totalPrice}, #{cartItemIds})")
    @Options(useGeneratedKeys = true, keyProperty = "orderNo", keyColumn = "orderNo")
    int createOrder(CreateOrderBody createOrderBody);

    @Update("update orders set orderStatus=#{orderStatusString},payType=#{payType} where orderNo=#{orderNo}")
    int updateOrder(PayOrderBody payOrderBody);

    @Select("select userID from orders where orderNo=#{id}")
    int selectUserId(int id);

    @Select("select email from users where userID=#{id}")
    String selectMail(int id);
}
