package com.belgium.cps.web.marushkai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor(){
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry
				.addResourceHandler("/images/**", "/videos/**")
                .addResourceLocations("file:///C:/Programming/CPS/images/", "file:/opt/cps/media/images/",
                        "file:///C:/Programming/CPS/videos/", "file:/opt/cps/media/videos/",
                        "classpath:/static/images/", "classpath:/static/videos/",
						"https://s3.eu-west-2.amazonaws.com/cps228/videos/",
						"https://s3.eu-west-2.amazonaws.com/cps228/images/");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
