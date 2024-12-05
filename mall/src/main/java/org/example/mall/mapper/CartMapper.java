package org.example.mall.mapper;

import org.apache.ibatis.annotations.*;
import org.example.mall.controller.Cart;
import org.example.mall.databaseEntity.CartItems;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfCart;

import java.util.List;

@Mapper
public interface CartMapper {

    //options这个选项会将插入后生成的id主键给直接注入到这个实体类中（更数据库绑定了）
    @Insert("INSERT INTO cart_items (userID,goodsId, goodsCount) VALUES (#{userID},#{goodsId}, #{goodsCount})")
    @Options(useGeneratedKeys = true, keyProperty = "cartItemId", keyColumn = "cartItemId")
    int addToCart(CartItems cartItem);

    //检查是否存在这个商品在购物车中了
    @Select("select  count(*) from cart_items where goodsId=#{goodsId} and userID=#{userID}")
    int searchIsExist(int goodsId,int userID);

    //在xml中定义
    //@Param是用来将参数传递的xml文件的
    List<ReturnDataOfCart> getCartItems(@Param("userID") int userID);

    @Update("update cart_items set goodsCount=#{goodsCount} where cartItemId=#{cartItemId}")
    int modifyCart(int goodsCount,int cartItemId);

    //删除购物车商品
    @Delete("delete from cart_items where cartItemId=#{id}")
    int deleteCartItem(int id);


    @Select("SELECT c.cartItemId , g.goodsCoverImg , g.goodsName, c.goodsCount, g.sellingPrice FROM cart_items c JOIN goods g ON c.goodsId = g.goodsId WHERE c.cartItemId = #{cartItemId} ")
    ReturnDataOfCart getItem(int cartItemId);
}
