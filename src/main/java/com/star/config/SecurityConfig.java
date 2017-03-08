package com.star.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


/**
 * Created by hp on 2017/3/7.
 */
@Configuration
@EnableWebSecurity //启用Web安全性配置
//继承WebSecurityConfigurerAdapter并使用其三个configury方法来配置安全性
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
//                .inMemoryAuthentication()
//                .withUser("zhou")
//                .password("123")
//                .roles("USER");
                .jdbcAuthentication()
              .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled from users where username=?")
                .authoritiesByUsernameQuery("select username,'ROLE_USER' from users where username=?");
                //.passwordEncoder(new Md5PasswordEncoder());添加密码加密

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();//这一行一定不能少，否则应用将无法进入
    }
}
