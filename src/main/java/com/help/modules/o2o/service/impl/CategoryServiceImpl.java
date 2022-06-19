package com.help.modules.o2o.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.help.modules.o2o.dao.CategoryDao;
import com.help.modules.o2o.entity.CategoryEntity;
import com.help.modules.o2o.service.CategoryService;



@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public CategoryEntity queryObject(Integer categoryId){
		return categoryDao.queryObject(categoryId);
	}
	
	@Override
	public List<CategoryEntity> queryList(Map<String, Object> map){
		return categoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return categoryDao.queryTotal(map);
	}
	
	@Override
	public void save(CategoryEntity category){
		categoryDao.save(category);
	}
	
	@Override
	public void update(CategoryEntity category){
		categoryDao.update(category);
	}
	
	@Override
	public void delete(Integer categoryId){
		categoryDao.delete(categoryId);
	}
	
	@Override
	public void deleteBatch(Integer[] categoryIds){
		categoryDao.deleteBatch(categoryIds);
	}

	@Override
	public List<CategoryEntity> queryAll() {
		return categoryDao.queryAll();
	}
	
}
