package com.zty.study.commons.code;

/***
 * 自动生成验证码工具类
 */
public class IndentifyCodeUtil {
    public static String getRandom(){
        String num="";
        for(int i=0;i<6;i++){
            num=num+String.valueOf((int)Math.floor(Math.random()*9+1));
        }
        return num;
    }

}
