package com.help.modules.o2o.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.help.common.utils.Query;
import com.help.common.utils.R;
import com.help.modules.o2o.entity.GoodsEntity;
import com.help.modules.o2o.service.GoodsService;


/**
 * 任务列表
 * 
 *
 */
@RestController
@RequestMapping("/o2o/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("goods:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		for (String ss : params.keySet()) {
			System.out.println("hghhhh");
			System.out.println(params.get(ss));
		}

		List<GoodsEntity> goodsList = goodsService.queryList(query);
		int total = goodsService.queryTotal(query);

		return R.ok().put("rows", goodsList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{goodsId}")
	@RequiresPermissions("goods:info")
	public R info(@PathVariable("goodsId") Integer goodsId){
		GoodsEntity goods = goodsService.queryObject(goodsId);
		
		return R.ok().put("goods", goods);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:save")
	public R save(@RequestBody GoodsEntity goods){
		goods.setCreateTime(new Date());
		goodsService.save(goods);
//		System.out.println("hello");
		return R.ok();
	}






	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:update")
	public R update(@RequestBody GoodsEntity goods){
		goodsService.update(goods);

		return R.ok();
	}
	
//	@RequestMapping("/confirmFail")
//	@RequiresPermissions("goods:update")
//	public R confirmFail(){
//
//		return R.ok();
//	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:delete")
	public R delete(@RequestBody Integer[] goodsIds){
		goodsService.deleteBatch(goodsIds);
		for(int i=0;i<goodsIds.length;i++){
			System.out.println("nihahihgufhbg");
			System.out.println(goodsIds[i]);
		}
		return R.ok();
	}
	
	@RequestMapping("/getAll")
	@RequiresPermissions("goods:list")
	public R getAll() {
		List<GoodsEntity> goodsList = goodsService.queryList(null);
		return R.ok().put("goodsList", goodsList);
	}
	
}
