package com.help.modules.o2o.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 任务
 * 
 *
 */
public class GoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer goodsId;
	// 物品名称
	private String location;
	// 社区地址
	private String goodsName;
	// 分类ID
	private Integer categoryId;
	// 价格
	private BigDecimal price;
//时间
	private BigDecimal qtime;

	private String picUrl;
	// 描述
	private String describe;
	// 创建时间
	private Date createTime;

	private String[] picUrls;

	/**
	 * 分类
	 */
	private CategoryEntity category;
	
	private Long userId;
	
	private UserEntity user;
	
	private Integer status;

	/**
	 * 设置：
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * 获取：
	 */
	public Integer getGoodsId() {
		return goodsId;
	}
	public String  getLocation() {
		return location;
	}
	/**
	 * 设置：物品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * 获取：物品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * 设置：分类ID
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 获取：分类ID
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * 设置：价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setQtime(BigDecimal qtime) {
		this.qtime = qtime;
	}

	/**
	 * 获取：价格
	 */
	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getQtime() {
		return qtime;
	}

	/**
	 * 设置：描述
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**
	 * 获取：描述
	 */
	public String getDescribe() {
		return describe;
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

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String[] getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String[] picUrls) {
		this.picUrls = picUrls;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
