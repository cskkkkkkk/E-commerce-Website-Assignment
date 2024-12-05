package org.example.mall.service;

import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfCart;
import org.example.mall.returnEntity.ReturnDataOfNull;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {
    Response<List<Integer>>addToCart(int userID,int goodsId, int goodsCount);

    //初始化购物车
    Response<List<ReturnDataOfCart>>getCartItems(int id);


    //修改商品数量
    Response<Integer>modifyCart(int goodsCount,int cartItemId);

    //删除
    Response<ReturnDataOfNull>deleteCartItem(int id);


    //批量查询商品
    Response<List<ReturnDataOfCart>>getByCartItemIds(List<Integer>list);
}
