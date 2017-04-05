package kr.ac.hansung.maldives.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.HstsHeaderWriter;

@Configuration
@ComponentScan(basePackages={ "kr.ac.hansung.maldives.web" })
@EnableWebSecurity
public class SecurityConfig {
	
	@Configuration
	protected static class SigninAuthenticationConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception{
			HstsHeaderWriter writer = new HstsHeaderWriter(false);
			
			http
				.antMatcher("/**")
				.formLogin()
					.loginPage("/user/login")
					.permitAll()
					.and()
				.logout()
					.logoutUrl("/user/logout")
					.logoutSuccessUrl("/")
					.and()
				.authorizeRequests()
					.antMatchers("/", "/connect/facebook", "/user/login", "/user/logout", "/resources/**")
					.permitAll()
					.and()
				.authorizeRequests()
					.anyRequest()
					.authenticated()
					.and()
				.headers()
					.contentTypeOptions()
						.and()
					.xssProtection()
						.and()
					.cacheControl()
						.and()
					.addHeaderWriter(writer)
						.frameOptions()
						.and()
					.frameOptions()
						.sameOrigin()
					.and()
				.httpBasic();
		}
		
		@Bean
		public BCryptPasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}
	}
	
}
