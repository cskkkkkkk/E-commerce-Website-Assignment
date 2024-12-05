package org.example.mall.returnEntity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

//这个类是在点击商品，展示更多信息
@Getter
@Setter
@TableName("goods")
public class ReturnDataOfDetailGood {
    @TableId
    private int goodsId;
    private String goodsName;
    private String sellingPrice;
    private String goodsCoverImg;
    private String goodsDetailContent;
}
