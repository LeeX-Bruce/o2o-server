package com.help.modules.o2o.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.help.modules.api.utils.OrderNumUtil;
import com.help.modules.o2o.dao.GoodsDao;
import com.help.modules.o2o.dao.GoodsPicDao;
import com.help.modules.o2o.dao.OrderDao;
import com.help.modules.o2o.dao.OrderGoodsDao;
import com.help.modules.o2o.entity.GoodsEntity;
import com.help.modules.o2o.entity.OrderEntity;
import com.help.modules.o2o.entity.OrderGoodsEntity;
import com.help.modules.o2o.service.OrderService;



@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderGoodsDao orderGoodsDao;

	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private GoodsPicDao goodsPicDao;
	
	@Override
	public OrderEntity queryObject(Integer orderId){
		OrderEntity order = orderDao.queryObject(orderId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", order.getOrderId());
		List<OrderGoodsEntity> orderGoodsList = orderGoodsDao.queryList(params);
		order.setOrderGoodsList(orderGoodsList);

		return order;
	}
	
	@Override
	public List<OrderEntity> queryList(Map<String, Object> map){
		List<OrderEntity> orderList = orderDao.queryList(map);
		for (OrderEntity orderEntity : orderList) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("orderId", orderEntity.getOrderId());
			List<OrderGoodsEntity> orderGoodsList = orderGoodsDao.queryList(params);
			orderEntity.setOrderGoodsList(orderGoodsList);
		}
		return orderList;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderEntity order){
		orderDao.save(order);
	}
	
	@Override
	public void update(OrderEntity order){
		orderDao.update(order);
	}
	
	@Override
	public void delete(Integer orderId){
		orderDao.delete(orderId);
	}
	
	@Override
	public void deleteBatch(Integer[] orderIds){
		orderDao.deleteBatch(orderIds);
	}

	@Override
	@Transactional
	public void createOrder(OrderEntity orderEntity) {
		orderEntity.setOrderNumber(OrderNumUtil.getOrderNum());
		orderDao.save(orderEntity);
		List<OrderGoodsEntity> orderGoodsList = orderEntity.getOrderGoodsList();
		for (OrderGoodsEntity orderGoodsEntity : orderGoodsList) {
			GoodsEntity goods = goodsDao.queryObject(orderGoodsEntity.getGoodsId());
			orderGoodsEntity.setOrderId(orderEntity.getOrderId());
			orderGoodsEntity.setGoodsName(goods.getGoodsName());
			orderGoodsEntity.setPicUrl(goods.getPicUrl());
			orderGoodsEntity.setQtime(goods.getQtime());

			orderGoodsEntity.setLocation(goods.getLocation());

			orderGoodsDao.save(orderGoodsEntity);

			//修改商品状态
			goods.setStatus(1);
			goodsDao.update(goods);
		}

	}

	@Override
	public void updateByOrderNumber(OrderEntity order) {
		orderDao.updateByOrderNumber(order);
	}
	
}
