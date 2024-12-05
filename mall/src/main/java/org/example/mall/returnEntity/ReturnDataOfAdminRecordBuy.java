package org.example.mall.returnEntity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

//返回admin端购买记录的所需信息
@Getter
@Setter
public class ReturnDataOfAdminRecordBuy {
    private int totalCount;
    List<AdminRecordBuy> list;
}
