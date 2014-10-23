package com.infotop.eshop.activities;

import com.infotop.eshop.R;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ClothDetailsActivity extends Activity {

	Long position = null;

	String[] productName = { "Denim", "T-shirt2", "T-shirt3", "T-shirt4",
			"T-shirt5" };

	Integer[] productImage = { R.drawable.img_tshirt1, R.drawable.img_tshirt2,
			R.drawable.img_tshirt3, R.drawable.img_tshirt4,
			R.drawable.img_tshirt5 };

	String[] productDescription = { "Denim Brand is very famous for reliablity", "Description2",
			"Description3", "Description4", "Description5" };

	String[] productPrice = { "Price: $102", "Price: $110", "Price: $40",
			"Price: $65", "Price: $73" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cloth_details);
		Bundle extras = getIntent().getExtras();
		position = (long) extras.getInt("book_item");
		for (int i = 0; i <= productName.length; i++)
			if (i == position) {
				TextView tv = (TextView) findViewById(R.id.prName);
				TextView tv1 = (TextView) findViewById(R.id.prDescription);
				TextView tv2 = (TextView) findViewById(R.id.prPrice);
				ImageView iv = (ImageView) findViewById(R.id.prImage);
				iv.setImageResource(productImage[i]);
				tv.setText(productName[i]);
				tv1.setText(productDescription[i]);
				tv2.setText(productPrice[i]);
			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cloth_details, menu);
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
