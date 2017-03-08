package com.star.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by hp on 2017/3/7.
 */
@Configuration
@ComponentScan(basePackages = {"com.star"}, excludeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ANNOTATION,value = EnableWebMvc.class)})
@Import(value = {WebConfig.class,SecurityConfig.class})
public class RootConfig {

}
