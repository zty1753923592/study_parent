package com.zty.study.commons.Sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zty.study.commons.config.Constants;
import com.zty.study.commons.config.SendSmsConfig;
import com.zty.study.commons.exception.StudyBaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Slf4j
@Component
public class SendSms {

    @Autowired
    private SendSmsConfig sendSmsConfig;


    public String getCode(){
        String num="";
        for(int i=0;i<6;i++){
            num=num+String.valueOf((int)Math.floor(Math.random()*9+1));
        }
         return num;
    }

    public boolean SendSmsMessage(String phone,String code){
        if (StringUtils.isEmpty(phone)||StringUtils.isEmpty(code)){
            throw new  StudyBaseException(Constants.StatusType.INVALID_PARAM_MSG.getCode(),Constants.StatusType.INVALID_PARAM_MSG.getMsg());
        }
        DefaultProfile profile=DefaultProfile.getProfile("cn-hangzhou",sendSmsConfig.getAccessKeyID(),sendSmsConfig.getAccessKeySecret());
        IAcsClient iAcsClient=new DefaultAcsClient(profile);
        CommonRequest request=new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", sendSmsConfig.getSignName());
        request.putQueryParameter("TemplateCode", sendSmsConfig.getTemplateCode());
        request.putQueryParameter("TemplateParam", "{\"code\": " + code + "}");

        try {
            CommonResponse response = iAcsClient.getCommonResponse(request);
            log.info("response: {}", response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
