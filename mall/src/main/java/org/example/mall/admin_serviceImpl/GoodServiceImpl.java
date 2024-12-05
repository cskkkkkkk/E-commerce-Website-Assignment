package org.example.mall.admin_serviceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.mall.admin_mapper.GoodMapper;
import org.example.mall.databaseEntity.Goods;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfAdminGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl {
    @Autowired
    private GoodMapper goodMapper;

    // 获取商品列表
    public Response<ReturnDataOfAdminGoods> getGoodsList(int pageNumber, int pageSize) {
        // 使用 PageHelper 分页查询
        PageHelper.startPage(pageNumber, pageSize);
        List<Goods> goods = goodMapper.selectAllGoods();

        // 获取分页后的结果
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        ReturnDataOfAdminGoods answer = new ReturnDataOfAdminGoods();
        answer.setList(pageInfo.getList());
        answer.setTotalCount(pageInfo.getTotal());
        // 返回分页结果
        return Response.success(answer);
    }

    public Response<Goods>getGoodsById(int id) {
        Goods good=goodMapper.selectGoodsById(id);
        if(good==null){
            return Response.failure("商品不存在");
        }
        return Response.success(good);
    }

    // 添加商品
    public void addGood(Goods good) {
        // 这里可以加入一些业务校验或处理逻辑
        goodMapper.insertGood(good);
    }

    // 更新商品
    public void updateGood(Goods good) {
        // 这里可以加入一些业务校验或处理逻辑
        goodMapper.updateGood(good);
    }
}
