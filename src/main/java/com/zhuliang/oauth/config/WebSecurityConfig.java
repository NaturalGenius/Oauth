package com.zhuliang.oauth.config;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import com.zhuliang.oauth.contact.LoginContact;
import com.zhuliang.oauth.filter.JwtAuthenticationTokenFilter;
import com.zhuliang.oauth.handle.CustomAccessDeniedHandler;
import com.zhuliang.oauth.handle.ServerAuthenticationFailureHandler;
import com.zhuliang.oauth.handle.ServerAuthenticationSuccessHandler;
import com.zhuliang.oauth.provider.DbAuthenticationProvider;
import com.zhuliang.oauth.provider.PhoneAuthenticationProvider;
import com.zhuliang.oauth.security.DbUserDetailsService;
import com.zhuliang.oauth.service.PermissionService;
import com.zhuliang.oauth.source.metadata.CustomSecurityMetadataSource;
import com.zhuliang.oauth.strategy.CustomSessionInformationExpiredStrategy;
import com.zhuliang.oauth.voter.RoleBasedVoter;

@EnableWebSecurity(debug = true)
//使用动态代理的方式进行AOP，只允许在接口层面进行权限拦截，如果想在任意的方法上进行权限拦截，那么就需要借助于AspectJ的方式进行AOP。首先将注解EnableGlobalMethodSecurity的mode设置为AdviceMode.ASPECTJ，然后添加JVM启动参数，这样就可以在任意方法上使用Spring Security的注解了
@EnableGlobalMethodSecurity(prePostEnabled = true/* mode = AdviceMode.ASPECTJ */)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

//	@Autowired
//	private VerificationCodeFIlter verificationCodeFIlter;
	@Autowired
	@Qualifier("phoneAuthenticationProvider")
	private PhoneAuthenticationProvider phoneAuthenticationProvider;
	@Autowired
	@Qualifier("dbAuthenticationProvider")
	private DbAuthenticationProvider dbAuthenticationProvider;
	@Autowired
	private DbUserDetailsService dbUserDetailsService;
	@Autowired
	@Qualifier("dbWebAuthenticationDetailsSource")
	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> dbWebAuthenticationDetailsSource;
    
	@Autowired
	private PhoneAuthenticationSecurityConfig phoneAuthenticationSecurityConfig;
	
//	@Autowired
//	private CustomFilterSecurityInterceptor customFilterSecurityInterceptor;
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	@Autowired
	private CustomSessionInformationExpiredStrategy customSessionInformationExpiredStrategy;
    @Autowired
	private PermissionService permissionService;
    
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private DataSource dataSource;
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(
                O fsi) {
                fsi.setSecurityMetadataSource(new CustomSecurityMetadataSource(fsi.getSecurityMetadataSource(), permissionService));
                return fsi;
            }
        })
       // .antMatchers("/static/**", "/test/**", "/kaptcha/image").permitAll()
        .anyRequest().authenticated()
        .accessDecisionManager(accessDecisionManager())
        .and()
        .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl(LoginContact.LOGIN_PATH)
                .permitAll()
                .authenticationDetailsSource(dbWebAuthenticationDetailsSource)//应用自定义的
                .successHandler(new ServerAuthenticationSuccessHandler())
                .failureHandler(new ServerAuthenticationFailureHandler())
                // .defaultSuccessUrl("/")
                // .failureUrl("")
                .and().csrf().disable();
        //初级实现验证码
           //  http.addFilterBefore(verificationCodeFIlter, UsernamePasswordAuthenticationFilter.class);
        http.apply(phoneAuthenticationSecurityConfig)
        .and()
              .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
        //session 会话管理
//        http
//      //  .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//     //   .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .maximumSessions(1)
//            .maxSessionsPreventsLogin(true)
//           .expiredSessionStrategy(customSessionInformationExpiredStrategy);
          http.rememberMe().tokenRepository(jdbcTokenRepositoryImpl())
          .tokenValiditySeconds(60 * 60 * 24 *7)
          .userDetailsService(dbUserDetailsService)
  
          //实现事件
          ;
          http.oauth2Login().failureHandler((req, res, e) -> {
              res.sendRedirect("/login.html");;
          });
        ;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(dbAuthenticationProvider);
    	auth.authenticationProvider(phoneAuthenticationProvider);
    	//super.configure(auth);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/test/**", "/kaptcha/image");
    	super.configure(web);
    }
    
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
            = Arrays.asList(
            new WebExpressionVoter(),
            // new RoleVoter(),
            new RoleBasedVoter(),
            new AuthenticatedVoter());
        return new AffirmativeBased(decisionVoters);
    }
    
    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
    //    jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
        return jdbcTokenRepositoryImpl;
    }
}
