package kr.ac.hansung.maldives.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(indexes = {
		@Index(columnList="fullName", name="idx_cate_full_name")
})
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
