package kr.ac.hansung.maldives.model;

import java.util.Arrays;

public class StoreAndRating {

	private DaumStoreItem storeInfo;
	private Float rating[];

	public void setStoreInfo(DaumStoreItem storeInfo) {
		this.storeInfo = storeInfo;
	}

	public DaumStoreItem getStoreInfo() {
		return storeInfo;
	}

	public void setRating(Float[] rating) {
		this.rating = rating;
	}

	public Float[] getRating() {
		return rating;
	}

	@Override
	public String toString() {
		return "StoreAndRating [storeInfo=" + storeInfo + ", rating=" + Arrays.toString(rating) + "]";
	}
}
