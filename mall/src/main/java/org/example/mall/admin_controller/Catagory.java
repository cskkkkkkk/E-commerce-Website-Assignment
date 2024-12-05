package org.example.mall.admin_controller;

import org.example.mall.admin_DTO.DelCategoryBody;
import org.example.mall.admin_serviceImpl.CategoryServiceImpl;
import org.example.mall.databaseEntity.Category;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
public class Catagory {
    @Autowired
    private CategoryServiceImpl categoryService;

    // 分页查询分类
    @GetMapping("/get")
    public Response<Map<String, Object> >getCategory(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        // 获取数据
        List<Category> categories = categoryService.getCategories(pageNumber, pageSize);

        // 获取总记录数
        int totalCount = categoryService.getTotalCount();

        // 返回分页数据
        Map<String, Object> result = new HashMap<>();
        result.put("list", categories);
        result.put("totalCount", totalCount);

        return Response.success(result);
    }


    @DeleteMapping("/del")
    public Response<ReturnDataOfNull> deleteCategories(@RequestBody DelCategoryBody delCategoryBody) {
        List<Integer>ids=delCategoryBody.getIds();
        //前端过滤了我这里就不用过滤了
        categoryService.deleteCategories(ids);
        return Response.success();
    }

    @PostMapping("/add")
    public Response<ReturnDataOfNull> addCategory(@RequestBody Category category) {
        try {
            categoryService.addCategory(category);
            return Response.success();
        } catch (Exception e) {
            return Response.failure("添加失败");
        }
    }

    // 编辑分类
    @PutMapping("/edit/{id}")
    public Response<ReturnDataOfNull> editCategory(@PathVariable("id") int id, @RequestBody Category category) {
        try {
            category.setCategoryId(id);
            categoryService.editCategory(category);
            return Response.success("编辑成功");
        } catch (Exception e) {
            return Response.failure("编辑失败");
        }
    }


    //返回商品分类列表
    @GetMapping()
    public Response<List<Category>> getAllCategories(@RequestParam int pageNumber,@RequestParam int pageSize) {

        return Response.success(categoryService.getCategories(pageNumber,pageSize));
    }
}
