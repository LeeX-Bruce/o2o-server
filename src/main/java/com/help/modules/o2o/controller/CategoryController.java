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

import com.help.modules.o2o.entity.CategoryEntity;
import com.help.modules.o2o.service.CategoryService;
import com.help.common.utils.Query;
import com.help.common.utils.R;


/**
 * 分类
 *
 *
 */
@RestController
@RequestMapping("/o2o/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("category:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CategoryEntity> categoryList = categoryService.queryList(query);
		int total = categoryService.queryTotal(query);
		
		return R.ok().put("rows", categoryList).put("total", total);
	}
	
	/**
	 * 查询全部
	 * @return
	 */
	@RequestMapping("/getAll")
	@RequiresPermissions("category:list")
	public R getAll(){
		List<CategoryEntity> categoryList = categoryService.queryList(null);
		return R.ok().put("categoryList", categoryList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{categoryId}")
	@RequiresPermissions("category:info")
	public R info(@PathVariable("categoryId") Integer categoryId){
		CategoryEntity category = categoryService.queryObject(categoryId);
		
		return R.ok().put("category", category);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("category:save")
	public R save(@RequestBody CategoryEntity category){
		category.setCreateTime(new Date());
		categoryService.save(category);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("category:update")
	public R update(@RequestBody CategoryEntity category){
		categoryService.update(category);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("category:delete")
	public R delete(@RequestBody Integer[] categoryIds){
		categoryService.deleteBatch(categoryIds);
		
		return R.ok();
	}
	
}
