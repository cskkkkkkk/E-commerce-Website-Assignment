package org.example.mall.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

//用户确认支付之后发送的数据
@Getter
@Setter
@TableName("orders")
public class PayOrderBody {
    @TableId
    int orderNo;
    @TableField("payType")
    int payType;
    @TableField("orderStatus")
    int orderStatusString;
}
