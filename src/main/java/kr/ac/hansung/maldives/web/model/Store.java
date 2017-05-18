package kr.ac.hansung.maldives.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="store", indexes = {
		@Index(columnList="dsiId", name="idx_store_dsi_id")
})
public class Store {
	@Id
	@GeneratedValue
	private Long storeIdx;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "latitude", nullable = false)
	private Double latitude;
	
	@Column(name = "longitude", nullable = false)
	private double longitude;
	
	@Column(name = "address" )
	private String address;

	@Column(length=300)
	private String imageUrl;
	
	@Column(length=40)
	private String phone;
	
	//daumStoreItem
	private String dsiId;
	
	@ManyToOne
	@JoinColumn(name="code")
	private Category category;

}
