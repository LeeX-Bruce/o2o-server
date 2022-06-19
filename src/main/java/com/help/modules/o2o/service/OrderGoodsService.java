package com.help.modules.o2o.service;

import com.help.modules.o2o.entity.OrderGoodsEntity;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface OrderGoodsService {
	
	OrderGoodsEntity queryObject(Integer id);
	
	List<OrderGoodsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderGoodsEntity orderGoods);
	
	void update(OrderGoodsEntity orderGoods);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);


}
