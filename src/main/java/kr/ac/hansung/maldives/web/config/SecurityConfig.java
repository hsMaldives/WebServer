package kr.ac.hansung.maldives.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.writers.HstsHeaderWriter;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import kr.ac.hansung.maldives.web.dao.UserRepository;
import kr.ac.hansung.maldives.web.service.RepositoryUserDetailsService;
import kr.ac.hansung.maldives.web.service.SimpleSocialUserDetailsService;

/**
 * Spring Security 설정
 * @author MingyuBae
 *
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages={ "kr.ac.hansung.maldives.web" })
public class SecurityConfig {
	
	/**
	 * RememeberMe를 사용하는 사용자의 브라우저에 저장될 토큰값의 쿠키 이름
	 */
	private static final String REMEMBER_ME_NAME = "where-you-re";
	
	/**
	 * RememberMe 토큰값의 암호화 키
	 */
	private static final String tokenKey = "wHeReYoU!R2mEmB2RmE%TK";
	
	/**
	 * API Security 설정
	 * @author MingyuBae
	 *
	 */
	@Configuration
	@Order(Ordered.LOWEST_PRECEDENCE - 100)
	protected static class ApiSecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private DataSource dataSource;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http
				.antMatcher("/api/**")
				.csrf()
					.disable()
				.authorizeRequests()
					.anyRequest()
						.authenticated()
					.and()
				.addFilterBefore(rememberMeAuthenticationFilter(), BasicAuthenticationFilter.class )
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
				.exceptionHandling()
					.authenticationEntryPoint(new Http403ForbiddenEntryPoint());
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
				.authenticationProvider(rememberMeAuthenticationProvider());
		}
		
		/** Security에서 사용되는 UserDetatilsService를 구현한 Bean */
		@Bean
		public UserDetailsService userDetailsService() {
			return new RepositoryUserDetailsService(userRepository);
		}
		
		/* - Remember Me 관련 Bean 설정 - */
		/** RememberMe 인증 필터 */
		@Bean
		public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception{
			return new RememberMeAuthenticationFilter(authenticationManager(), persistentTokenBasedRememberMeServices());
		}
		
		/** RememberMe 서비스 */
		@Bean
		public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices(){
			PersistentTokenBasedRememberMeServices service = new PersistentTokenBasedRememberMeServices(tokenKey, userDetailsService(), jdbcTokenRepositoryImpl());
			service.setAlwaysRemember(true);
			service.setCookieName(REMEMBER_ME_NAME);
			return service;
		}
		
		/** RememberMe를 사용하는 사용자들의 토큰을 DB에 저장하는 Bean */
		@Bean
		public JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl(){
			JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
			jdbcTokenRepository.setCreateTableOnStartup(false);
			jdbcTokenRepository.setDataSource(dataSource);
			return jdbcTokenRepository;
		}
		
		/** RememberMe에서 사용되는 인증 제공자 Bean */
		@Bean
		public RememberMeAuthenticationProvider rememberMeAuthenticationProvider(){
			return new RememberMeAuthenticationProvider(tokenKey);
		}
		
	}
	
	/**
	 * Web 서비스의 Spring Security 설정
	 * @author MingyuBae
	 *
	 */
	@Configuration
	@Order(Ordered.LOWEST_PRECEDENCE - 90)
	protected static class SigninAuthenticationConfig extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private UserDetailsService userDetailsService;
		
		@Autowired
		private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
		
		@Autowired
		private RememberMeAuthenticationProvider rememberMeAuthenticationProvider;
		
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
					.antMatchers("/", "/connect/facebook", "/user/login", "/user/register","/user/logout", "/auth/*","/resources/**", "/rating/**")
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
				.rememberMe()
					.rememberMeServices(persistentTokenBasedRememberMeServices)
					.and()
				.httpBasic();
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
				.userDetailsService(userDetailsService)
					.passwordEncoder(passwordEncoder())
					.and()
				.authenticationProvider(rememberMeAuthenticationProvider);
		}
		
		/** 비밀번호 암호화 처리 인코더 Bean */
		@Bean
		public BCryptPasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}
		
		/** 소셜 로그인 처리에 사용되는 SocialUserDetailsService Bean */
		@Bean
		public SocialUserDetailsService socialUserDetailsService() {
			return new SimpleSocialUserDetailsService(userDetailsService);
		}
	}	
}
