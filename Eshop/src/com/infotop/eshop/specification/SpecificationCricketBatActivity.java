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
import android.widget.TextView;

public class SpecificationCricketBatActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specification_cricket_bat);
		
		/*String s1 = getIntent().getExtras().getString("idspec");
		System.out.println("pabitr spec"+s1);
		
		String serverURL=UrlInfo.SPECIFICATIONBYID+s1;
		//new LongOperation().execute(serverURL);
	
	 AsyncTask<String, Void, String> productSpec = new GetOperation().execute(serverURL);
		// Use AsyncTask execute Method To Prevent ANR Problem
		try {
			System.out.println(productSpec.get());
			final ProductSpecification pdata= (ProductSpecification) JsonHelper.toObject(productSpec.get(), ProductSpecification.class);
			
			TextView txt1=(TextView)findViewById(R.id.sport_type_cricket);
			TextView txt2=(TextView)findViewById(R.id.playing_level_cricket);
			TextView txt3=(TextView)findViewById(R.id.handle_length_cricket);
			TextView txt4=(TextView)findViewById(R.id.weight_cricket);
			TextView txt5=(TextView)findViewById(R.id.blade_height_cricket);
			TextView txt6=(TextView)findViewById(R.id.height_cricket);
			TextView txt7=(TextView)findViewById(R.id.width_cricket);
			TextView txt8=(TextView)findViewById(R.id.blade_material_cricket);
			TextView txt9=(TextView)findViewById(R.id.handle_materia_cricket);
			TextView txt10=(TextView)findViewById(R.id.toe_guard_cricket);
			TextView txt11=(TextView)findViewById(R.id.handle_grip_type_cricket);
			
			txt1.setText(pdata.getSportType());
			txt2.setText(pdata.getPlayingLevel());
			txt3.setText(pdata.getHandleLength());
			txt4.setText(pdata.getWeight());
			txt5.setText(pdata.getBladeHeight());
			txt6.setText(pdata.getHeight());
			txt7.setText(pdata.getWidth());
			txt8.setText(pdata.getBladeMaterial());
			txt9.setText(pdata.getHandleMaterial());
			txt10.setText(pdata.getToeGuard());
			txt11.setText(pdata.getHandleGripType());
			
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
		getMenuInflater().inflate(R.menu.specification_cricket_bat, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.home_cricket:
			Intent intent=new Intent(SpecificationCricketBatActivity.this,EshopMainActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
