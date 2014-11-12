package com.infotop.eshop.httpservice;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

public class HttpServiceHandler {
	private String result;
	private HttpClient client = new DefaultHttpClient();
	private HttpContext localContext = new BasicHttpContext();
	private HttpGet httpGet;
	private HttpResponse response;
	private HttpEntity entity;
	private HttpPost post;

	public String httpContent(String url) {
		try {
			httpGet = new HttpGet(url);
			response = client.execute(httpGet, localContext);
			entity = response.getEntity();
			result = getASCIIContentFromEntity(entity);
		} catch (Exception e) {
			System.out.println("Exception e:" + e.getMessage());
		}
		return result;
	}

	public String httpPost(String url, String jsonData) {
		try {
			StringEntity se = new StringEntity(jsonData);
			post = new HttpPost(url);
			post.setEntity(se);
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			response = client.execute(post);
			entity = response.getEntity();
			result = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			System.out.println("Exception e:" + e.getMessage());
		}
		return result;
	}

	private String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);
			if (n > 0)
				out.append(new String(b, 0, n));
		}
		return out.toString();
	}

}
