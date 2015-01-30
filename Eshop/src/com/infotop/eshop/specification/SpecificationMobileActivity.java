package com.infotop.eshop.specification;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.infotop.eshop.R;
import com.infotop.eshop.main.activity.EshopMainActivity;
import com.infotop.eshop.model.ProductSpecification;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.GetOperation;
import com.infotop.eshop.utilities.JsonHelper;

public class SpecificationMobileActivity extends Activity {
	
	
	
	String s1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specification_mobile);
		
		/*
		s1=getIntent().getExtras().getString("idspec");
		System.out.println("pabitr spec"+s1);
		
		String serverURL=UrlInfo.SPECIFICATIONBYID+s1;
		AsyncTask<String, Void, String> productSpec = new GetOperation().execute(serverURL);
		// Use AsyncTask execute Method To Prevent ANR Problem
		try {
			System.out.println(productSpec.get());
			final ProductSpecification pdata= (ProductSpecification) JsonHelper.toObject(productSpec.get(), ProductSpecification.class);
			
			TextView txt1=(TextView)findViewById(R.id.brand_mobile);
			TextView txt2=(TextView)findViewById(R.id.model_mobile);
			TextView txt3=(TextView)findViewById(R.id.type_mobile);
			TextView txt4=(TextView)findViewById(R.id.simtype_mobile);
			TextView txt5=(TextView)findViewById(R.id.call_features_mobile);
			TextView txt6=(TextView)findViewById(R.id.battery_backup_mobile);
			TextView txt7=(TextView)findViewById(R.id.touchscreen_mobile);
			TextView txt8=(TextView)findViewById(R.id.warrenty_mobile);
			
			txt1.setText(pdata.getBrandName());
			txt2.setText(pdata.getModelId());
			txt3.setText(pdata.getType());
			txt4.setText(pdata.getSimType());
			txt5.setText(pdata.getCallFeatures());
			txt6.setText(pdata.getBatteryBackup());
			txt7.setText(pdata.getTouchScreen());
			txt8.setText(pdata.getWarrenty());
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	

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
		switch (item.getItemId()) {
		case R.id.home_mobile:
			Intent intent=new Intent(SpecificationMobileActivity.this,EshopMainActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
