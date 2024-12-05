package org.example.mall.returnEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.example.mall.databaseEntity.Goods;

import java.util.List;

@Setter
@Getter
//被包括在了ReturnDataOfOrderManage
//相当于一个订单
public class OrderManageSon {
    String createTime;
    int orderStatusString;
    List<Goods> GoodsInOrder;
}
