package org.example.mall.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartBody {
    private int userID;
    private int goodsCount;
    private int goodsId;
}
