package org.example.mall.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mall.returnEntity.Response;
import org.example.mall.databaseEntity.ReturnDataOfSearchView;
import org.example.mall.returnEntity.ReturnDataOfDetailGood;
import org.example.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class Product {

    @Autowired
    private ProductService productService;

    @GetMapping("/getKindList")
    Response<List<String>>getKindList(HttpServletRequest request,
                                      HttpServletResponse response)
    {
        return productService.getKindList();
    }

    //这个controller主要用来实现前端根据商品类别去浏览相应商品的功能。
    @PostMapping("/selectInfoByKind")
    Response<List<ReturnDataOfSearchView>>selectInfoByKind(@RequestBody List<String>list,
                                                           HttpServletResponse response,
                                                           HttpServletRequest request){
            return productService.selectInfoByKind(list);
    }


    //查询product的详细信息
    @GetMapping("/detail/{id}")
    Response<ReturnDataOfDetailGood>getDetail(@PathVariable int id,
                                              HttpServletResponse response,
                                              HttpServletRequest request){
        return productService.selectDetailOfGood(id);
    }
}
