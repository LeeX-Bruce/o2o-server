package com.help.modules.o2o.service;

import com.help.modules.o2o.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 分类
 *
 */
public interface CategoryService {
	
	CategoryEntity queryObject(Integer categoryId);
	
	List<CategoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CategoryEntity category);
	
	void update(CategoryEntity category);
	
	void delete(Integer categoryId);
	
	void deleteBatch(Integer[] categoryIds);

	List<CategoryEntity> queryAll();
}
