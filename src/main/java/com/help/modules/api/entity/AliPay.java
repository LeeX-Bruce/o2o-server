package com.help.modules.api.entity;
import lombok.Data;

/**
 * @author LMH
 * @date 2022/6/20 1:20
 * @description
 */


@Data
public class AliPay {
    private String subject;
    private String traceNo;
    private String totalAmount;
}
