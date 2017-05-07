package kr.ac.hansung.maldives.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="Store")

@Data
public class Store {
	@Id
	@GeneratedValue
	private Long store_idx;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "code", nullable = false)
	private Integer code;
	
	@Column(name = "latitude", nullable = false)
	private Double latitude;
	
	@Column(name = "longitude", nullable = false)
	private double longitude;
	
	@Column(name = "address" )
	private String address;
	
	
}
