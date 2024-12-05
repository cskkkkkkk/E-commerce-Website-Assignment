package org.example.mall.admin_controller;

import org.example.mall.admin_serviceImpl.GoodServiceImpl;
import org.example.mall.databaseEntity.Goods;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfAdminGoods;
import org.example.mall.returnEntity.ReturnDataOfNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodController {

    @Autowired
    private GoodServiceImpl goodService;

    // 获取商品列表
    @GetMapping("/list")
    public Response<ReturnDataOfAdminGoods> getGoodsList(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {

        // 调用服务层获取商品列表
        return goodService.getGoodsList(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public Response<Goods> getGood(@PathVariable int id) {
        return goodService.getGoodsById(id);
    }

    @PostMapping()
    public Response<ReturnDataOfNull>addGood(@RequestBody Goods good)
    {
        try {
            goodService.addGood(good);
            return Response.success();
        } catch (Exception e) {
            return Response.failure("添加商品失败：" + e.getMessage());
        }
    }

    @PutMapping
    public Response<ReturnDataOfNull>updateGood(@RequestBody Goods good)
    {
        try {
            goodService.updateGood(good);
            return Response.success();
        } catch (Exception e) {
            return Response.failure("更新商品失败：" + e.getMessage());
        }
    }
}