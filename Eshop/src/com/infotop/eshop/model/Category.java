package com.infotop.eshop.model;

/**
 * @author Rajesh
 *
 */
public class Category {

	private Long id;
	private String uuid;
	private String categoryName;
	private String categoryDescription;
	private Boolean categoryStatusIndcation;
	private Boolean categoryFlag;
	private String categoryStatus;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String pcid;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

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

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Boolean getCategoryStatusIndcation() {
		return categoryStatusIndcation;
	}

	public void setCategoryStatusIndcation(Boolean categoryStatusIndcation) {
		this.categoryStatusIndcation = categoryStatusIndcation;
	}

	public Boolean getCategoryFlag() {
		return categoryFlag;
	}

	public void setCategoryFlag(Boolean categoryFlag) {
		this.categoryFlag = categoryFlag;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getPcid() {
		return pcid;
	}

	public void setPcid(String pcid) {
		this.pcid = pcid;
	}

}
