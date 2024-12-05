package org.example.mall.admin_mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.mall.databaseEntity.BrowseRecord;

import java.util.List;

@Mapper
public interface BrowseRecordMapper {

    @Select("select * from user_browse_history LIMIT #{pageNumber}, #{pageSize}")
    // 查询所有浏览记录
    List<BrowseRecord> selectAllBrowseRecords(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

    @Select("select count(*) from user_browse_history")
    int count();
}
