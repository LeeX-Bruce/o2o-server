package com.help.modules.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.help.common.utils.R;
import com.help.modules.api.annotation.LoginUser;
import com.help.modules.o2o.entity.UserEntity;
import com.help.modules.o2o.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Api
 * 用于类，表示标识这个类是swagger的资源，描述Controller的作用。
 */
@RestController
@RequestMapping("/api/user/")
@Api("用户接口")
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
    @PostMapping("update")
    @ApiOperation(value = "更新用户信息")
    public R update(@RequestBody UserEntity user){
    	userService.update(user);
        return R.ok();
    }
    
    @GetMapping("info")
    @ApiOperation(value = "获取用户信息")
    public R info(@LoginUser UserEntity user) {
    	return R.ok().put("user", user);
    }
}
