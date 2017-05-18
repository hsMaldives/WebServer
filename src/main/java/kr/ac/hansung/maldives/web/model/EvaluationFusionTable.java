package kr.ac.hansung.maldives.web.model;

import java.util.Date;

import lombok.Data;

@Data
public class EvaluationFusionTable {

	private Float eva1;
	private Float eva2;
	private Float eva3;
	private Float eva4;
	private Float eva5;
	private Double latitude;
	private Double longitude;
	
	private String category;
	private Integer age;
	private Integer jobIdx;
	private Integer sex;
	
	private Date date;

	public Float getEva1() {
		if(eva1 == null)	return -1.0f;
		return eva1;
	}
	public Float getEva2() {
		if(eva2 == null)	return -1.0f;
		return eva2;
	}
	public Float getEva3() {
		if(eva3 == null)	return -1.0f;
		return eva3;
	}
	public Float getEva4() {
		if(eva4 == null)	return -1.0f;
		return eva4;
	}
	public Float getEva5() {
		if(eva5 == null)	return -1.0f;
		return eva5;
	}
	
	public String getLocation(){
		return latitude + ", " + longitude;
	}
	
}
