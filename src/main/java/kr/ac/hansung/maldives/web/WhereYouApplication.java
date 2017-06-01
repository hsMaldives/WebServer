package kr.ac.hansung.maldives.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@SpringBootApplication
@EnableScheduling
public class WhereYouApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(WhereYouApplication.class, args);
	}
	
	/* lucy-xss filter */
	@Bean
	public XssEscapeServletFilter xssEscapeServletFilter(){
		XssEscapeServletFilter xssEscapeServletFilter = new XssEscapeServletFilter();
		
		return xssEscapeServletFilter;
	}
}
