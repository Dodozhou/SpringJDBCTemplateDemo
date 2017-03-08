package com.star.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import javax.sql.DataSource;

/**
 * Created by hp on 2017/3/7.
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.star")
@PropertySource("classpath:c3p0.properties")
public class WebConfig extends WebMvcConfigurerAdapter{
    @Value("${c3p0.driverClass}")
    private String driverClass;
    @Value("${c3p0.jdbcUrl}")
    private String jdbcUrl;
    @Value("${c3p0.user}")
    private String user;
    @Value("${c3p0.password}")
    private String password;
    @Value("${c3p0.maxPoolSize}")
    private int maxPoolSize;

    //配置ViewResolver
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine engine){
        ThymeleafViewResolver resolver=new ThymeleafViewResolver();
        resolver.setTemplateEngine(engine);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }


    //配置TemplateEngine
    @Bean
    public SpringTemplateEngine templateEngine(TemplateResolver resolver){
        SpringTemplateEngine engine=new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        //注册安全方言，以便在Thymeleaf页面中使用Thymeleaf Security标签
        //使用之前要引入thymeleaf-extras-springsecurity4依赖
        engine.addDialect(new SpringSecurityDialect());
        return engine;
    }

    //配置TemplateResolver
    @Bean
    public TemplateResolver templateResolver(){
        TemplateResolver resolver=new ServletContextTemplateResolver();
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode("HTML5");
        return resolver;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    //配置数据源
    @Bean
    public DataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setMaxPoolSize(maxPoolSize);
        return dataSource;
    }


    //配置静态资源处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
