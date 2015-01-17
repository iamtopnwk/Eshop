package com.infotop.eshop.model;

public class Category {

	private String id;
	private String uuid;
	private String categoryName;
	private String deleteFlag;
	private String categoryDescription;
	private String categoryParentId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public String getCategoryParentId() {
		return categoryParentId;
	}
	public void setCategoryParentId(String categoryParentId) {
		this.categoryParentId = categoryParentId;
	}

}
