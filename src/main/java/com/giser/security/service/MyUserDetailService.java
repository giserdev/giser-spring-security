package com.giser.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author giserDev
 * @description
 * @date 2022-09-17 12:13:05
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 判断用户名是否存在
        if (!"admin".equals(username)){
            throw  new UsernameNotFoundException("用户名不存在！");
        }

        // 从数据库中获取的密码 giser 的密文
        String pwd = new BCryptPasswordEncoder().encode("giser");
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,");

        // 第三个参数表示权限
        return new User(username,pwd,grantedAuthorities);
    }
}
