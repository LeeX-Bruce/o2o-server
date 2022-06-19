package com.help.modules.o2o.service;

import com.help.modules.o2o.entity.GoodsPicEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品图片
 */
public interface GoodsPicService {
	
	GoodsPicEntity queryObject(Integer id);
	
	List<GoodsPicEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GoodsPicEntity goodsPic);
	
	void update(GoodsPicEntity goodsPic);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
