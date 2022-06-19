package com.help.modules.o2o.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信用户
 *
 *
 */
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Long id;
	// 微信openid
	private String openid;
	// 昵称
	private String nickname;
	// 头像
	private String avatarUrl;

	// 性别
	private String balance;


	// 创建时间
	private Date createTime;

	private String realName;

	private String mobile;
	
	private String email;

	//	地址

	private String address;
	
	private String password;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置：微信openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 获取：微信openid
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置：头像
	 */
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	/**
	 * 获取：头像
	 */
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
