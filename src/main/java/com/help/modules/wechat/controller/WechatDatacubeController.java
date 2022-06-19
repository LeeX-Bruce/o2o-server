package com.help.modules.wechat.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.help.common.utils.DateUtils;
import com.help.common.utils.R;

import cn.binarywang.wx.miniapp.api.WxMaService;
import me.chanjar.weixin.common.exception.WxErrorException;

@RestController
@RequestMapping("wechatdatacube")
public class WechatDatacubeController {
	
	@Autowired
    private WxMaService service;
	
	/**
	 * 概况趋势
	 */
	public static String getweanalysisappiddailysummarytrend = "https://api.weixin.qq.com/datacube/getweanalysisappiddailysummarytrend";
	
	/**
	 * 访问趋势
	 */
	public static String getweanalysisappiddailyvisittrend = "https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend";
	
	/**
	 * 访问分布
	 */
	public static String getweanalysisappidvisitdistribution = "https://api.weixin.qq.com/datacube/getweanalysisappidvisitdistribution";
	
	/**
	 * 访问留存
	 */
	public static String getweanalysisappiddailyretaininfo = "https://api.weixin.qq.com/datacube/getweanalysisappiddailyretaininfo";
	
	/**
	 * 访问页面
	 */
	public static String getweanalysisappidvisitpage = "https://api.weixin.qq.com/datacube/getweanalysisappidvisitpage?access_token=";


	/**
	 * 概况趋势
	 * @return
	 * @throws WxErrorException
	 */
	public R getweanalysisappiddailysummarytrend() throws WxErrorException{
		String begin = DateUtils.format(new Date(), "yyyyMMdd");
		String end = DateUtils.format(new Date(), "yyyyMMdd");
		
		String postData = "{\"begin_date\" : \""+begin+"\",\"end_date\" : \""+end+"\"}";
		
		service.post(getweanalysisappiddailysummarytrend, postData);
		
		return R.ok();
	}
	
	/**
	 * 访问趋势
	 * @return
	 * @throws WxErrorException 
	 */
	@RequestMapping("/getweanalysisappiddailyvisittrend")
	public R getweanalysisappiddailyvisittrend() throws WxErrorException{
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.DATE, -1);
		String begin = DateUtils.format(cl.getTime(), "yyyyMMdd");
		String end = DateUtils.format(cl.getTime(), "yyyyMMdd");
		String postData = "{\"begin_date\" : \""+begin+"\",\"end_date\" : \""+end+"\"}";
		String result = service.post(getweanalysisappiddailyvisittrend, postData);
		Map<String, Object> map = new Gson().fromJson(result, Map.class);
		return R.ok().put("data", map);
	}
}
