package org.example.mall.serviceImpl;

import org.example.mall.mapper.ProductMapper;
import org.example.mall.returnEntity.Response;
import org.example.mall.databaseEntity.ReturnDataOfSearchView;
import org.example.mall.returnEntity.ReturnDataOfDetailGood;
import org.example.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Response<List<String>> getKindList()
    {
        return Response.success(productMapper.getKindList());
    }

    @Override
    public Response<List<ReturnDataOfSearchView>>selectInfoByKind(List<String>list){
        List<ReturnDataOfSearchView>listAns=new ArrayList<>();
        if(list.size()==0)
        {
            listAns.addAll(productMapper.selectAllGoods());
            return Response.success(listAns);
        }
        for(String s:list)
        {
            listAns.addAll(productMapper.selectByKind(s));
        }
        return Response.success(listAns);

    }

    @Override
    public Response<ReturnDataOfDetailGood>selectDetailOfGood(int id)
    {
        ReturnDataOfDetailGood ans= productMapper.selectDetailGood(id);
        if(ans==null)
        {
            return Response.failure("没有此商品");
        }
        return Response.success(ans);
    }
}
