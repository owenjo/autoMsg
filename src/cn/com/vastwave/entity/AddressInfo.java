package cn.com.vastwave.entity;

public class AddressInfo {
	private String province;
	private String city;
	private String country;
	private String postCode;
	private AddressProperty adrProperty;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public AddressProperty getAdrProperty() {
		return adrProperty;
	}
	public void setAdrProperty(AddressProperty adrProperty) {
		this.adrProperty = adrProperty;
	}

}
