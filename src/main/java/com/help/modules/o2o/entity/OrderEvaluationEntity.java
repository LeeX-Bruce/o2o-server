package com.help.modules.o2o.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单评价
 * 
 *
 */
public class OrderEvaluationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 主键
	private Integer id;
	// 订单id
	private Integer orderId;
	// 用户id
	private Integer memberId;
	// 评价内容
	private String content;
	//
	private Integer star;
	// 评价时间
	private Date createTime;
	// 商品id
	private Integer goodsId;

	private UserEntity user;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置：订单id
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取：订单id
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * 设置：用户id
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * 获取：用户id
	 */
	public Integer getMemberId() {
		return memberId;
	}

	/**
	 * 设置：评价内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取：评价内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置：
	 */
	public void setStar(Integer star) {
		this.star = star;
	}

	/**
	 * 获取：
	 */
	public Integer getStar() {
		return star;
	}

	/**
	 * 设置：评价时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：评价时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：商品id
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * 获取：商品id
	 */
	public Integer getGoodsId() {
		return goodsId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
