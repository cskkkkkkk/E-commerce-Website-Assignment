package org.example.mall.databaseEntity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("category")
@Getter
@Setter
public class Category {
    @TableId
    private int categoryId;
    private String categoryName;
    private int categoryRank;
    private String createTime;
}
