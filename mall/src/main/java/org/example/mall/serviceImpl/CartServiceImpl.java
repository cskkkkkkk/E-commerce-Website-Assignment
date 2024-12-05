package org.example.mall.serviceImpl;

import org.example.mall.databaseEntity.CartItems;
import org.example.mall.mapper.CartMapper;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfCart;
import org.example.mall.returnEntity.ReturnDataOfNull;
import org.example.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    //加入购物车将商品
    @Override
    public Response<List<Integer>> addToCart(int userID,int goodsId, int goodsCount)
    {
        int row;
        row=cartMapper.searchIsExist(goodsId,userID);
        if(row>0)
        {   //code返回0，和前端约束好了，为0就打印错误信息不拦截。
            //说明已经存在这个商品就无法插入了
            return Response.failure("商品已存在购物车中","0");

        }
        CartItems cartItem = new CartItems();
        cartItem.setGoodsId(goodsId);
        cartItem.setUserID(userID);
        cartItem.setGoodsCount(goodsCount);
        cartMapper.addToCart(cartItem);
        List<Integer>arr=new ArrayList<>();
        arr.add(goodsId);
        return Response.success(arr,"201");
    }


    @Override
    public Response<List<ReturnDataOfCart>>getCartItems(int id)
    {
        List<ReturnDataOfCart>list=cartMapper.getCartItems(id);
        if(list.size()==0)
        {
            return Response.failure("购物车为空");
        }
        return Response.success(list);
    }

    @Override
    public Response<Integer>modifyCart(int goodsCount, int cartItemId)
    {
        cartMapper.modifyCart(goodsCount,cartItemId);
        return Response.success();

    }

    @Override
    public Response<ReturnDataOfNull>deleteCartItem(int id)
    {
        cartMapper.deleteCartItem(id);
        return Response.success();
    }

    @Override
    public Response<List<ReturnDataOfCart>>getByCartItemIds(List<Integer>list)
    {   List<ReturnDataOfCart>listAns=new ArrayList<>();
        for(int item:list)
        {
            listAns.add(cartMapper.getItem(item));
        }
        return Response.success(listAns);
    }
}
