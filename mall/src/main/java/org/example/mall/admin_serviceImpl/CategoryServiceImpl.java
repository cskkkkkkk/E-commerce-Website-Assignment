package org.example.mall.admin_serviceImpl;

import org.example.mall.admin_mapper.CategoryMapper;
import org.example.mall.databaseEntity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl {
    @Autowired
    private CategoryMapper categoryMapper;

    // 获取分页数据
    public List<Category> getCategories(int pageNum, int pageSize) {
        return categoryMapper.getCategories((pageNum - 1) * pageSize, pageSize);
    }

    // 获取总记录数
    public int getTotalCount() {
        return categoryMapper.getTotalCount();
    }

    // 批量删除分类
    public void deleteCategories(List<Integer> ids) {
        categoryMapper.deleteCategories(ids);
    }

    // 添加分类
    public void addCategory(Category category) {
        categoryMapper.insertCategory(category);
    }

    // 编辑分类
    public void editCategory(Category category) {
        categoryMapper.updateCategorys(category);
    }
}
