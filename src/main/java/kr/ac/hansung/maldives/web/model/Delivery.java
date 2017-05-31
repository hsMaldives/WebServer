package kr.ac.hansung.maldives.web.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Delivery {

	@NotEmpty
	String country;
	
	@NotEmpty
	String username;
	
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}")
	String phone;

	@NotEmpty
	String address;
	
	@NotEmpty
	String zipcode;
}
