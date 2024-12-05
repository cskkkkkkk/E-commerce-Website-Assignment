package org.example.mall.admin_mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.mall.databaseEntity.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    // 使用注解插入新分类，id由数据库自动生成
    @Insert("INSERT INTO category (categoryName, categoryRank) VALUES (#{categoryName}, #{categoryRank})")
    void insertCategory(Category category);

    // 使用注解更新分类信息
    @Update("UPDATE category SET categoryName = #{categoryName}, categoryRank = #{categoryRank} WHERE categoryId = #{categoryId}")
    void updateCategorys(Category category);


    // 分页查询分类
    @Select("SELECT categoryId, categoryName, categoryRank, createTime " +
            "FROM category " +
            "ORDER BY createTime DESC " +
            "LIMIT #{pageNum}, #{pageSize}")
    List<Category> getCategories(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 获取总条数
    @Select("SELECT COUNT(*) FROM category")
    int getTotalCount();


    // 批量删除分类
    @Delete("<script>" +
            "DELETE FROM category WHERE categoryId IN " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    void deleteCategories(@Param("ids") List<Integer> ids);
}