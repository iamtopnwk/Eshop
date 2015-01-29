package com.infotop.eshop.urls;

public class UrlInfo {
	
	public static String HOMEPAGE_PATH = "http://192.168.8.163:8080/eshop/rest/parentCategories";
	public static String LOGIN_PATH = "http://192.168.8.163:8080/eshop/rest/login";
	public static String REGISTER_PATH = "http://192.168.8.163:8080/eshop/rest/registration";
	public static String SUBCATEGORY_PATH = "http://192.168.8.163:8983/solr/collection1/select?q=categoryName%3A*&fq=categoryParentId%3A";
    public static String ADDWishlist="http://192.168.8.163:8080/eshop/rest/addwishlist";
    public static String GET_ALLWISHLIST="http://192.168.8.163:8080/eshop/rest/getAllWishlistItemsByAccount";
    public static String DELETE_WISHLIST="http://192.168.8.163:8080/eshop/rest/deletewishlist";
    public static String ADDCartlist="http://192.168.8.163:8080/eshop/rest/addcartlist";
    public static String DELETE_CARTLIST="http://192.168.8.163:8080/eshop/rest/deletecartlist";
    public static String GET_ALLCARTLIST="http://192.168.8.163:8080/eshop/rest/getAllCartlistItemsByAccount";
    public static String INDVProduct="http://192.168.8.163:8080/eshop/rest/productByuuid/";
    
    public static String GET_ALLPRODUCTS="http://192.168.8.163:8080/eshop/rest/productsbyCategoryId";
    public static String SPECIFICATIONBYID="http://192.168.8.163:8080/eshop/rest/specificationbyproductid/";
}
