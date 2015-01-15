package com.infotop.eshop.wishlist;

import org.json.JSONObject;

import android.os.AsyncTask;

import com.infotop.eshop.httpservice.HttpServiceHandler;
import com.infotop.eshop.model.Product;

public class PostOperation extends AsyncTask<Object, Void, String> {
	private String pcontent = "";

	@Override
	public String doInBackground(Object... urls) {
		// TODO Auto-generated method stub
		String jsonData = "";
		// Send data
		Product product = (Product) urls[0];
		// System.out.println("Wishlist data object"+wislist.getEmailId());
		try {
			HttpServiceHandler hs = new HttpServiceHandler();
			JSONObject json = new JSONObject();

			json.accumulate("emailId", product.getEmailId());
			json.accumulate("productId", product.getProductId());
			json.accumulate("productName", product.getProductName());
			json.accumulate("description", product.getDescription());
			json.accumulate("price", product.getPrice());
			json.accumulate("imageUrl", product.getImageUrl());
			jsonData = json.toString();
			pcontent = hs.httpPost(product.getServiceUrl(), jsonData);
			// System.out.println("Executed data:" + pcontent);
			return pcontent;
		} catch (Exception ex) {
			System.out.println("Exception e:" + ex.getMessage());
		}
		return pcontent;
	}

}
