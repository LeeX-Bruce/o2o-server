package com.help.modules.api.controller;

import com.help.common.utils.R;
import com.help.modules.api.entity.TokenEntity;
import com.help.modules.api.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author LMH
 * @date 2022/6/16 21:01
 * @description
 */

@RestController
@RequestMapping("/api")
@Api("Token接口")
public class ApiTokenController {

    private TokenService tokenService;

    @GetMapping("/checkToken")
    @ApiOperation(value = "检查Token是否失效")
    public R checkToken(TokenEntity token) {
        //比较当前时间，看token是否过期
        if(token.getExpireTime().after(new Date())){
            return R.ok();
        }
        else{
            return R.error();
        }
    }
}
