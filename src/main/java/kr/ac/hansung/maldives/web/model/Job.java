package kr.ac.hansung.maldives.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Job")

@Data
public class Job {
	@Id
	@GeneratedValue
	private Integer jobIdx;
	
	@Column(name = "name", nullable = false)
	private String name;
}
