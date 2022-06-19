package com.help.modules.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.help.modules.api.annotation.AuthIgnore;
import com.help.modules.o2o.dao.OrderDao;
import com.help.modules.o2o.dao.OrderGoodsDao;
import com.help.modules.o2o.entity.GoodsEntity;
import com.help.modules.o2o.entity.OrderGoodsEntity;
import com.help.modules.o2o.service.GoodsService;


import com.help.modules.o2o.service.OrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.help.common.utils.R;
import com.help.modules.api.annotation.LoginUser;
import com.help.modules.o2o.entity.UserEntity;
import com.help.modules.o2o.entity.OrderEntity;
import com.help.modules.o2o.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/order")
@Api("订单接口")
public class ApiOrderController {
	
	@Autowired
	private OrderService orderService;
    @Autowired
	private GoodsService  goodsService;
    @Autowired
    private OrderGoodsService orderGoodsService;
	@Autowired
	private OrderGoodsDao orderGoodsDao;

    @Autowired
    public OrderDao orderDao;

    @GetMapping("getOrderList")
    @ApiOperation(value = "获取订单信息")
    public R getOrderList(@LoginUser UserEntity user, Integer status){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("orderStatus", status);
    	params.put("userId", user.getId());
    	List<OrderEntity> orderList = orderService.queryList(params);
        return R.ok().put("orderList", orderList);
    }
    
    @PostMapping("createOrder")
    @ApiOperation(value = "创建订单")
    public R createOrder(@LoginUser UserEntity user, @RequestBody OrderEntity orderEntity){
    	orderEntity.setUserId(user.getId());
    	orderEntity.setCreateTime(new Date());
    	orderEntity.setOrderStatus(1);
    	orderService.createOrder(orderEntity);
    	return R.ok();
    }
    
    @GetMapping("orderDetail")
    @ApiOperation(value = "订单详情")
    public R getOrderDetail(Integer id) {
    	OrderEntity order = orderService.queryObject(id);
    	return R.ok().put("order", order);
    }
    
    @GetMapping("cancelOrder")
    @ApiOperation(value = "取消订单")
    public R cancelOrder(Integer id) {
    	OrderEntity order = new OrderEntity();
    	order.setOrderStatus(0);

    	order.setOrderId(id);
    	orderService.update(order);
    	System.out.println("hello000");
    	//任务状态变回1
        //OrderGoodsEntity  orderGoods= new OrderGoodsEntity();
       // orderGoods.setOrderId(id);
		Integer dd=0;
		OrderGoodsEntity orderGoods = orderGoodsDao.find(id);
		dd=orderGoods.getGoodsId();
		System.out.println("hello"+dd);

		GoodsEntity  goods=new GoodsEntity();
		goods.setStatus(0);
		goods.setGoodsId(dd);
		goodsService.update(goods);
        /*




        System.out.println("hello111");*/
		//System.out.println("hello");System.out.println(id);System.out.println(gid);
      //GoodsEntity goods=new GoodsEntity();
      //goods.setStatus(0);
      //goods.setGoodsId(id);
		//GoodsService.update(goods);



    	return R.ok();
    }
	
	@GetMapping("payOrder")
    @ApiOperation(value = "支付订单")
    public R payOrder(Integer id) {
    	OrderEntity order = new OrderEntity();
    	order.setOrderStatus(2);
    	order.setOrderId(id);
    	orderService.update(order);

		//任务状态变回2
		//OrderGoodsEntity  orderGoods= new OrderGoodsEntity();
		// orderGoods.setOrderId(id);
		Integer dd=0;
		OrderGoodsEntity orderGoods = orderGoodsDao.find(id);
		dd=orderGoods.getGoodsId();
		System.out.println("hello"+dd);

		GoodsEntity  goods=new GoodsEntity();
		goods.setStatus(2);
		goods.setGoodsId(dd);
		goodsService.update(goods);

    	return R.ok();
    }
    
    @GetMapping("confirmOrder")
    @ApiOperation(value = "完成订单")
    public R confirmOrder(Integer id) {
    	/*

		*/


		GoodsEntity  goods=new GoodsEntity();
		goods.setStatus(2);
		goods.setGoodsId(id);
		System.out.println("hello"+id);
		goodsService.update(goods);

        Integer dd=0;
        OrderGoodsEntity orderGoods = orderGoodsDao.findorderid(id);
        dd=orderGoods.getOrderId();
        System.out.println("hello111"+dd);
        OrderEntity order = new OrderEntity();
        order.setOrderStatus(2);
        order.setOrderId(dd);
        orderService.update(order);

    	return R.ok();
    }




    @GetMapping("quxiaoOrder")
    @ApiOperation(value = "取消订单")
    public R quxiaoOrder(Integer id) {

        GoodsEntity  goods=new GoodsEntity();
        goods.setStatus(0);
        goods.setGoodsId(id);
        System.out.println("hello"+id);
        goodsService.update(goods);

        Integer dd=0;
        OrderGoodsEntity orderGoods = orderGoodsDao.findorderid(id);
        dd=orderGoods.getOrderId();
        System.out.println("hello111"+dd);
        OrderEntity order = new OrderEntity();
        order.setOrderStatus(0);
        order.setOrderId(dd);
        orderService.update(order);
        return R.ok();
    }


    @AuthIgnore
    @GetMapping("delete")
    @ApiOperation(value = "删除订单")
    public R del(int orderId) {
        //System.out.println("删除订单id:"+orderId);


        orderDao.delete(orderId);
        return R.ok();
    }


    @GetMapping("statistics")
    @ApiOperation(value = "统计")
    public R statistics() {
    	
    	return R.ok();
    }
    
}
