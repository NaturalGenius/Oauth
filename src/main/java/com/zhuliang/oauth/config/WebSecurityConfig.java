package com.zhuliang.oauth.config;

import java.io.PrintWriter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.alibaba.fastjson.JSON;
import com.zhuliang.oauth.handle.ServerAuthenticationFailureHandler;
import com.zhuliang.oauth.result.Result;
import com.zhuliang.oauth.result.enums.GlobalResultCode;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/static/**", "/test/**", "/api/**", "/kaptcha/image").permitAll()
        .antMatchers("/user/**").hasAnyRole("user")
        .antMatchers("/admin/**").hasAnyRole("admin")
        .anyRequest().authenticated().and().formLogin()
                .loginPage("/login.html").permitAll().successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    writer.write(JSON.toJSONString(
                            Result.newInstance().setResultCode(GlobalResultCode.SUCCESS).setData("欢迎访问系统")));
                }).failureHandler(new ServerAuthenticationFailureHandler())
                // .defaultSuccessUrl("/")
                // .failureUrl("")
                .and().csrf().disable();
    }
    
}
