package com.help.modules.o2o.service;

import com.help.modules.o2o.entity.OrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单
 * 
 *
 */
public interface OrderService {
	
	OrderEntity queryObject(Integer orderId);
	
	List<OrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderEntity order);
	
	void update(OrderEntity order);
	
	void delete(Integer orderId);
	
	void deleteBatch(Integer[] orderIds);

	void createOrder(OrderEntity orderEntity);

	void updateByOrderNumber(OrderEntity order);
}
