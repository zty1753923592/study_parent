package com.zty.study.auth.login;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 这个类是用来做登录拦截的，通过用户名查询用户对象，然后进行判断就可以了
 */
@Component
public class MyUserDetails  implements UserDetailsService {


    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
