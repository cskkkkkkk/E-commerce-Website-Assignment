package org.example.mall.returnEntity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

//返回用户订单管理需要的信息
@Getter
@Setter
public class ReturnDataOfOrderManage {
    int totalPage;
    //这是包含了很多订单
    List<OrderManageSon> list;
}
