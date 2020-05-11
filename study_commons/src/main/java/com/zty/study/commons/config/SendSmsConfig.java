package com.zty.study.commons.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@ConfigurationProperties(prefix = "alibaba.sendsms")
@Data
public class SendSmsConfig {

    private String AccessKeyID="LTAI4FyWEhwZw5ag9KmikxPa";
    private String AccessKeySecret="RcEbAR0Kh3DMXA8F518PIc8ND4ysBB";
    /*模板编号*/
    private String TemplateCode="SMS_163625310";
    /*模板签名*/
    private String SignName="码农";
}
