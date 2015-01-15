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

public class SpecificationMobileActivity extends Activity {
	
	
	/*private static final String BRAND_NAME = "brandname";
	private static final String MODEL_ID = "modelId";
	
	private static final String TYPE = "type";
	private static final String SIM_TYPE = "simType";
	private static final String BATTERY_BACKUP = "batteryBackup";
	private static final String CALL_FEATURES = "callFeatures";
	private static final String WARRENTY = "warrenty";
	
	private static final String TOUCHSCREEN = "touchScreen";
	
	
	
	String brandName1;
	String modelId1;
	
	String warrenty1;
	String type1;
	String simType1;
	String batteryBackup1;
	String callFeatures1;
	
	String touchScreen1;

	String s1;*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specification_mobile);
		
		/*
		s1=getIntent().getExtras().getString("idspec");
		System.out.println("pabitr spec"+s1);
		
		String serverURL=new HttpUrl().getUrl()+"/eshop/rest/specificationbyproductid/"+s1;
		new LongOperation().execute(serverURL);*/
	}
	
	/*private class LongOperation extends AsyncTask<String, Void, Void> {
		
		String scontent;

		@Override
		protected Void doInBackground(String... urls) {
			
			try{
			HttpServiceHandler hs = new HttpServiceHandler();
			scontent = hs.httpContent(urls[0]);
			JSONObject jsonObj;
			jsonObj = new JSONObject(scontent);
			System.out.println("SCONTENT URL:"+jsonObj);
			
			 brandName1=jsonObj.getString(BRAND_NAME);
			 modelId1=jsonObj.getString(MODEL_ID);
			 
			 warrenty1=jsonObj.getString(WARRENTY);
			 type1=jsonObj.getString(TYPE);
			 simType1=jsonObj.getString(SIM_TYPE);
			 batteryBackup1=jsonObj.getString(BATTERY_BACKUP);
			 callFeatures1=jsonObj.getString(CALL_FEATURES);
			 
			 touchScreen1=jsonObj.getString(TOUCHSCREEN);
			
			
			
			} catch (Exception ex) {
				System.out.println("Exception e:" + ex.getMessage());
			}
			return null;
		}
		protected void onPostExecute(Void unused) {
			
			
			TextView txt1=(TextView)findViewById(R.id.brand_mobile);
			TextView txt2=(TextView)findViewById(R.id.model_mobile);
			TextView txt3=(TextView)findViewById(R.id.type_mobile);
			TextView txt4=(TextView)findViewById(R.id.simtype_mobile);
			TextView txt5=(TextView)findViewById(R.id.call_features_mobile);
			TextView txt6=(TextView)findViewById(R.id.battery_backup_mobile);
			TextView txt7=(TextView)findViewById(R.id.touchscreen_mobile);
			TextView txt8=(TextView)findViewById(R.id.warrenty_mobile);
			
			
			
			
			txt1.setText(brandName1);
			txt2.setText(modelId1);
			txt3.setText(type1);
			txt4.setText(simType1);
			txt5.setText(callFeatures1);
			txt6.setText(batteryBackup1);
			txt7.setText(touchScreen1);
			txt8.setText(warrenty1);
			
			
		}
		
	}*/
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.specification_mobile, menu);
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
