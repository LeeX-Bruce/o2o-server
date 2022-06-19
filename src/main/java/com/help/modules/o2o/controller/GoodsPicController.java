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

import com.help.modules.o2o.entity.GoodsPicEntity;
import com.help.modules.o2o.service.GoodsPicService;
import com.help.common.utils.PageUtils;
import com.help.common.utils.Query;
import com.help.common.utils.R;




/**
 * 商品图片
 * 
 *
 */
@RestController
@RequestMapping("/o2o/goodspic")
public class GoodsPicController {
	@Autowired
	private GoodsPicService goodsPicService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("goodspic:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GoodsPicEntity> goodsPicList = goodsPicService.queryList(query);
		int total = goodsPicService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(goodsPicList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goodspic:info")
	public R info(@PathVariable("id") Integer id){
		GoodsPicEntity goodsPic = goodsPicService.queryObject(id);
		
		return R.ok().put("goodsPic", goodsPic);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goodspic:save")
	public R save(@RequestBody GoodsPicEntity goodsPic){
		goodsPicService.save(goodsPic);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goodspic:update")
	public R update(@RequestBody GoodsPicEntity goodsPic){
		goodsPicService.update(goodsPic);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goodspic:delete")
	public R delete(@RequestBody Integer[] ids){
		goodsPicService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
