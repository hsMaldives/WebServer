package kr.ac.hansung.maldives.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.HstsHeaderWriter;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import kr.ac.hansung.maldives.web.dao.UserRepository;
import kr.ac.hansung.maldives.web.service.RepositoryUserDetailsService;
import kr.ac.hansung.maldives.web.service.SimpleSocialUserDetailsService;

@Configuration
@ComponentScan(basePackages={ "kr.ac.hansung.maldives.web" })
@EnableWebSecurity
public class SecurityConfig {
	
	@Configuration
	protected static class SigninAuthenticationConfig extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private UserRepository userRepository;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			HstsHeaderWriter writer = new HstsHeaderWriter(false);
			SpringSocialConfigurer socialConfigurer = new SpringSocialConfigurer();
			
			socialConfigurer
				.signupUrl("/user/register");
			
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
					.antMatchers("/", "/connect/facebook", "/user/login", "/user/register","/user/logout", "/auth/*","/resources/**")
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
				.apply(socialConfigurer)
					.and()
				.httpBasic();
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
				.userDetailsService(userDetailsService())
				.passwordEncoder(passwordEncoder());
		}
		
		@Bean
		public BCryptPasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}
		
		@Bean
		public SocialUserDetailsService socialUserDetailsService() {
			return new SimpleSocialUserDetailsService(userDetailsService());
		}
		
		@Bean
		public UserDetailsService userDetailsService() {
			return new RepositoryUserDetailsService(userRepository);
		}
	}
	
}
