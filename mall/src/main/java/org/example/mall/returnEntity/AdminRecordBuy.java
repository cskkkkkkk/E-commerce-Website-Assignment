package org.example.mall.returnEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRecordBuy {
    String nickName;
    String loginName;
    int orderNo;
    String createTime;
    double totalPrice;
}
