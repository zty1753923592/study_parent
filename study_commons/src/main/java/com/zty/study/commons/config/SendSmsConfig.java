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

    private String AccessKeyID="";
    private String AccessKeySecret="";
    /*模板编号*/
    private String TemplateCode="";
    /*模板签名*/
    private String SignName="";
}
