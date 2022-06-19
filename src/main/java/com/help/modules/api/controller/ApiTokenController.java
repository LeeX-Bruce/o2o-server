package com.help.modules.api.controller;

import com.help.modules.api.service.TokenService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LMH
 * @date 2022/6/16 21:01
 * @description
 */

@RestController
@RequestMapping("/api/checkToken")
@Api("Token接口")
public class ApiTokenController {

    private TokenService tokenService;


}
