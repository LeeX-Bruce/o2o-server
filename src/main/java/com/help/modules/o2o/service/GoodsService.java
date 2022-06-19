package com.help.modules.o2o.service;

import com.help.modules.o2o.entity.GoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品
 * 
 *
 */
public interface GoodsService {
	
	GoodsEntity queryObject(Integer goodsId);
	
	List<GoodsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GoodsEntity goods);


	 void update(GoodsEntity goods);

	void delete(Integer goodsId);
	
	void deleteBatch(Integer[] goodsIds);


	//UserEntity queryByOpenid(String openid);

}
