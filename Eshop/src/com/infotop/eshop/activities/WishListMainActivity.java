package com.infotop.eshop.activities;

import java.util.ArrayList;
import java.util.List;

import com.infotop.eshop.R;
import com.infotop.eshop.R.drawable;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;
import com.infotop.eshop.adapters.ProductListAdapter;
import com.infotop.eshop.adapters.WishListAdapter;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.model.Wishlist;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class WishListMainActivity extends Activity {
	String[] productId, productName, productDescription, productPrice,productImage;
	DisplayImageOptions op;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wish_list_main);
		WishListAdapter listAdapter;
		ListView list;
		op = new DisplayImageOptions.Builder()
        .showStubImage(R.drawable.notavailable)
        .showImageForEmptyUri(R.drawable.notavailable)
        .showImageOnFail(R.drawable.notavailable)
        .cacheInMemory()
        .cacheOnDisc()
        .displayer(new RoundedBitmapDisplayer(20))
        .build();
		list = (ListView) findViewById(R.id.wishListViewItems);
		DatabaseHandler db = new DatabaseHandler(WishListMainActivity.this);
		List<Wishlist> wishlistItems = db.getAllWishListItems();
		int size = wishlistItems.size();
		productId = new String[size];
		productName = new String[size];
		productDescription = new String[size];
		productPrice = new String[size];
		productImage = new String[size];
		for (int i = 0; i < size; i++) {
			productId[i] = wishlistItems.get(i).getProductId();
			productName[i] = wishlistItems.get(i).getProductName();
			productDescription[i] = wishlistItems.get(i).getDescription();
			productPrice[i] = wishlistItems.get(i).getPrice();
			productImage[i] = wishlistItems.get(i).getImageUrl();
			System.out.println("Wishlist product Image Url's:"+productImage[i]);
		}
		listAdapter = new WishListAdapter(WishListMainActivity.this,
				productId,productName, productImage, productDescription, productPrice,op);
		list.setAdapter(listAdapter);
		list.setTextFilterEnabled(true);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ArrayList<String> productData = new ArrayList<String>();
				productData.add(productId[position]);
				productData.add(productName[position]);
				productData.add(productDescription[position]);
				productData.add(productPrice[position]);
				productData.add(productImage[position]);
				// String product = (String) adapter.getItem(position);
				// pass Data to other Activity
					Intent i = new Intent(WishListMainActivity.this,
							BookDetailsActivity.class);
					i.putStringArrayListExtra("productData", productData);
					startActivity(i);
			}
		});

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
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		System.gc();
		super.onDestroy();
	}
}
