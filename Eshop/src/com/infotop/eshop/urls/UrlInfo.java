package com.infotop.eshop.urls;

public class UrlInfo {
	
	public static String HOMEPAGE_PATH = "http://192.168.8.163:8983/solr/collection1/select?q=categoryParentId%3A*&rows=1000&wt=json&indent=true";
	public static String LOGIN_PATH = "http://192.168.8.163:8080/eshop/rest/login";
	public static String REGISTER_PATH = "http://192.168.8.163:8080/eshop/rest/registration";
	public static String SUBCATEGORY_PATH = "http://192.168.8.163:8983/solr/collection1/select?q=categoryName%3A*&fq=categoryParentId%3A";

}
