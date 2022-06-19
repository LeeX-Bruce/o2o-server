package com.help.modules.o2o.dao;

import com.help.modules.o2o.entity.OrderEntity;
import com.help.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 *
 */
@Mapper
public interface OrderDao extends BaseDao<OrderEntity> {

	void updateByOrderNumber(OrderEntity order);
	
}
