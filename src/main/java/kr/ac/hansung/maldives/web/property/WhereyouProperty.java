package kr.ac.hansung.maldives.web.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="whereyou")
public class WhereyouProperty {

	private String fusionTableId;
}
