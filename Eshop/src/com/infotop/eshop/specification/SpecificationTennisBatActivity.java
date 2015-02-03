package com.infotop.eshop.specification;

import java.util.concurrent.ExecutionException;

import com.infotop.eshop.R;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;
import com.infotop.eshop.main.activity.EshopMainActivity;
import com.infotop.eshop.model.ProductSpecification;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.GetOperation;
import com.infotop.eshop.utilities.JsonHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class SpecificationTennisBatActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specification_tennis_bat);
		
		
		/*String s1 = getIntent().getExtras().getString("idspec");
		System.out.println("pabitr spec"+s1);
		
		String serverURL=UrlInfo.SPECIFICATIONBYID+s1;
		//new LongOperation().execute(serverURL);
	
	 AsyncTask<String, Void, String> productSpec = new GetOperation().execute(serverURL);
		// Use AsyncTask execute Method To Prevent ANR Problem
		try {
			System.out.println(productSpec.get());
			final ProductSpecification pdata= (ProductSpecification) JsonHelper.toObject(productSpec.get(), ProductSpecification.class);
			
			TextView txt1=(TextView)findViewById(R.id.sport_type_tennis);
			TextView txt2=(TextView)findViewById(R.id.playing_level_tennis);
			TextView txt3=(TextView)findViewById(R.id.strung_tennis);
			TextView txt4=(TextView)findViewById(R.id.age_group);
			TextView txt5=(TextView)findViewById(R.id.flexibility_tennis);
			TextView txt6=(TextView)findViewById(R.id.type_tennis);
			TextView txt7=(TextView)findViewById(R.id.series_tennis);
			TextView txt8=(TextView)findViewById(R.id.cover_tennis);
			TextView txt9=(TextView)findViewById(R.id.balance_tennis);
			TextView txt10=(TextView)findViewById(R.id.beam_width_tennis);
			TextView txt11=(TextView)findViewById(R.id.height_tennis);
			TextView txt12=(TextView)findViewById(R.id.body_material_tennis);
			TextView txt13=(TextView)findViewById(R.id.head_size_tennis);
			
			txt1.setText(pdata.getSportType());
			txt2.setText(pdata.getPlayingLevel());
			txt3.setText(pdata.getStrung());
			txt4.setText(pdata.getAgeGroup());
			txt5.setText(pdata.getFlexibility());
			txt6.setText(pdata.getType());
			txt7.setText(pdata.getSeries());
			txt8.setText(pdata.getCover());
			txt9.setText(pdata.getBalance());
			txt10.setText(pdata.getBeamWidth());
			txt11.setText(pdata.getHeight());
			txt12.setText(pdata.getBodyMaterial());
			txt13.setText(pdata.getHeadSize());
			
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
		getMenuInflater().inflate(R.menu.specification_tennis_bat, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.home_tennis:
			Intent intent=new Intent(SpecificationTennisBatActivity.this,EshopMainActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
