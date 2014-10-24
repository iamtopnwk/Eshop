package com.infotop.eshop.activities;

import com.infotop.eshop.R;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;
import com.infotop.eshop.adapters.CustomListHorizontalAdapter;
import com.infotop.eshop.adapters.HorizontalListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ClothDetailsActivity extends Activity {

	Long position = null;

	// adding CartButton,WishlistButton,BuyButton
	ImageButton cartBtn1;
	ImageButton buyBtn1;
	ImageButton wishlistBtn1;

	HorizontalListView list;

	String[] productName = { "Denim", "T-shirt2", "T-shirt3", "T-shirt4",
			"T-shirt5" };

	Integer[] productImage = { R.drawable.img_tshirt1, R.drawable.img_tshirt2,
			R.drawable.img_tshirt3, R.drawable.img_tshirt4,
			R.drawable.img_tshirt5 };

	String[] productDescription = { "Description1", "Description2",
			"Description3", "Description4", "Description5" };

	String[] productPrice = { "Price: $102", "Price: $110", "Price: $40",
			"Price: $65", "Price: $73" };

	CustomListHorizontalAdapter hAdapter;

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
		hAdapter = new CustomListHorizontalAdapter(this, productName,
				productImage, productPrice);
		// Adapter Object set to a list
		list = (HorizontalListView) findViewById(R.id.listhorizontal);
		list.setAdapter(hAdapter);
		// Click to any item
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// String products = (String) hAdapter.getItem(position);
				// pass Data to other Activity
				Intent i = new Intent(ClothDetailsActivity.this,
						ClothDetailsActivity.class);
				i.putExtra("book_item", position);
				startActivity(i);

				// Toast.makeText(BooksListViewActivity.this, "You Clicked at "
				// +web[+ position], Toast.LENGTH_SHORT).show();
			}
		});
	}

	// functionalities for cartBtn
	public void addToCart(View view) {
		System.out.println("Add Cart Button");

		Toast.makeText(ClothDetailsActivity.this, "Your Item is Added to Cart",
				Toast.LENGTH_SHORT).show();

	}

	// functionalities for buyBtn
	public void buyItem(View view) {
		System.out.println("Add Buy Button");

		Toast.makeText(ClothDetailsActivity.this,
				"your item is booked and go to payment details",
				Toast.LENGTH_SHORT).show();

	}

	// functionalities for wishlistBtn
	public void addToWishlist(View view) {
		System.out.println("Add WishList Button");

		Toast.makeText(ClothDetailsActivity.this,
				"Your item is added to Wish List", Toast.LENGTH_SHORT).show();
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
