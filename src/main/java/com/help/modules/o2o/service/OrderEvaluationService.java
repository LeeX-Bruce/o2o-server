package com.help.modules.o2o.service;

import com.help.modules.o2o.entity.OrderEvaluationEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单评价
 * 
 *
 */
public interface OrderEvaluationService {
	
	OrderEvaluationEntity queryObject(Integer id);
	
	List<OrderEvaluationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderEvaluationEntity orderEvaluation);
	
	void update(OrderEvaluationEntity orderEvaluation);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
