package com.infotop.eshop.utilities;

import org.json.JSONObject;

import android.os.AsyncTask;

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
			json.accumulate("uuid" , product.getUuid());
			json.accumulate("id", product.getId());
			json.accumulate("productName", product.getProductName());
			json.accumulate("productDescription", product.getProductDescription());
			json.accumulate("productPrice", product.getProductPrice());
			json.accumulate("image", product.getImage());
			jsonData = json.toString();
			pcontent = hs.httpPost(product.getServiceUrl(), jsonData);
			 System.out.println("UUID=========" +product.getUuid());
			 System.out.println("UUID=========" +product.getEmailId());
			return pcontent;
		} catch (Exception ex) {
			System.out.println("Exception e:" + ex.getMessage());
		}
		return pcontent;
	}

}
