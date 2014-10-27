/**
 *
 */
package com.infotop.eshop.domain;

/**
 * @author Rajesh
 *
 */
public class Product {

	private Long id;
	private String uuid;
	private String productName;
	private Long productUnqId;
	private String productDescription;
	private Boolean productFlag;
	private Boolean productStatus;
	private Double priductPrice;
	private String productStartDate;
	private String productEndDate;
	private Long storeId;
	private Long categoryId;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getProductUnqId() {
		return productUnqId;
	}
	public void setProductUnqId(Long productUnqId) {
		this.productUnqId = productUnqId;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Boolean getProductFlag() {
		return productFlag;
	}
	public void setProductFlag(Boolean productFlag) {
		this.productFlag = productFlag;
	}
	public Boolean getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Boolean productStatus) {
		this.productStatus = productStatus;
	}
	public Double getPriductPrice() {
		return priductPrice;
	}
	public void setPriductPrice(Double priductPrice) {
		this.priductPrice = priductPrice;
	}
	public String getProductStartDate() {
		return productStartDate;
	}
	public void setProductStartDate(String productStartDate) {
		this.productStartDate = productStartDate;
	}
	public String getProductEndDate() {
		return productEndDate;
	}
	public void setProductEndDate(String productEndDate) {
		this.productEndDate = productEndDate;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
