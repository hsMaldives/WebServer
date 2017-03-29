package kr.ac.hansung.maldives.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			// TODO Auto-generated method stub
			super.configure(auth);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception{
			HstsHeaderWriter writer = new HstsHeaderWriter(false);
			
			http
				.formLogin()
					.loginPage("/login")
					.permitAll()
					.and()
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.and()
				.csrf()
					.disable()
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
