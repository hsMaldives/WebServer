package kr.ac.hansung.maldives.model;

import java.io.Serializable;

public class DaumStoreItem implements Serializable {

	private static final long serialVersionUID = 9012778041024535094L;

	private String title;
	private String imageUrl;
	private String address;
	private String newAddress;
	private String zipcode;
	private String phone;
	private double longitude;
	private double latitude;
	private double distance;
	private String category;
	private String id;
	private String placeUrl;
	private String direction;
	private String addressBCode;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaceUrl() {
		return placeUrl;
	}

	public void setPlaceUrl(String placeUrl) {
		this.placeUrl = placeUrl;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAddressBCode() {
		return addressBCode;
	}

	public void setAddressBCode(String addressBCode) {
		this.addressBCode = addressBCode;
	}

	@Override
	public String toString() {
		return "DaumStoreItem [title=" + title + ", imageUrl=" + imageUrl + ", address=" + address + ", newAddress="
				+ newAddress + ", zipcode=" + zipcode + ", phone=" + phone + ", longitude=" + longitude + ", latitude="
				+ latitude + ", distance=" + distance + ", category=" + category + ", id=" + id + ", placeUrl="
				+ placeUrl + ", direction=" + direction + ", addressBCode=" + addressBCode + "]";
	}
}
