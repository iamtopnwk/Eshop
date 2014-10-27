/**
 *
 */
package com.infotop.eshop.domain;

/**
 * @author Rajesh
 *
 */
public class Store {

	private Long id;
	private String uuid;
	private String storeName;
	private String storeWebUrl;
	private String storeDescription;
	private Boolean storeFlag;
	private String storeStatus;
	private String craeteTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreWebUrl() {
		return storeWebUrl;
	}
	public void setStoreWebUrl(String storeWebUrl) {
		this.storeWebUrl = storeWebUrl;
	}
	public String getStoreDescription() {
		return storeDescription;
	}
	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
	}
	public Boolean getStoreFlag() {
		return storeFlag;
	}
	public void setStoreFlag(Boolean storeFlag) {
		this.storeFlag = storeFlag;
	}
	public String getStoreStatus() {
		return storeStatus;
	}
	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}
	public String getCraeteTime() {
		return craeteTime;
	}
	public void setCraeteTime(String craeteTime) {
		this.craeteTime = craeteTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}



}
