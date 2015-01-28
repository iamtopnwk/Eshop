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

public class SpecificationWashingMachinActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specification_washing_machin);
		
		/*String s1 = getIntent().getExtras().getString("idspec");
		System.out.println("pabitr spec"+s1);
		
		String serverURL=UrlInfo.SPECIFICATIONBYID+s1;
		//new LongOperation().execute(serverURL);
	
	 AsyncTask<String, Void, String> productSpec = new GetOperation().execute(serverURL);
		// Use AsyncTask execute Method To Prevent ANR Problem
		try {
			System.out.println(productSpec.get());
			final ProductSpecification pdata= (ProductSpecification) JsonHelper.toObject(productSpec.get(), ProductSpecification.class);
			
			TextView txt1=(TextView)findViewById(R.id.brand_washingmachine);
			TextView txt2=(TextView)findViewById(R.id.model_washingmachine);
			TextView txt3=(TextView)findViewById(R.id.loadtype_washingmachine);
			TextView txt4=(TextView)findViewById(R.id.washingcapacity_washingmachine);
			TextView txt5=(TextView)findViewById(R.id.weight_washingmachine);
			TextView txt6=(TextView)findViewById(R.id.functiontype_washingmachine);
			TextView txt7=(TextView)findViewById(R.id.inbuiltheater_washingmachine);
			TextView txt8=(TextView)findViewById(R.id.shade_washingmachine);
			TextView txt9=(TextView)findViewById(R.id.maximum_spin_speed_washingmachine);
			TextView txt10=(TextView)findViewById(R.id.washing_method_washingmachine);
			TextView txt11=(TextView)findViewById(R.id.power_consumption_washingmachine);
			TextView txt12=(TextView)findViewById(R.id.warranty_washingmachine);
			
			txt1.setText(pdata.getBrandName());
			txt2.setText(pdata.getModelId());
			txt3.setText(pdata.getLoadType());
			txt4.setText(pdata.getWashingCapacity());
			txt5.setText(pdata.getWeight());
			txt6.setText(pdata.getFunctionType());
			txt7.setText(pdata.getInBuiltHeater());
			txt8.setText(pdata.getShade());
			txt9.setText(pdata.getMaximumSpinSpeed());
			txt10.setText(pdata.getWashingMethod());
			txt11.setText(pdata.getPowerConsumption());
			txt12.setText(pdata.getWarrenty());
			
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
		getMenuInflater().inflate(R.menu.specification_washing_machin, menu);
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
