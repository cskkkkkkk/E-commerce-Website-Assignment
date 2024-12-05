package org.example.mall.databaseEntity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Select;

@Getter
@Setter
@TableName("cart_items")
public class CartItems {
    @TableId
    private int cartItemId;
    private int userID;
    private int goodsId;
    private int goodsCount;
    private String createdAt;
    private String updatedAt;
}
