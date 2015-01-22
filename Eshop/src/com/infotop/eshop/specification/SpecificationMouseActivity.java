package com.infotop.eshop.specification;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.infotop.eshop.R;
import com.infotop.eshop.model.ProductSpecification;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.GetOperation;
import com.infotop.eshop.utilities.JsonHelper;

public class SpecificationMouseActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specification_mouse);
		
		
/*		 
	String s1=getIntent().getExtras().getString("idspec");
		System.out.println("pabitr spec"+s1);
		
		String serverURL= UrlInfo.SPECIFICATIONBYID+s1;
	AsyncTask<String, Void, String> productSpec=new GetOperation().execute(serverURL);
	 
	try{
	final ProductSpecification pdata=(ProductSpecification) JsonHelper.toObject(productSpec.get(), ProductSpecification.class);
	
	TextView txt1=(TextView)findViewById(R.id.brand);
	TextView txt2=(TextView)findViewById(R.id.modelMouse);
	TextView txt3=(TextView)findViewById(R.id.capacityMouse);
	TextView txt4=(TextView)findViewById(R.id.warrentyMouse);
	
	txt1.setText(pdata.getBrandName());
	txt2.setText(pdata.getModelId());
	txt3.setText(pdata.getCapacity());
	txt4.setText(pdata.getWarrenty());	
	
	
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
