package org.example.mall.returnEntity;

import lombok.Getter;
import lombok.Setter;
import org.example.mall.databaseEntity.Goods;

import java.util.List;

@Getter
@Setter
public class ReturnDataOfAdminGoods {
    private List<Goods> list;
    private long totalCount;
}
