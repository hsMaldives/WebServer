package kr.ac.hansung.maldives.web.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PointLog {
	public enum PointType {RATING, EVENT, OTHERS};
	
	@Id
	@GeneratedValue
	private Long point_idx;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_idx")
	private User user;


	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="store_idx")
	private Store store;
	
	
	// 이번에 쌓는 포인트
	private Integer acc_point;

	// 현재 총 포인트
	private Integer total_point;

	private Date timestamp;
	
	private PointType point_type;
}
