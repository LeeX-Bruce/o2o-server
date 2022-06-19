package com.help.modules.sys.service;

import com.help.modules.sys.entity.SysUserTokenEntity;
import com.help.common.utils.R;

/**
 * 用户Token
 *
 *
 */
public interface SysUserTokenService {

	SysUserTokenEntity queryByUserId(Long userId);

	SysUserTokenEntity queryByToken(String token);
	
	void save(SysUserTokenEntity token);
	
	void update(SysUserTokenEntity token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

}
