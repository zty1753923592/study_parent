package com.zty.study.cache;

import com.zty.study.commons.md5.mdUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * cookie工具类
 */
public class CookieUtils {

    //默认cookie有效时间
    private static final  Integer DEFAULT_TIME=7*24*60*60;


    public static void creatCookie(String userName, HttpServletResponse response){
        Cookie userCookie=new Cookie("userName",userName);
        Cookie ssidCookie= null;
        try {
            ssidCookie = new Cookie("ssid", mdUtils.EncodeByMd5(userName));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        userCookie.setMaxAge(DEFAULT_TIME);
        ssidCookie.setMaxAge(DEFAULT_TIME);
        userCookie.setPath("/");
        ssidCookie.setPath("/");
        response.addCookie(userCookie);
        response.addCookie(ssidCookie);
    }

}
