package org.example.mall.returnEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//这个是购物车初始化后返回的商品数据
public class ReturnDataOfCart {
    private int cartItemId;         //购物车表
    private String goodsCoverImg; //商品表
    private String goodsName;   //商品表
    private int goodsCount;    //购物车表
    private double sellingPrice;  //商品表
}
