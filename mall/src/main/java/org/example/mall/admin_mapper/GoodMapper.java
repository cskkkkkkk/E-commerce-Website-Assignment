package org.example.mall.admin_mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.mall.databaseEntity.Goods;

import java.util.List;

@Mapper
public interface GoodMapper {

    // 查询所有商品
    @Select("select * from goods")
    List<Goods> selectAllGoods();

    @Select("select * from goods where goodsId=#{id}" )
    Goods selectGoodsById(int id);

    // 添加商品
    @Insert("INSERT INTO goods (goodsName, goodsIntro, goodsCoverImg, goodsCategoryId, goodsKind, goodsDetailContent, goodsSellStatus, originalPrice, sellingPrice, stockNum, tag) " +
            "VALUES (#{goodsName}, #{goodsIntro}, #{goodsCoverImg}, #{goodsCategoryId}, #{goodsKind}, #{goodsDetailContent}, #{goodsSellStatus}, #{originalPrice}, #{sellingPrice}, #{stockNum}, #{tag})")
    void insertGood(Goods good);

    // 更新商品
    @Update("UPDATE goods SET goodsName = #{goodsName}, goodsIntro = #{goodsIntro}, goodsCoverImg = #{goodsCoverImg}, goodsCategoryId = #{goodsCategoryId}, " +
            "goodsKind = #{goodsKind}, goodsDetailContent = #{goodsDetailContent}, goodsSellStatus = #{goodsSellStatus}, " +
            "originalPrice = #{originalPrice}, sellingPrice = #{sellingPrice}, stockNum = #{stockNum}, tag = #{tag} " +
            "WHERE goodsId = #{goodsId}")
    void updateGood(Goods good);
}