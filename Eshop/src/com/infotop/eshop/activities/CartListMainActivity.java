package com.infotop.eshop.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.infotop.eshop.R;
import com.infotop.eshop.R.drawable;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;
import com.infotop.eshop.adapters.CartListAdapter;
import com.infotop.eshop.adapters.ProductListAdapter;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.model.Wishlist;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class CartListMainActivity extends Activity {
	CartListAdapter listAdapter;
	ListView list;
	String[] productId, productName, productDescription, productPrice;
	String[] productImage;
	protected ImageLoader loader = ImageLoader.getInstance();
	DisplayImageOptions op;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart_list_main);
		
	//	CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
		
		op = new DisplayImageOptions.Builder()
        .showStubImage(R.drawable.notavailable)
        .showImageForEmptyUri(R.drawable.notavailable)
        .showImageOnFail(R.drawable.notavailable)
        .cacheInMemory()
        .cacheOnDisc()
        .displayer(new RoundedBitmapDisplayer(20))
        .build();
		list = (ListView) findViewById(R.id.cartListViewItems);
		DatabaseHandler db = new DatabaseHandler(CartListMainActivity.this);
		List<Wishlist> cartItems = db.getAllCartListItems();
		int size = cartItems.size();
		productId = new String[size];
		productName = new String[size];
		productDescription = new String[size];
		productPrice = new String[size];
		productImage = new String[size];
		for (int i = 0; i < size; i++) {
			productId[i] = cartItems.get(i).getProductId();
			productName[i] = cartItems.get(i).getProductName();
			productDescription[i] = cartItems.get(i).getDescription();
			productPrice[i] = cartItems.get(i).getPrice();
			productImage[i] = cartItems.get(i).getImageUrl();
			System.out.println("Cart product Image Url's:"+productImage[i]);
		}
		listAdapter = new CartListAdapter(CartListMainActivity.this,
				productId,productName, productImage, productDescription, productPrice,op);
		list.setAdapter(listAdapter);
		list.setTextFilterEnabled(true);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				 listAdapter = (CartListAdapter) parent.getAdapter();
				
				 
				
				
				ArrayList<String> productData = new ArrayList<String>();
				productData.add(productId[position]);
				productData.add(productName[position]);
				productData.add(productDescription[position]);
				productData.add(productPrice[position]);
				productData.add(productImage[position]);
				// String product = (String) adapter.getItem(position);
				// pass Data to other Activity
					Intent i = new Intent(CartListMainActivity.this,
							BookDetailsActivity.class);
					i.putStringArrayListExtra("productData", productData);
					startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cart_list_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		
		case R.id.ab_deleteFromCart:
			DatabaseHandler db = new DatabaseHandler(CartListMainActivity.this);
			Wishlist w = new Wishlist();
			//CartListAdapter bAdapter;    
			
			//db.addCartList(w);
			/*w.setProductId(s.get(0));
			w.setProductName(s.get(1));
			w.setDescription(s.get(2));
			w.setPrice(s.get(3));
			w.setImageUrl(s.get(4));
			w.setCreatedDate(new SimpleDateFormat("dd MMM yyyy").format(new Date()));
			db.addCartList(w);
			Toast.makeText(BookDetailsActivity.this, "Your Item is Added to Cart",
					Toast.LENGTH_SHORT).show();*/
			return true;
		case R.id.ab_purChaseItem:
			Intent in=new Intent(CartListMainActivity.this,PaymentMainActivity.class);
			//in.putStringArrayListExtra("purChaseItem", list);
			startActivity(in);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}