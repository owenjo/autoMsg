package cn.com.vastwave.entity;

import java.util.List;

/**
 * @author Owenjo
 *
 */
public class UserInfo {
private String idCard;
private String idType;
private List<AddressInfo> ads;
private OtherInfo otherInfo;
public String getIdCard() {
	return idCard;
}
public void setIdCard(String idCard) {
	this.idCard = idCard;
}
public String getIdType() {
	return idType;
}
public void setIdType(String idType) {
	this.idType = idType;
}
public List<AddressInfo> getAds() {
	return ads;
}
public void setAds(List<AddressInfo> ads) {
	this.ads = ads;
}
public OtherInfo getOtherInfo() {
	return otherInfo;
}
public void setOtherInfo(OtherInfo otherInfo) {
	this.otherInfo = otherInfo;
}


}
