package kr.ac.hansung.maldives.web.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class StoreComment {

	@Id
	@GeneratedValue
	private Long storeCommentIdx;
	
	@Column(nullable=false)
	private String comment;
	
	@Column(nullable=false)
	private LocalDateTime timestamp;
	
	@Column(nullable=false)
	private Boolean deleted;
	
	@ManyToOne
	@JoinColumn(name="storeIdx")
	private Store store;
	
	@ManyToOne
	@JoinColumn(name="userIdx")
	private User user;
}
