package com.qdu.test.common;

import com.qdu.test.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2020/2/18.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${file.location}")
    private String location;

    @Value("${file.mapping}")
    private String mapping;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(this.mapping)
                .addResourceLocations("file:"+location);
    }
    /*@Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("index");
    }*/

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("*//**")
                .excludePathPatterns("/jquery*//**//**","/bootstrap*//**//**","/pics*//**//**")
                .excludePathPatterns("/verifyCode")
                .excludePathPatterns("/users/to_login","/users/to_add");
    }*/
}
