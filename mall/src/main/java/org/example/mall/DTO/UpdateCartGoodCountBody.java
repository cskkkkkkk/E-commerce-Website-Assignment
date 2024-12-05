package org.example.mall.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCartGoodCountBody {
    private int goodsCount;
    private int cartItemId;
}
