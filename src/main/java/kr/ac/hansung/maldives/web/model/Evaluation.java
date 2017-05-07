package kr.ac.hansung.maldives.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Entity
@Data
public class Evaluation {

	@Id
	@Column(name="position_idx", unique=true, nullable=false)
	@GeneratedValue(generator="positionGenerator")
	@GenericGenerator(name="positionGenerator", strategy="foreign", 
				parameters=@Parameter(value="position", name="property"))
	private Long position_idx;
	
	private Double eva1;
	
	private Double eva2;
	
	private Double eva3;
	
	private Double eva4;
	
	private Double eva5;
	
	@OneToOne
	@JoinColumn(name="position_idx")
	private Position position;
}
