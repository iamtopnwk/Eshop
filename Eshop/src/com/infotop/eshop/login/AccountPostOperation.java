package com.infotop.eshop.login;

import org.json.JSONObject;

import android.os.AsyncTask;

import com.infotop.eshop.httpservice.HttpServiceHandler;
import com.infotop.eshop.model.Account;

public class AccountPostOperation extends AsyncTask<Object, Void, String> {
	private String pcontent = "";

	@Override
	public String doInBackground(Object... urls) {
		// TODO Auto-generated method stub
		String jsonData = "";
		// Send data
		Account acc = (Account) urls[0];
		// System.out.println("Wishlist data object"+wislist.getEmailId());
		try {
			HttpServiceHandler hs = new HttpServiceHandler();
			JSONObject json = new JSONObject();
			json.accumulate("userName", acc.getUserName());
			json.accumulate("emailId", acc.getEmailId());
			json.accumulate("password", acc.getPassword());
			json.accumulate("shippingAddress", acc.getShippingAddress());
			json.accumulate("billingAddress", acc.getBillingAddress());
			json.accumulate("mobileNumber", acc.getMobileNumber());
			jsonData = json.toString();
			pcontent = hs.httpPost(acc.getServiceUrl(), jsonData);
			// System.out.println("Executed data:" + pcontent);
			return pcontent;
		} catch (Exception ex) {
			System.out.println("Exception e:" + ex.getMessage());
		}
		return pcontent;
	}

}
