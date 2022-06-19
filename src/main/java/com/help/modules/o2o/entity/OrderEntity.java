package com.help.modules.o2o.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单
 *
 */
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer orderId;
	// 订单编号
	private String orderNumber;
	// 订单金额
		private BigDecimal order_price;

	private String order_location;



	// 订单状态，1：已取消，2：待完成，3：已完成
	private Integer orderStatus;
	// 创建时间
	private Date createTime;

	private List<OrderGoodsEntity> orderGoodsList;

	private Long userId;
	private UserEntity user;
	private OrderGoodsEntity orderGoods;


	public void setOrder_location(String order_location) {
		this.order_location = order_location;
	}

	public String getOrder_location() {
		return order_location;
	}

	/**
	 * 设置：
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取：
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * 设置：订单编号
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * 获取：订单编号
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	public BigDecimal getOrder_price() {
		return order_price;
	}

	/*public void setOrder_price(BigDecimal order_price) {
		this.order_price = order_price;
	}*/
	public void setOrder_price(BigDecimal order_price) {
		this.order_price = order_price;
	}
	/**
	 * 设置：订单状态，1：待付款，2：代发货，3：代收货，4：已完成
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 获取：订单状态，1：待付款，2：代发货，3：代收货，4：已完成
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public List<OrderGoodsEntity> getOrderGoodsList() {
		return orderGoodsList;
	}

	public void setOrderGoodsList(List<OrderGoodsEntity> orderGoodsList) {
		this.orderGoodsList = orderGoodsList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public OrderGoodsEntity getOrderGoods() {
		return orderGoods;
	}

	public void setOrderGoods(OrderGoodsEntity orderGoods) {
		this.orderGoods = orderGoods;
	}
}
