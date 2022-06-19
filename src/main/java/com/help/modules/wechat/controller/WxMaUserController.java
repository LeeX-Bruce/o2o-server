package com.help.modules.wechat.controller;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.help.common.utils.R;
import com.help.modules.api.annotation.AuthIgnore;
import com.help.modules.api.service.TokenService;
import com.help.modules.o2o.entity.UserEntity;
import com.help.modules.o2o.service.UserService;
import com.help.modules.wechat.utils.JsonUtils;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * 微信小程序用户接口
 */
@RestController
@RequestMapping("/api/wechat/user")
public class WxMaUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMaService wxService;
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private UserService userService;

    /**
     * 登陆接口
     */
    @AuthIgnore
    @GetMapping("login")
    public R login(String code) {
        // StringUtils.isBlank()方法:一次性检测String类型的变量是否为空的情况
        if (StringUtils.isBlank(code)) {
            return R.error("empty jscode");
        }
        //调微信官方接口获得sesssion_key openid存到 session对象里
        try {
            WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            
            //查询用户信息
            UserEntity user = userService.queryByOpenid(session.getOpenid());
            if(user == null) {
            	String sessionKey = session.getSessionKey();
            	return R.error(1, "未注册").put("sessionKey", sessionKey);
            }
            
            //生成token
            Map<String, Object> map = tokenService.createToken(user.getId());
            map.put("userInfo", user);
            return R.ok(map);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return R.error();
        }
    }
    
    
    /**
     * 用户注册
     */
    @AuthIgnore
    @GetMapping("register")
    public R register(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
    	
        // 用户信息校验
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return R.error("user check failed");
        }

        // 解密用户信息
        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        
        //注册
        UserEntity user = new UserEntity();
        user.setAvatarUrl(userInfo.getAvatarUrl());
        user.setOpenid(userInfo.getOpenId());
        user.setNickname(filterUtf8mb4(userInfo.getNickName()));
        user.setBalance(userInfo.getGender());
        user.setCreateTime(new Date());
        userService.save(user);

        return R.ok();
    }
    
    public static String filterUtf8mb4(String str) {
        final int LAST_BMP = 0xFFFF;
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            int codePoint = str.codePointAt(i);
            if (codePoint < LAST_BMP) {
                sb.appendCodePoint(codePoint);
            } else {
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @AuthIgnore
    @GetMapping("info")
    public String info(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        // 用户信息校验
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(userInfo);
    }

}
