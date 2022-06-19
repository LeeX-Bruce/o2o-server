package com.help.modules.api.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.help.modules.o2o.dao.UserDao;
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
import com.help.modules.o2o.entity.GoodsEntity;
import com.help.modules.o2o.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/goods")
@Api("任务接口")
public class ApiGoodsController {
	
	@Autowired
	private GoodsService goodsService;

	@Autowired
     UserDao userDao;
	
    @AuthIgnore
    @GetMapping("list")
    @ApiOperation(value = "获取任务信息")
    //    @RequestParam: 把请求中指定名称的参数给控制器中的形参赋值
    public R list(@RequestParam Map<String, Object> map){
            List<GoodsEntity> goodsList = goodsService.queryList(map);


            return R.ok().put("goodsList", goodsList);
    }
    
    @AuthIgnore
    @GetMapping("detail")
    @ApiOperation(value = "获取任务详情")
    public R detail(int id){
    	GoodsEntity goods = goodsService.queryObject(id);

    	return R.ok().put("goods", goods);
    }
    
    @PostMapping("save")
    @ApiOperation(value = "保存任务")
    public R add(@LoginUser UserEntity user, @RequestBody GoodsEntity goods) {
    	goods.setUserId(user.getId());
    	goods.setStatus(0);
    	goods.setCreateTime(new Date());
    	goodsService.save(goods);
//        System.out.println("hello");
    	return R.ok();
    }

    @AuthIgnore
    @GetMapping("delete")
    @ApiOperation(value = "删除任务")
    public R del(int goodsId) {
        System.out.println("删除任务id:"+goodsId);
        goodsService.delete(goodsId);
        return R.ok();
    }

    @GetMapping("myPublish")
    @ApiOperation(value = "获取任务信息")
    public R myPublish(@LoginUser UserEntity user, @RequestParam Map<String, Object> map){
    	map.put("userId", user.getId());

    	List<GoodsEntity> goodsList = goodsService.queryList(map);
        return R.ok().put("goodsList", goodsList);
    }

    @PostMapping("update")
    @ApiOperation(value = "获取任务信息")
    public R update(@RequestBody GoodsEntity goods){
        System.out.println("修改"+goods);

        goodsService.update(goods);

        return R.ok();
    }
}
