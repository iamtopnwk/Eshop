package com.infotop.eshop.activities;

import org.json.JSONObject;

import com.infotop.eshop.R;
import com.infotop.eshop.httpservice.HttpServiceHandler;
import com.infotop.eshop.httpservice.HttpUrl;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SpecificationMouseActivity extends Activity {
	private static final String BRAND_NAME = "brandname";
	private static final String MODEL_ID = "modelId";
	private static final String CAPACITY = "capacity";
	private static final String WARRENTY = "warrenty";
	
	String s1;
	
	String bName;
	String mName;
	String capacity1;
	String warrenty1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specification_mouse);
		
		
		 
		s1=getIntent().getExtras().getString("idspec");
		System.out.println("pabitr spec"+s1);
		
		String serverURL= new HttpUrl().getUrl()+":8989/eshop/rest/specificationbyproductid/"+s1;
		new LongOperation().execute(serverURL);
	}
	
	private class LongOperation extends AsyncTask<String, Void, Void> {
		
		String scontent;

		@Override
		protected Void doInBackground(String... urls) {
			
			try{
			HttpServiceHandler hs = new HttpServiceHandler();
			scontent = hs.httpContent(urls[0]);
			JSONObject jsonObj;
			jsonObj = new JSONObject(scontent);
			System.out.println("SCONTENT URL:"+jsonObj);
			
			 bName=jsonObj.getString(BRAND_NAME);
			 mName=jsonObj.getString(MODEL_ID);
			 capacity1=jsonObj.getString(CAPACITY);
			 warrenty1=jsonObj.getString(WARRENTY);
			
			
			} catch (Exception ex) {
				System.out.println("Exception e:" + ex.getMessage());
			}
			return null;
		}
		protected void onPostExecute(Void unused) {
			
			
			TextView txt1=(TextView)findViewById(R.id.brand);
			TextView txt2=(TextView)findViewById(R.id.modelMouse);
			TextView txt3=(TextView)findViewById(R.id.capacityMouse);
			TextView txt4=(TextView)findViewById(R.id.warrentyMouse);
			
			txt1.setText(bName);
			txt2.setText(mName);
			txt3.setText(capacity1);
			txt4.setText(warrenty1);	
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.specification_mouse, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
