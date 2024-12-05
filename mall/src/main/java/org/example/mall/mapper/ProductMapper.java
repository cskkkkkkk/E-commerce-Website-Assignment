package org.example.mall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.mall.databaseEntity.Goods;
import org.example.mall.databaseEntity.ReturnDataOfSearchView;
import org.example.mall.returnEntity.ReturnDataOfDetailGood;


import java.util.List;
@Mapper
public interface ProductMapper {

    @Select("SELECT categoryName FROM category")
    List<String> getKindList();

    //将表中所有的商品查询后返回
    @Select("select goodsId,goodsName,sellingPrice,goodsCoverImg from goods")
    List<ReturnDataOfSearchView>selectAllGoods();

    //返回相应类别的所有商品
    @Select("select goodsId,goodsName,sellingPrice,goodsCoverImg from goods where goodsKind=#{keyword};")
    List<ReturnDataOfSearchView> selectByKind( String keyword);

    @Select("select goodsId,goodsName,sellingPrice,goodsCoverImg,goodsDetailContent from goods where goodsId=#{id}")
    ReturnDataOfDetailGood selectDetailGood(int id);

    //这是订单管理调用的接口
    @Select("select * from goods where goodsId=#{id}")
    Goods selectGood(int id);
}
