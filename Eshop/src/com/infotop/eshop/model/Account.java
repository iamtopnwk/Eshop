package com.infotop.eshop.model;

public class Account {
private String userName;
private String emailId;
private String password;
private String shippingAddress;
private String billingAddress;
private String mobileNumber;
private String serviceUrl;

public String getServiceUrl() {
	return serviceUrl;
}
public void setServiceUrl(String serviceUrl) {
	this.serviceUrl = serviceUrl;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getShippingAddress() {
	return shippingAddress;
}
public void setShippingAddress(String shippingAddress) {
	this.shippingAddress = shippingAddress;
}
public String getBillingAddress() {
	return billingAddress;
}
public void setBillingAddress(String billingAddress) {
	this.billingAddress = billingAddress;
}
public String getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}


}
