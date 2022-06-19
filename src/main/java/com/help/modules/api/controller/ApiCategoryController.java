package com.help.modules.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.help.common.utils.R;
import com.help.modules.api.annotation.AuthIgnore;
import com.help.modules.o2o.entity.CategoryEntity;
import com.help.modules.o2o.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/category")
@Api("分类接口")
public class ApiCategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
    @AuthIgnore
    @GetMapping("list")
    @ApiOperation(value = "获取分类信息")
    public R list(){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("sidx", "sort");
    	params.put("order", "ASC");
    	
    	List<CategoryEntity> categoryList = categoryService.queryList(params);
        return R.ok().put("categoryList", categoryList);
    }
}
