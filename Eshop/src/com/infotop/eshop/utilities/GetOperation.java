package com.infotop.eshop.utilities;

import android.os.AsyncTask;

public class GetOperation extends AsyncTask<String, Void, String> {
	private String pcontent = "";

	@Override
	public String doInBackground(String... urls) {
		try {
			HttpServiceHandler hs = new HttpServiceHandler();
			pcontent = hs.httpContent(urls[0]);
			// System.out.println("Executed data:" + pcontent);
			return pcontent;
		} catch (Exception ex) {
			System.out.println("Exception e:" + ex.getMessage());
		}
		return pcontent;
	}

}
