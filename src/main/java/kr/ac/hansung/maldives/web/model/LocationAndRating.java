package kr.ac.hansung.maldives.web.model;

import lombok.Data;

@Data
public class LocationAndRating {
	private double lati;
	private double longi;
	
	private float rating[];
}
