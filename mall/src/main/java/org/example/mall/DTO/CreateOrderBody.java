package org.example.mall.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("orders")
//创建订单时接收前端的数据
public class CreateOrderBody {
    @TableId
    int orderNo;
    int userID;
    @TableField("orderStatus")
    int orderStatusString;
    double totalPrice;
    String cartItemIds;
}
