package com.help.modules.wechat.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderCloseResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.help.common.utils.IPUtils;
import com.help.common.utils.R;
import com.help.modules.api.annotation.LoginUser;
import com.help.modules.o2o.entity.UserEntity;
import com.help.modules.o2o.entity.OrderEntity;
import com.help.modules.o2o.service.OrderService;
import com.help.modules.o2o.service.UserService;


/**
 * @author lizhengle
 */
@RestController
@RequestMapping("/api/wechat/pay")
public class WxPayController{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "wxPayService")
    private WxPayService payService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;

    /**
     *
     * @param transactionId 微信订单号
     * @param outTradeNo    商户系统内部的订单号，当没提供transactionId时需要传这个。
     */
    @GetMapping("/queryOrder")
    public WxPayOrderQueryResult queryOrder(@RequestParam(required = false) String transactionId,
                                            @RequestParam(required = false) String outTradeNo)
            throws WxPayException {
        return this.payService.queryOrder(transactionId, outTradeNo);
    }

    /**
     *
     *
     * @param outTradeNo 商户系统内部的订单号
     */
    @GetMapping("/closeOrder/{outTradeNo}")
    public WxPayOrderCloseResult closeOrder(@PathVariable String outTradeNo) {
        try {
            WxPayOrderCloseResult orderCloseResult = this.payService.closeOrder(outTradeNo);
            return orderCloseResult;
        } catch (WxPayException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    @PostMapping("/unifiedOrder")
    public R unifiedOrder(@LoginUser UserEntity user, @RequestBody OrderEntity order, HttpServletRequest req) throws WxPayException {
    	WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
    	request.setOutTradeNo(order.getOrderNumber());
    	request.setOpenid(user.getOpenid());
    	request.setBody("测试商品");
    	request.setNotifyURL(payService.getConfig().getNotifyUrl());
    	request.setTotalFee(order.getOrder_price().multiply(new BigDecimal(100)).intValue());
//    	request.setTotalFee(order.getPrice().multiply(new BigDecimal(100)).intValue());
    	request.setSpbillCreateIp(IPUtils.getIpAddr(req));
    	request.setTradeType("JSAPI");
    	WxPayUnifiedOrderResult result = this.payService.unifiedOrder(request);
    	
    	String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = String.valueOf(System.currentTimeMillis());
    			
    	//signKey 商户平台设置的密钥key
    	//签名字段：appId，timeStamp，nonceStr，package，signType
    			
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("appId", payService.getConfig().getAppId());
    	params.put("timeStamp", timeStamp);
    	params.put("nonceStr", nonceStr);
    	params.put("package", "prepay_id=" + result.getPrepayId());
    	params.put("signType", "MD5");
    	
    	String sign = SignUtils.createSign(params, payService.getConfig().getMchKey(), "MD5");
    	
    	params.put("paySign", sign);
    	
    	return R.ok().put("data", params);
    }


//    /**
//     *
//     * 微信支付-申请退款
//     *
//     * @param request 请求对象
//     * @return 退款操作结果
//     */
//    @PostMapping("/refund")
//    public WxPayRefundResult refund(@RequestBody WxPayRefundRequest request) throws WxPayException {
//        return this.payService.refund(request);
//    }
//
//    /**
//     * <pre>
//     * 微信支付-查询退款
//     *
//     *
//     * @param transactionId 微信订单号
//     * @param outTradeNo    商户订单号
//     * @param outRefundNo   商户退款单号
//     * @param refundId      微信退款单号
//     * @return 退款信息
//     */
//    @GetMapping("/refundQuery")
//    public WxPayRefundQueryResult refundQuery(@RequestParam(required = false) String transactionId,
//                                              @RequestParam(required = false) String outTradeNo,
//                                              @RequestParam(required = false) String outRefundNo,
//                                              @RequestParam(required = false) String refundId)
//            throws WxPayException {
//        return this.payService.refundQuery(transactionId, outTradeNo, outRefundNo, refundId);
//    }
//
//
//    @AuthIgnore
//    @PostMapping("/parseOrderNotifyResult")
//    public String parseOrderNotifyResult(@RequestBody String xmlData) throws WxPayException {
//    	WxPayOrderNotifyResult wxPayOrderNotifyResult = this.payService.parseOrderNotifyResult(xmlData);
//
//    	if("SUCCESS".equals(wxPayOrderNotifyResult.getReturnCode())){
//    		OrderEntity order = new OrderEntity();
//    		order.setOrderNumber(wxPayOrderNotifyResult.getOutTradeNo());
//    		order.setOrderStatus(2);
//    		orderService.updateByOrderNumber(order);
//    		return WxPayNotifyResponse.success("OK");
//    	}else {
//    		logger.error(wxPayOrderNotifyResult.getReturnMsg());
//    		return WxPayNotifyResponse.success("OK");
//    	}
//
//    }
//
//    /**
//     * 此方法需要改造，根据实际需要返回com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse对象
//     */
//    @PostMapping("/parseRefundNotifyResult")
//    public WxPayRefundNotifyResult parseRefundNotifyResult(@RequestBody String xmlData) throws WxPayException {
//        return this.payService.parseRefundNotifyResult(xmlData);
//    }
//
//    /**
//     * 发送微信红包给个人用户
//     *
//     * @param request 请求对象
//     */
//    @PostMapping("sendRedpack")
//    public WxPaySendRedpackResult sendRedpack(@RequestBody WxPaySendRedpackRequest request) throws WxPayException {
//        return this.payService.sendRedpack(request);
//    }
//
//    /**
//     *   查询红包记录
//     *
//     * @param mchBillNo 商户发放红包的商户订单号，比如10000098201411111234567890
//     */
//    @GetMapping("/queryRedpack/{mchBillNo}")
//    public WxPayRedpackQueryResult queryRedpack(@PathVariable String mchBillNo) throws WxPayException {
//        return this.payService.queryRedpack(mchBillNo);
//    }
//
//    /**
//     *
//     * @param request 请求对象
//     */
//    @PostMapping("/entPay")
//    public WxEntPayResult entPay(@RequestBody WxEntPayRequest request) throws WxPayException {
//        return this.payService.entPay(request);
//    }
//
//    /**
//     *
//     * @param partnerTradeNo 商户订单号
//     */
//    @GetMapping("/queryEntPay/{partnerTradeNo}")
//    public WxEntPayQueryResult queryEntPay(@PathVariable String partnerTradeNo) throws WxPayException {
//        return this.payService.queryEntPay(partnerTradeNo);
//    }
//
//    /**
//     *
//     *
//     * @param productId  产品Id
//     * @param logoFile   商户logo图片的文件对象，可以为空
//     * @param sideLength 要生成的二维码的边长，如果为空，则取默认值400
//     * @return 生成的二维码的字节数组
//     */
//    public byte[] createScanPayQrcodeMode1(String productId, File logoFile, Integer sideLength) {
//        return this.payService.createScanPayQrcodeMode1(productId, logoFile, sideLength);
//    }
//
//    /**
//     *
//     *
//     * @param productId 产品Id
//     * @return 生成的二维码URL连接
//     */
//    public String createScanPayQrcodeMode1(String productId) {
//        return this.payService.createScanPayQrcodeMode1(productId);
//    }
//
//    /**
//     * <
//     *
//     * @param codeUrl    微信返回的交易会话的二维码链接
//     * @param logoFile   商户logo图片的文件对象，可以为空
//     * @param sideLength 要生成的二维码的边长，如果为空，则取默认值400
//     * @return 生成的二维码的字节数组
//     */
//    public byte[] createScanPayQrcodeMode2(String codeUrl, File logoFile, Integer sideLength) {
//        return this.payService.createScanPayQrcodeMode2(codeUrl, logoFile, sideLength);
//    }
//
//    /**
//     *
//     *
//     * @param request
//     */
//    @PostMapping("/report")
//    public void report(@RequestBody WxPayReportRequest request) throws WxPayException {
//        this.payService.report(request);
//    }
//
//    /**
//     *
//     *
//     * @param billDate   对账单日期 bill_date	下载对账单的日期，格式：20140603
//     * @param billType   账单类型	bill_type	ALL，返回当日所有订单信息，默认值，SUCCESS，返回当日成功支付的订单，REFUND，返回当日退款订单
//     * @param tarType    压缩账单	tar_type	非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
//     * @param deviceInfo 设备号	device_info	非必传参数，终端设备号
//     * @return 保存到本地的临时文件
//     */
//    @GetMapping("/downloadBill")
//    public WxPayBillResult downloadBill(@RequestParam String billDate,
//                                        @RequestParam String billType,
//                                        @RequestParam String tarType,
//                                        @RequestParam String deviceInfo) throws WxPayException {
//        return this.payService.downloadBill(billDate, billType, tarType, deviceInfo);
//    }
//
//    /**
//     *
//     */
//    @PostMapping("/micropay")
//    public WxPayMicropayResult micropay(@RequestBody WxPayMicropayRequest request) throws WxPayException {
//        return this.payService.micropay(request);
//    }
//
//    /**
//     * <
//     */
//    @PostMapping("/reverseOrder")
//    public WxPayOrderReverseResult reverseOrder(@RequestBody WxPayOrderReverseRequest request) throws WxPayException {
//        return this.payService.reverseOrder(request);
//    }
//
//    public String shorturl(WxPayShorturlRequest wxPayShorturlRequest) throws WxPayException {
//        //TODO 待补充完善
//        return null;
//    }
//
//    public String shorturl(String s) throws WxPayException {
//        //TODO 待补充完善
//        return null;
//    }
//
//    public String authcode2Openid(WxPayAuthcode2OpenidRequest wxPayAuthcode2OpenidRequest) throws WxPayException {
//        //TODO 待补充完善
//        return null;
//    }
//
//    public String authcode2Openid(String s) throws WxPayException {
//        //TODO 待补充完善
//        return null;
//    }
//
//    @GetMapping("/getSandboxSignKey")
//    public String getSandboxSignKey() throws WxPayException {
//        return this.payService.getSandboxSignKey();
//    }
//
//    @PostMapping("/sendCoupon")
//    public WxPayCouponSendResult sendCoupon(@RequestBody WxPayCouponSendRequest request)
//            throws WxPayException {
//        return this.payService.sendCoupon(request);
//    }
//
//    @PostMapping("/queryCouponStock")
//    public WxPayCouponStockQueryResult queryCouponStock(@RequestBody WxPayCouponStockQueryRequest request)
//            throws WxPayException {
//        return this.payService.queryCouponStock(request);
//    }
//
//    @PostMapping("/queryCouponInfo")
//    public WxPayCouponInfoQueryResult queryCouponInfo(@RequestBody WxPayCouponInfoQueryRequest request)
//            throws WxPayException {
//        return this.payService.queryCouponInfo(request);
//    }
//
//    @PostMapping("/queryComment")
//    public String queryComment(Date beginDate, Date endDate, Integer offset, Integer limit) throws WxPayException {
//        return this.queryComment(beginDate, endDate, offset, limit);
//    }

}