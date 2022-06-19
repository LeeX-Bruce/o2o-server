package com.help.modules.o2o.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 *
 */
public class OrderGoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 订单ID
	private Integer orderId;
	// 商品ID
	private Integer goodsId;

	private Integer num;

	private String picUrl;

	private BigDecimal price;

	private BigDecimal qtime;

	private String goodsName;
	private String location;
	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置：订单ID
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取：订单ID
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * 设置：商品ID
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * 获取：商品ID
	 */
	public Integer getGoodsId() {
		return goodsId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
    
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQtime() {
		return qtime;
	}

	public void setQtime(BigDecimal qtime) {
		this.qtime = qtime;
	}


	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location ) {
		this.location = location;
	}

}
