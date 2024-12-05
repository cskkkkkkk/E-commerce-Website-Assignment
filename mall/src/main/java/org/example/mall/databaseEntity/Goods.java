package org.example.mall.databaseEntity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("goods")
@Setter
@Getter
public class Goods {
    @TableId
    private int goodsId;
    private String goodsName;
    private String goodsKind;
    private double sellingPrice;
    private String goodsCoverImg;
    private String goodsDetailContent;
    private double originalPrice;
    private int goodsSellStatus;
    private String tag;
    private int stockNum;
    private String goodsIntro;
    private int goodsCategoryId;
    private String createTime;
    private String updatedAt;
}
