package com.infotop.eshop.activities;

import com.infotop.eshop.R;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;
import com.infotop.eshop.adapters.CustomGridViewAdapter;
import com.infotop.eshop.adapters.CustomListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;

public class ClothsMainActivity extends Activity {

	GridView grid;
	String[] productName = { "T-shirt1", "T-shirt2",
			"T-shirt3", "T-shirt4", "T-shirt5"};

	Integer[] productImage = { R.drawable.img_tshirt1, R.drawable.img_tshirt2,
			R.drawable.img_tshirt3, R.drawable.img_tshirt4, R.drawable.img_tshirt5};

	String[] productDescription = { "Description1", "Description2",
			"Description3", "Description4", "Description5"};

	String[] productPrice = { "Price: $102", "Price: $110", "Price: $40",
			"Price: $65", "Price: $73"};
	
	CustomGridViewAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cloths_main);
		adapter = new CustomGridViewAdapter(this, productName, productImage, productDescription, productPrice);
		// Adapter Object set to a list
		grid = (GridView) findViewById(R.id.clothsgridView);
		grid.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cloths_main, menu);
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
