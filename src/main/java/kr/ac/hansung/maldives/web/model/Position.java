package kr.ac.hansung.maldives.web.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity

@Data
public class Position {

	@Id
	@GeneratedValue
	private Long position_idx;
	private Date time;
	private Boolean eva_bit;
	
	@OneToOne(mappedBy="position", cascade=CascadeType.ALL)
	private Evaluation evaluation;
	
	@ManyToOne
	@JoinColumn(name="store_idx")
	private Store store;
	
	@ManyToOne
	@JoinColumn(name="user_idx")
	private User user;
	
}
