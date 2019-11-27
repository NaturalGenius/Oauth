package com.zhuliang.oauth.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.google.common.collect.Lists;

@Configuration
public class WebBeanConfig {

   @Bean
   public UserDetailsService memoryUserDetailsService() {
       InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
       userDetailsManager.createUser(new User("user", "123456", Lists.newArrayList(new SimpleGrantedAuthority("ROLE_user"))));
       userDetailsManager.createUser(new User("admin", "admin", Lists.newArrayList(new SimpleGrantedAuthority("ROLE_admin"))));
       return userDetailsManager;
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() {
       return NoOpPasswordEncoder.getInstance();
   }
   
   /**
    * 验证码配置
    * @return
    */
   @Bean
   public Producer kaptcha() {
       DefaultKaptcha producer = new DefaultKaptcha();
       Properties properties = new Properties();
       //图片宽度
       properties.put(Constants.KAPTCHA_IMAGE_WIDTH, "150");
       //图片高度
       properties.put(Constants.KAPTCHA_IMAGE_HEIGHT, "50");
       //字符编码
       properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789");
       //字符数
       properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
       Config config = new Config(properties);
       producer.setConfig(config);
       return producer;
   }
}
