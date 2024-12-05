package org.example.mall.returnEntity;


import lombok.Getter;
import lombok.Setter;

//封装的返回信息
@Setter
@Getter
public class AdminRecord {
    int goodsId;
    String goodsCoverImg;
    String goodsName;
    String browseTime;
    String nickName;
    String loginName;
}
