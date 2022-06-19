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

import com.help.modules.o2o.entity.OrderEvaluationEntity;
import com.help.modules.o2o.service.OrderEvaluationService;
import com.help.common.utils.Query;
import com.help.common.utils.R;




/**
 * 订单评价
 * 
 *
 */
@RestController
@RequestMapping("/o2o/orderevaluation")
public class OrderEvaluationController {
	@Autowired
	private OrderEvaluationService orderEvaluationService;
	@RequestMapping("/list")
	@RequiresPermissions("orderevaluation:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderEvaluationEntity> orderEvaluationList = orderEvaluationService.queryList(query);
		int total = orderEvaluationService.queryTotal(query);
		
		return R.ok().put("rows", orderEvaluationList).put("total", total);
	}
	
	
	/**
	 * 信息mall:
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("orderevaluation:info")
	public R info(@PathVariable("id") Integer id){
		OrderEvaluationEntity orderEvaluation = orderEvaluationService.queryObject(id);
		
		return R.ok().put("orderEvaluation", orderEvaluation);
	}
	
	/**
	 * 保存mall:
	 */
	@RequestMapping("/save")
	@RequiresPermissions("orderevaluation:save")
	public R save(@RequestBody OrderEvaluationEntity orderEvaluation){
		orderEvaluationService.save(orderEvaluation);
		
		return R.ok();
	}
	
	/**
	 * 修改mall:
	 */
	@RequestMapping("/update")
	@RequiresPermissions("orderevaluation:update")
	public R update(@RequestBody OrderEvaluationEntity orderEvaluation){
		orderEvaluationService.update(orderEvaluation);
		
		return R.ok();
	}
	
	/**
	 * 删除mall:
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("orderevaluation:delete")
	public R delete(@RequestBody Integer[] ids){
		for (Integer id : ids) {
			System.out.println("hello" + id);
		}
		orderEvaluationService.deleteBatch(ids);
		for(int i=0;i<ids.length;i++){
			System.out.println(ids[i]);
		}
		return R.ok();
	}




}
