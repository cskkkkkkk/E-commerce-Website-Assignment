package org.example.mall.service;

import org.example.mall.returnEntity.Response;
import org.example.mall.databaseEntity.ReturnDataOfSearchView;
import org.example.mall.returnEntity.ReturnDataOfDetailGood;

import java.util.List;

public interface ProductService {
    Response<List<String>>getKindList();
    Response<List<ReturnDataOfSearchView>>selectInfoByKind(List<String>list);
    Response<ReturnDataOfDetailGood>selectDetailOfGood(int id);
}
