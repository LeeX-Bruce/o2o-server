package com.help.modules.api.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.help.common.utils.R;
import com.help.modules.api.annotation.AuthIgnore;
import com.help.modules.api.annotation.LoginUser;
import com.help.modules.o2o.entity.UserEntity;
import com.help.modules.o2o.entity.OrderEvaluationEntity;
import com.help.modules.o2o.service.OrderEvaluationService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/order/evaluation")
@Api("评价接口")
public class ApiEvaluationController {
	
	@Autowired
	private OrderEvaluationService orderEvaluationService;
	
	@AuthIgnore
    @GetMapping("list")
	public R list(@RequestParam Map<String, Object> map) {
		List<OrderEvaluationEntity> evaluations = orderEvaluationService.queryList(map);
		return R.ok().put("evaluations", evaluations);
	}
	
	@PostMapping("save")
	public R save(@LoginUser UserEntity user, @RequestBody OrderEvaluationEntity orderEvaluation) {
		orderEvaluation.setCreateTime(new Date());
		orderEvaluation.setMemberId(user.getId().intValue());
		orderEvaluationService.save(orderEvaluation);
		return R.ok();
	}



}
