package kr.ac.hansung.maldives.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data

@Entity
public class Category {

	@Id
	private String categoryCode;
	
	@Column(length=30)
	private String name;
	
	@Column(length=50)
	private String description;
}
