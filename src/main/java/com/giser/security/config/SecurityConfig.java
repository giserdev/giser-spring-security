package com.giser.security.config;

import com.giser.security.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author giserDev
 * @description
 * @date 2022-09-17 12:20:09
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                /*
        formLogin() 自定义登陆页面
        loginPage("/login.html") 自定义登陆页面的路径
        loginProcessingUrl("/user/login") 点击登陆请求的地址
        defaultSuccessUrl("/test/index") 登陆成功之后，默认跳转地址
        permitAll() 无条件允许访问但是不校验权限
        authorizeRequests() 哪些不需要认证
        anyRequest().authenticated() 任何请求都必须经过身份验证
        csrf().disable() 关闭csrf防护,也就是关闭跨域保护
        .antMatchers("/test/index").hasAuthority("admin,manager") 用户拥有指定的权限时才可以访问/test/index 路径 全匹配
        .antMatchers("/test/index").hasAnyAuthority("admin,manager") 满足一个就行 包含
        .antMatchers("/test/index").hasRole("sale") 角色全匹配才可以访问该路径 注意 ROLE_
         */
        http.formLogin().loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/success.html").permitAll()
                .and()
                // 哪些路径不需要登陆
                .authorizeRequests()
                .antMatchers("/", "/test/hello", "/user/login").permitAll()
                //权限全匹配
//                .antMatchers("/test/index").hasAuthority("admin")
                //权限满足一个就行
//                .antMatchers("/test/index").hasAnyAuthority("admin,manager")
                //角色全匹配
//                .antMatchers("/test/index").hasRole("sale")
                //角色满足一个就行
                .antMatchers("/test/index").hasAnyRole("sale,manager")
                .anyRequest().authenticated()
                .and()
                // 设置 过期时间 用于记住我 自动登陆
                .rememberMe()
                .tokenValiditySeconds(60)
                .userDetailsService(myUserDetailService)
                .and()
                .csrf().disable();
    }
}
