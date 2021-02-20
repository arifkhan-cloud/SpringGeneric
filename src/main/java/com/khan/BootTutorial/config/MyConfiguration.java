package com.khan.BootTutorial.config;


import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfiguration implements WebMvcConfigurer {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    return bCryptPasswordEncoder;
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	ServletRegistrationBean h2servletRegistration() {
	    @SuppressWarnings("unchecked")
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
	    registrationBean.addUrlMappings("/console/*");
	    return registrationBean;
	}
	
	
}
