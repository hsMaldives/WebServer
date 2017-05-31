package kr.ac.hansung.maldives.web.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="whereyou")
public class WhereyouProperty {

	private String googleMapApiKey;
	
	private String fusionTableId;
	
	//평가했을 때 주는 포인트
	private Integer evalPoint;
	
}
