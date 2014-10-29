/**
 *
 */
package com.infotop.eshop.model;

/**
 * @author Rajesh
 *
 */
public class Image {


	private Long Id;
	private String uuId;
	private String imageName;
	private String imageValue;
	private String ImageType;
	private Long productId;
	private Long storeId;
	private Long categoryId;
	private String craeteTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getUuId() {
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageValue() {
		return imageValue;
	}
	public void setImageValue(String imageValue) {
		this.imageValue = imageValue;
	}
	public String getImageType() {
		return ImageType;
	}
	public void setImageType(String imageType) {
		ImageType = imageType;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
