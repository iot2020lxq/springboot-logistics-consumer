package com.iot.logistics.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/index.html").setViewName("index");
		registry.addViewController("/take.html").setViewName("take/take");
		registry.addViewController("/send.html").setViewName("send/send");
		registry.addViewController("/login.html").setViewName("login");
		registry.addViewController("/regist.html").setViewName("regist");
		registry.addViewController("/order.html").setViewName("order/order");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(new LoginHandlerInterecptor()).
		addPathPatterns("/**").
		excludePathPatterns("/","/index.html","/login.html","/userLogin",
				"/regist.html","/userRegist","/verifyCodeSrc",
				"/css/**","/fonts/**","/js/**","/images/**","/favicon.ico");
	}
	
}
