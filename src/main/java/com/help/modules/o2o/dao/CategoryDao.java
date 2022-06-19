package com.help.modules.o2o.dao;

import com.help.modules.o2o.entity.CategoryEntity;
import com.help.modules.sys.dao.BaseDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 分类
 */
@Mapper
public interface CategoryDao extends BaseDao<CategoryEntity> {

	List<CategoryEntity> queryAll();
	
}
