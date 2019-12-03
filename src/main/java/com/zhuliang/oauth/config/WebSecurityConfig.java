package com.zhuliang.oauth.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.zhuliang.oauth.contact.LoginContact;
import com.zhuliang.oauth.filter.CustomFilterSecurityInterceptor;
import com.zhuliang.oauth.filter.VerificationCodeFIlter;
import com.zhuliang.oauth.handle.CustomAccessDeniedHandler;
import com.zhuliang.oauth.handle.ServerAuthenticationFailureHandler;
import com.zhuliang.oauth.handle.ServerAuthenticationSuccessHandler;
import com.zhuliang.oauth.provider.DbAuthenticationProvider;
import com.zhuliang.oauth.provider.PhoneAuthenticationProvider;

@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private VerificationCodeFIlter verificationCodeFIlter;
	@Autowired
	@Qualifier("phoneAuthenticationProvider")
	private PhoneAuthenticationProvider phoneAuthenticationProvider;
	@Autowired
	@Qualifier("dbAuthenticationProvider")
	private DbAuthenticationProvider dbAuthenticationProvider;
	
	@Autowired
	@Qualifier("dbWebAuthenticationDetailsSource")
	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> dbWebAuthenticationDetailsSource;
    
	@Autowired
	private PhoneAuthenticationSecurityConfig phoneAuthenticationSecurityConfig;
	
	@Autowired
	private CustomFilterSecurityInterceptor customFilterSecurityInterceptor;
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/static/**", "/test/**", "/kaptcha/image").permitAll()
        .anyRequest().authenticated().and().formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl(LoginContact.LOGIN_PATH)
                .permitAll()
                .authenticationDetailsSource(dbWebAuthenticationDetailsSource)//应用自定义的
                .successHandler(new ServerAuthenticationSuccessHandler()).failureHandler(new ServerAuthenticationFailureHandler())
                // .defaultSuccessUrl("/")
                // .failureUrl("")
                .and().csrf().disable();
        //初级实现验证码
           //  http.addFilterBefore(verificationCodeFIlter, UsernamePasswordAuthenticationFilter.class);
          http.apply(phoneAuthenticationSecurityConfig)
               .and()
               .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
              .and()//添加自定义权限过滤器
              .addFilterBefore(customFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(dbAuthenticationProvider);
    	auth.authenticationProvider(phoneAuthenticationProvider);
    	//super.configure(auth);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	super.configure(web);
    }
}
