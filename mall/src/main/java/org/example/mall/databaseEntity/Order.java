package org.example.mall.databaseEntity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@Setter
@TableName("orders")
public class Order {
    @TableId
    int orderNo;
    String createTime;
    int orderStatus;
    int userID;
    String cartItemIds;
    int payType;
    double totalPrice;
    String updatedAt;
}
