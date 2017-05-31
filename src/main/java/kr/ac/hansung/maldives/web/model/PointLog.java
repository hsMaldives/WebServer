package kr.ac.hansung.maldives.web.model;

import java.time.LocalDateTime;

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
	public enum PointType {RATING, EVENT, SPEND, OTHERS};
	
	@Id
	@GeneratedValue
	private Long pointIdx;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_idx")
	private User user;
		
	// 이번에 쌓는 포인트
	private Integer accPoint;

	// 현재 총 포인트
	private Integer totalPoint;

	private LocalDateTime timestamp;
	
	private PointType pointType;
}
