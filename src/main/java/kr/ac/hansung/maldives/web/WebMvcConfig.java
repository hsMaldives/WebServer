package kr.ac.hansung.maldives.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class WebMvcConfig {
	
	@Bean
	public TilesConfigurer tilesConfigurer(){
		final TilesConfigurer configurer = new TilesConfigurer();
		
		configurer.setDefinitions(new String[] {
				"/WEB-INF/views/**/views.xml"
				});
		configurer.setCheckRefresh(true);
		
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver(){
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		
		return resolver;
	}
}