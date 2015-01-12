package com.infotop.eshop.specification;

import org.json.JSONObject;

import com.infotop.eshop.R;


import com.infotop.eshop.httpservice.HttpServiceHandler;
import com.infotop.eshop.httpservice.HttpUrl;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SpecificationClothActivity extends Activity {
	
	
	private static final String OCCASSION = "occassion";
	private static final String FABRIC = "fabric";
	private static final String FABRIC_CARE = "fabricCare";
	private static final String FIT = "fit";
	private static final String COLOR = "color";
	

	String s1;
	String occassion1;
	String fabric1;
	String fabricCare1;
	String fit1;
	String color1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specification_cloth);
		
		s1=getIntent().getExtras().getString("idspec");
		System.out.println("pabitr spec"+s1);
		
		String serverURL=new HttpUrl().getUrl()+"/eshop/rest/specificationbyproductid/"+s1;
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
			
			 occassion1=jsonObj.getString(OCCASSION);
			 fabric1=jsonObj.getString(FABRIC);
			 
			 fabricCare1=jsonObj.getString(FABRIC_CARE);
			fit1=jsonObj.getString(FIT);
			color1=jsonObj.getString(COLOR);
			
			
			
			} catch (Exception ex) {
				System.out.println("Exception e:" + ex.getMessage());
			}
			return null;
		}
		protected void onPostExecute(Void unused) {
			
			
			TextView txt1=(TextView)findViewById(R.id.occassion_cloth);
			TextView txt2=(TextView)findViewById(R.id.fabric_cloth);
			TextView txt3=(TextView)findViewById(R.id.fabric_care_cloth);
			TextView txt4=(TextView)findViewById(R.id.Fit_cloth);
			TextView txt5=(TextView)findViewById(R.id.color_cloth);
			
			
			
			
			txt1.setText(occassion1);
			txt2.setText(fabric1);
			txt3.setText(fabricCare1);
			txt4.setText(fit1);
			txt5.setText(color1);
			
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.specification_cloth, menu);
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
