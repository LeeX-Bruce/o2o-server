package com.help.modules.o2o.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.help.modules.o2o.entity.OrderEntity;
import com.help.modules.o2o.service.OrderService;
import com.help.common.utils.Query;
import com.help.common.utils.R;




/**
 * 订单
 * 
 *
 */
@RestController
@RequestMapping("/o2o/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("order:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderEntity> orderList = orderService.queryList(query);
		int total = orderService.queryTotal(query);
		
		return R.ok().put("rows", orderList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{orderId}")
	@RequiresPermissions("order:info")
	public R info(@PathVariable("orderId") Integer orderId){
		OrderEntity order = orderService.queryObject(orderId);
		
		return R.ok().put("order", order);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("order:save")
	public R save(@RequestBody OrderEntity order){
		orderService.save(order);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("order:update")
	public R update(@RequestBody OrderEntity order){
		orderService.update(order);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("order:delete")
	public R delete(@RequestBody Integer[] orderIds){
		orderService.deleteBatch(orderIds);
		
		return R.ok();
	}
	
	/**
	 *
	 */
	@RequestMapping("/sendGoods/{orderId}")
	@RequiresPermissions("order:update")
	public R sendGoods(@PathVariable("orderId")Integer orderId, String courierNum) {
		OrderEntity order = new OrderEntity();
		order.setOrderId(orderId);
		order.setOrderStatus(3);
//		order.setCourierNum(courierNum);
		orderService.update(order);
		return R.ok();
	}
	
}
