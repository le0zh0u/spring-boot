package com.leozhou.demo.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by zhouchunjie on 16/8/8.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login").permitAll()//设置Spring Security对/和/login路径不拦截
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")//设置spring security  的登录页面访问的路径为/login
                .defaultSuccessUrl("/chat")//登录成功后转向/chat路径
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    //在内存中分别配置两个用户abc 和 abcd, 密码和用户名一样,角色是USER
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("abc").password("123").roles("USER")
                .and().withUser("abcd").password("123").roles("USER");
    }

    //  /resources/static/目录下的静态资源,Spring Security不拦截
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
    }
}
