package org.example.mall.databaseEntity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("goods")
public class ReturnDataOfSearchView {
    @TableId
    private int goodsId;
    private String goodsName;
    private String sellingPrice;
    private String goodsCoverImg;

}
