package com.infotop.eshop.httpservice;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class HttpServiceHandler {
	private String pcontent;

	public String httpContent(String url) {
		try {

			HttpClient client = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = client.execute(httpGet, localContext);
			HttpEntity entity = response.getEntity();
			pcontent = getASCIIContentFromEntity(entity);
		} catch (Exception e) {
			System.out.println("Exception e:" + e.getMessage());
		}
		return pcontent;
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
