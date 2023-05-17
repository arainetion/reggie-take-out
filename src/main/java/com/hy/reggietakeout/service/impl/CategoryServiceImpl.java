package com.hy.reggietakeout.service.impl;

import com.hy.reggietakeout.entity.Category;
import com.hy.reggietakeout.mapper.CategoryMapper;
import com.hy.reggietakeout.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author hy
 * @since 2023-05-17
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
