package com.zty.study.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * springSecurity核心配置类
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()   //关闭跨域， 这个东西你可以自己自定义，如何你用JWT Token进行用户的登录鉴权就可以关闭掉，如果不用你也可以开启，只是还要再配置下跨域问题就行
                .formLogin()   //采用表单登录
                .and()
                .authorizeRequests()   //任何请求
                .anyRequest();         //任何请求都生效，进行拦截
    }
}
