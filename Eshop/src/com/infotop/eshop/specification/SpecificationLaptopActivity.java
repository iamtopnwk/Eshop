package com.infotop.eshop.specification;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.infotop.eshop.R;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.model.ProductSpecification;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.GetOperation;
import com.infotop.eshop.utilities.JsonHelper;

public class SpecificationLaptopActivity extends Activity {
	
	String s1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specification_laptop);
		
		/*s1=getIntent().getExtras().getString("idspec");
		System.out.println("pabitr spec"+s1);
		
		String serverURL=UrlInfo.SPECIFICATIONBYID+s1;
		//new LongOperation().execute(serverURL);
	
	 AsyncTask<String, Void, String> productSpec = new GetOperation().execute(serverURL);
		// Use AsyncTask execute Method To Prevent ANR Problem
		try {
			System.out.println(productSpec.get());
			final ProductSpecification pdata= (ProductSpecification) JsonHelper.toObject(productSpec.get(), ProductSpecification.class);
			
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
			
			txt1.setText(pdata.getBrandName());
			txt2.setText(pdata.getModelId());
			txt3.setText(pdata.getHddCapacity());
			txt4.setText(pdata.getWarrenty());
			txt5.setText(pdata.getMicIn());
			txt6.setText(pdata.getHdmiPort());
			txt7.setText(pdata.getRam());
			txt8.setText(pdata.getCache());
			txt9.setText(pdata.getTouchScreen());
			txt10.setText(pdata.getOsSupported());
			txt11.setText(pdata.getBatteryBackup());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		
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
