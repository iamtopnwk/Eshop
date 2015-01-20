package com.infotop.eshop.model;

public class Product {
	private String serviceUrl;
	private String id;
	private String productName;
	private String productPrice;
	private String categoryId;
	private String emailId;
	private String wishlistId;
	private String productDescription;
	private String image;
	private String uuid;
	private String cartlistId;
	private ImageList[] imageList ;



	
	
	public ImageList[] getImageList() {
		return imageList;
	}

	public void setImageList(ImageList[] imageList) {
		this.imageList = imageList;
	}

	public String getCartlistId() {
		return cartlistId;
	}

	public void setCartlistId(String cartlistId) {
		this.cartlistId = cartlistId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(String wishlistId) {
		this.wishlistId = wishlistId;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
}
