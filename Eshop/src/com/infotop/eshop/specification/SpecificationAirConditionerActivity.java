package com.infotop.eshop.specification;

import java.util.concurrent.ExecutionException;

import com.infotop.eshop.R;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;
import com.infotop.eshop.model.ProductSpecification;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.GetOperation;
import com.infotop.eshop.utilities.JsonHelper;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SpecificationAirConditionerActivity extends Activity {
	
	String s1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specification_air_conditioner);
		
		/*s1=getIntent().getExtras().getString("idspec");
		System.out.println("pabitr spec"+s1);
		
		String serverURL=UrlInfo.SPECIFICATIONBYID+s1;
		//new LongOperation().execute(serverURL);
	
	 AsyncTask<String, Void, String> productSpec = new GetOperation().execute(serverURL);
		// Use AsyncTask execute Method To Prevent ANR Problem
		try {
			System.out.println(productSpec.get());
			final ProductSpecification pdata= (ProductSpecification) JsonHelper.toObject(productSpec.get(), ProductSpecification.class);
			
			TextView txt1=(TextView)findViewById(R.id.brand_ac);
			TextView txt2=(TextView)findViewById(R.id.model_ac);
			
			TextView txt4=(TextView)findViewById(R.id.dehumidification_ac);
			TextView txt5=(TextView)findViewById(R.id.capacity_in_tons_ac);
			TextView txt6=(TextView)findViewById(R.id.series_ac);
			TextView txt7=(TextView)findViewById(R.id.remote_control_ac);
			TextView txt8=(TextView)findViewById(R.id.Color_ac);
			TextView txt9=(TextView)findViewById(R.id.compressor_ac);
			TextView txt10=(TextView)findViewById(R.id.operating_modes_ac);
			TextView txt11=(TextView)findViewById(R.id.turbo_mode_ac);
			TextView txt12=(TextView)findViewById(R.id.power_consumption_ac);
			TextView txt13=(TextView)findViewById(R.id.warranty_ac);
			
			txt1.setText(pdata.getBrandName());
			txt2.setText(pdata.getModelId());
			
			txt4.setText(pdata.getDehumidification());
			txt5.setText(pdata.getCapacityInTons());
			txt6.setText(pdata.getSeries());
			txt7.setText(pdata.getRemoteControl());
			txt8.setText(pdata.getColor());
			txt9.setText(pdata.getCompressor());
			txt10.setText(pdata.getOperatingModes());
			txt11.setText(pdata.getTurboMode());
			txt12.setText(pdata.getPowerConsumption());
			txt13.setText(pdata.getWarrenty());
			
			
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
		getMenuInflater().inflate(R.menu.specification_air_conditioner, menu);
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
