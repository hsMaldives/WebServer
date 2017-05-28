package kr.ac.hansung.maldives.web.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
	
	@OneToMany(mappedBy="store", fetch=FetchType.LAZY)
	private List<StoreComment> comments;

	@Transient
	private Double avgEvaluation;
}
