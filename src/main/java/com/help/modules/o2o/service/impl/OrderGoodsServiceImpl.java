package com.help.modules.o2o.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.help.modules.o2o.dao.OrderGoodsDao;
import com.help.modules.o2o.entity.OrderGoodsEntity;
import com.help.modules.o2o.service.OrderGoodsService;



@Service("orderGoodsService")
public class OrderGoodsServiceImpl implements OrderGoodsService {
	//@Autowired
	private OrderGoodsDao orderGoodsDao;
	
	@Override
	public OrderGoodsEntity queryObject(Integer id){
		return orderGoodsDao.queryObject(id);
	}
	
	@Override
	public List<OrderGoodsEntity> queryList(Map<String, Object> map){
		return orderGoodsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderGoodsDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderGoodsEntity orderGoods){
//		System.out.println(orderGoods.getGoodsName()+orderGoods.getQtime());
		orderGoodsDao.save(orderGoods);
	}
	
	@Override
	public void update(OrderGoodsEntity orderGoods){
		orderGoodsDao.update(orderGoods);
	}
	
	@Override
	public void delete(Integer id){
		orderGoodsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		orderGoodsDao.deleteBatch(ids);
	}



	
}
