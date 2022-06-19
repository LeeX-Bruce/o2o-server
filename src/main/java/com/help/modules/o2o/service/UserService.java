package com.help.modules.o2o.service;

import java.util.List;
import java.util.Map;

import com.help.modules.o2o.entity.UserEntity;

/**
 * 微信用户
 * 
 *
 */
public interface UserService {
	
	UserEntity queryObject(Long id);
	
	List<UserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserEntity user);
	
	void update(UserEntity user);
	
	void delete(Long id);
	
	void deleteBatch(Integer[] ids);

	UserEntity queryByOpenid(String openid);
	//UserEntity queryBynickname(String nickname);

}
