package kr.ac.hansung.maldives.web.model;

import lombok.Data;

@Data
public class DaumStoreItem {
	public String title;	//store명
	public String imageUrl;
	public String address;	//address
	public String newAddress;
	public String zipcode;
	public String phone;
	public double longitude;	//longitude
	public double latitude;		//latitude
	public double distance;
	public String category;		//code
	public String id;			//pk
	public String placeUrl;
	public String direction;
	public String addressBCode;
}
