package com.infotop.eshop.activities;

import java.util.List;

import com.infotop.eshop.R;
import com.infotop.eshop.R.drawable;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;
import com.infotop.eshop.adapters.ProductListAdapter;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.model.Wishlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class WishListMainActivity extends Activity {
	ProductListAdapter listAdapter;
	ListView list;
	String[] productId, productName, productDescription, productPrice;
	Integer[] productImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wish_list_main);
		list = (ListView) findViewById(R.id.wishListViewItems);
		DatabaseHandler db = new DatabaseHandler(WishListMainActivity.this);
		List<Wishlist> cartItems = db.getAllCartListItems();
		int size = cartItems.size();
		productId = new String[size];
		productName = new String[size];
		productDescription = new String[size];
		productPrice = new String[size];
		productImage = new Integer[size];
		for (int i = 0; i < size; i++) {
			productId[i] = cartItems.get(i).getProductId();
			productName[i] = cartItems.get(i).getProductName();
			productDescription[i] = cartItems.get(i).getDescription();
			productPrice[i] = cartItems.get(i).getPrice();
			productImage[i] = R.drawable.productimg;
		}
		listAdapter = new ProductListAdapter(WishListMainActivity.this,
				productName, productImage, productDescription, productPrice);
		list.setAdapter(listAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wish_list_main, menu);
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
