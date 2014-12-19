package com.infotop.eshop.activities;

import org.json.JSONObject;

import com.infotop.eshop.R;

import com.infotop.eshop.httpservice.HttpServiceHandler;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SpecificationLaptopActivity extends Activity {
	
	
	private static final String BRAND_NAME = "brandname";
	private static final String MODEL_ID = "modelId";
	
	private static final String WARRENTY = "warrenty";
	private static final String HDD_CAPACITY = "hddcapacity";
	private static final String BATTERY_BACKUP = "batteryBackup";
	private static final String HDMI_PORT = "hdmiPort";
	private static final String MIC_IN = "micin";
	private static final String CACHE = "cache";
	private static final String OS_SUPPORTED = "osSupported";
	private static final String RAM = "ram";
	private static final String TOUCHSCREEN = "touchScreen";
	
	String s1;
	
	String brandName1;
	String modelId1;
	
	String warrenty1;
	String hddcapacity1;
	String batteryBackup1;
	String hdmiPort1;
	String micIn1;
	String cache1;
	String ossupported1;
	String ram1;
	String touchScreen1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specification_laptop);
		
		s1=getIntent().getExtras().getString("idspec");
		System.out.println("pabitr spec"+s1);
		
		String serverURL="http://192.168.8.162:8989/eshop/rest/specificationbyproductid/"+s1;
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
			
			 brandName1=jsonObj.getString(BRAND_NAME);
			 modelId1=jsonObj.getString(MODEL_ID);
			 
			 warrenty1=jsonObj.getString(WARRENTY);
			 hddcapacity1=jsonObj.getString(HDD_CAPACITY);
			 ram1=jsonObj.getString(RAM);
			 micIn1=jsonObj.getString(MIC_IN);
			 hdmiPort1=jsonObj.getString(HDMI_PORT);
			 batteryBackup1=jsonObj.getString(BATTERY_BACKUP);
			 cache1=jsonObj.getString(CACHE);
			 touchScreen1=jsonObj.getString(TOUCHSCREEN);
			 ossupported1=jsonObj.getString(OS_SUPPORTED);
			
			
			} catch (Exception ex) {
				System.out.println("Exception e:" + ex.getMessage());
			}
			return null;
		}
		protected void onPostExecute(Void unused) {
			
			
			TextView txt1=(TextView)findViewById(R.id.brand_laptop);
			TextView txt2=(TextView)findViewById(R.id.model_laptop);
			TextView txt3=(TextView)findViewById(R.id.hdd_capacity_laptop);
			TextView txt4=(TextView)findViewById(R.id.warrenty_laptop);
			TextView txt5=(TextView)findViewById(R.id.mic_in_laptop);
			TextView txt6=(TextView)findViewById(R.id.hdmi_port_laptop);
			TextView txt7=(TextView)findViewById(R.id.ram_size_laptop);
			TextView txt8=(TextView)findViewById(R.id.cache_laptop);
			TextView txt9=(TextView)findViewById(R.id.touchscreen_laptop);
			TextView txt10=(TextView)findViewById(R.id.os_supported_laptop);
			TextView txt11=(TextView)findViewById(R.id.battery_backup_laptop);
			
			
			
			txt1.setText(brandName1);
			txt2.setText(modelId1);
			txt3.setText(hddcapacity1);
			txt4.setText(warrenty1);
			txt5.setText(micIn1);
			txt6.setText(hdmiPort1);
			txt7.setText(ram1);
			txt8.setText(cache1);
			txt9.setText(touchScreen1);
			txt10.setText(ossupported1);
			txt11.setText(batteryBackup1);
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.specification_laptop, menu);
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
