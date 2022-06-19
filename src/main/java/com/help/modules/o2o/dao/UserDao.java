package com.help.modules.o2o.dao;

import org.apache.ibatis.annotations.Mapper;

import com.help.modules.o2o.entity.UserEntity;
import com.help.modules.sys.dao.BaseDao;

/**
 * 微信用户
 * 
 *
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity> {

	UserEntity queryByOpenid(String openid);
	//UserEntity queryBynickname(String nickname);

	//查找特定的用户
	UserEntity finaUser(int id);

}
