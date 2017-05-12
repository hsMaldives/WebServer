package kr.ac.hansung.maldives.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {

	@Id
	private String categoryCode;
	
	private Integer depth;
	
	@Column(length=30)
	private String name;
	
	@Column(length=150)
	private String fullName;
	
	@Column(length=50)
	private String description;
}
