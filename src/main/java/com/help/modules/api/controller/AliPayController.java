package com.help.modules.api.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.help.modules.api.annotation.AuthIgnore;
import com.help.modules.api.entity.AliPay;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.Ignore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/**
 * @author LMH
 * @date 2022/6/20 1:27
 * @description
 */

@RestController
@RequestMapping("/alipay")
@Api("支付宝支付接口")
public class AliPayController {

    @GetMapping("/pay")
    @ApiOperation(value = "支付")
    public String pay(AliPay aliPay) {
        AlipayTradePagePayResponse response;
        try {
            //  发起API调用（以创建当面付收款二维码为例）
            response = Factory.Payment.Page()
                    .pay(URLEncoder.encode(aliPay.getSubject(), "UTF-8"), aliPay.getTraceNo(), aliPay.getTotalAmount(), "");
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }

        return response.getBody();
    }
}


