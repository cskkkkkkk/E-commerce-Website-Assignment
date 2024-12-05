package org.example.mall.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mall.DTO.AddToCartBody;
import org.example.mall.DTO.GetPayItems;
import org.example.mall.DTO.UpdateCartGoodCountBody;
import org.example.mall.DTO.UserCartBody;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfCart;
import org.example.mall.returnEntity.ReturnDataOfNull;
import org.example.mall.service.CartService;
import org.example.mall.serviceImpl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shop-cart")
public class Cart {

    @Autowired
    private CartServiceImpl cartService;
    @Autowired
    private User user;

    @PostMapping("/addCart")
    public Response<List<Integer>> addCart(@RequestBody AddToCartBody addToCartBody,
                                           HttpServletRequest request
                        , HttpServletResponse response) {
        System.out.println(addToCartBody.getUserID());
            return cartService.addToCart(addToCartBody.getUserID(),addToCartBody.getGoodsId(),addToCartBody.getGoodsCount());
    }


    @PostMapping("/getAllGoods")
    public Response<List<ReturnDataOfCart>>getCart(@RequestBody UserCartBody userCartBody,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        return cartService.getCartItems(userCartBody.getId());
    }


    @PostMapping("/updateGoodsCount")
    public Response<Integer>modifyCart(@RequestBody UpdateCartGoodCountBody updateCartGoodCountBody,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {
        int goodsCount = updateCartGoodCountBody.getGoodsCount();
        int cartItemId = updateCartGoodCountBody.getCartItemId();
        return cartService.modifyCart(goodsCount,cartItemId);
    }

    @DeleteMapping("/{id}")
    public Response<ReturnDataOfNull>deleteCartItem(@PathVariable("id") int id,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response
                                                    ){
        return cartService.deleteCartItem(id);
    }


    @PostMapping("/settle")
    //获取批量购物车中商品数据，这个接口给pay功能使用
    public Response<List<ReturnDataOfCart>>getByCartItemIds(@RequestBody GetPayItems getPayItems,
                                                            HttpServletRequest request,
                                                            HttpServletResponse response)
    {

        List<Integer> cartItemIds = parseCartItemIds(getPayItems.getCartItemIds());
        return cartService.getByCartItemIds(cartItemIds);
    }

    //解析字符串
    private List<Integer> parseCartItemIds(String ids) {
        // 处理并将逗号分隔的字符串转换为 List<Long>
        List<Integer> cartItemIds = new ArrayList<>();
        if (ids != null && !ids.isEmpty()) {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                try {
                    cartItemIds.add(Integer.parseInt(id.trim()));  // 转换为 Long 类型
                } catch (NumberFormatException e) {
                    // 如果格式不正确，捕获并处理异常
                    System.err.println("Invalid ID format: " + id);
                }
            }
        }
        return cartItemIds;
    }

}
