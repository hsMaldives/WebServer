package kr.ac.hansung.maldives.web.model;

import lombok.Data;

@Data
public class StoreAndRating {
	
	private DaumStoreItem daumStoreItem;
	
	private Float rating[];
}
