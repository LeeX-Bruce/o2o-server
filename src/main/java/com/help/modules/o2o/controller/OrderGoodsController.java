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

import com.help.modules.o2o.entity.OrderGoodsEntity;
import com.help.modules.o2o.service.OrderGoodsService;
import com.help.common.utils.PageUtils;
import com.help.common.utils.Query;
import com.help.common.utils.R;




/**
 * 
 * 
 *
 */
@RestController
@RequestMapping("/o2o/ordergoods")
public class OrderGoodsController {
	@Autowired
	private OrderGoodsService orderGoodsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ordergoods:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderGoodsEntity> orderGoodsList = orderGoodsService.queryList(query);
		int total = orderGoodsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(orderGoodsList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ordergoods:info")
	public R info(@PathVariable("id") Integer id){
		OrderGoodsEntity orderGoods = orderGoodsService.queryObject(id);
		
		return R.ok().put("orderGoods", orderGoods);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ordergoods:save")
	public R save(@RequestBody OrderGoodsEntity orderGoods){
//		System.out.println(orderGoods.getQtime()+"hello");
		orderGoodsService.save(orderGoods);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ordergoods:update")
	public R update(@RequestBody OrderGoodsEntity orderGoods){
		orderGoodsService.update(orderGoods);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ordergoods:delete")
	public R delete(@RequestBody Integer[] ids){
		orderGoodsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
