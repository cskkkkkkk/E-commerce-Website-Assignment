package org.example.mall.databaseEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//用来存储从数据库一个订单的消息数据结构
@TableName("orders")
public class OrderManageTmp {
    int orderStatus;
    String cartItemIds;
    String createTime;
}
