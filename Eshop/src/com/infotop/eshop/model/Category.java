package com.infotop.eshop.model;

import java.util.List;

public class Category {

	private String id;
	private String uuid;
	private String categoryName;
	private String categoryDescription;
	private String categoryParentId;
	private String categoryType;
	private String status;
	private String categoryCode;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private List<News> news;
	private Category parentcategory;
	private String imageUuid;
	private int order1;
	private String showInNav;
	private String showInNavStr;
	private List<Product> superMenuProducts;
		
	private List<Product> hotProducts;
	
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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
	public List<News> getNews() {
		return news;
	}
	public void setNews(List<News> news) {
		this.news = news;
	}
	public Category getParentcategory() {
		return parentcategory;
	}
	public void setParentcategory(Category parentcategory) {
		this.parentcategory = parentcategory;
	}
	public String getImageUuid() {
		return imageUuid;
	}
	public void setImageUuid(String imageUuid) {
		this.imageUuid = imageUuid;
	}
	public int getOrder1() {
		return order1;
	}
	public void setOrder1(int order1) {
		this.order1 = order1;
	}
	public String getShowInNav() {
		return showInNav;
	}
	public void setShowInNav(String showInNav) {
		this.showInNav = showInNav;
	}
	public String getShowInNavStr() {
		return showInNavStr;
	}
	public void setShowInNavStr(String showInNavStr) {
		this.showInNavStr = showInNavStr;
	}
	public List<Product> getSuperMenuProducts() {
		return superMenuProducts;
	}
	public void setSuperMenuProducts(List<Product> superMenuProducts) {
		this.superMenuProducts = superMenuProducts;
	}
	public List<Product> getHotProducts() {
		return hotProducts;
	}
	public void setHotProducts(List<Product> hotProducts) {
		this.hotProducts = hotProducts;
	}
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
