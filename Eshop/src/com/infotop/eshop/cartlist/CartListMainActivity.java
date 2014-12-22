package com.infotop.eshop.cartlist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.infotop.eshop.R;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.db.Wishlist;
import com.infotop.eshop.payment.PaymentMainActivity;
import com.infotop.eshop.product.BookDetailsActivity;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class CartListMainActivity extends Activity {
	CartListAdapter listAdapter;
	String[] productId, productName, productDescription, productPrice,
			productImage;
	String grandTotal;
	int position;
	Double totalAmount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart_list_main);
		ListView list;
		DisplayImageOptions op;
		// ArrayList<String> s;
		TextView grand_Total;
		// CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);

		op = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.notavailable)
				.showImageForEmptyUri(R.drawable.notavailable)
				.showImageOnFail(R.drawable.notavailable).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
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
		totalAmount = 0D;
		for (int i = 0; i < size; i++) {
			productId[i] = cartItems.get(i).getProductId();
			productName[i] = cartItems.get(i).getProductName();
			productDescription[i] = cartItems.get(i).getDescription();
			productPrice[i] = cartItems.get(i).getPrice();
			totalAmount = totalAmount
					+ Double.valueOf(cartItems.get(i).getPrice());
			productImage[i] = cartItems.get(i).getImageUrl();
			System.out.println("Cart product Image Url's:" + productImage[i]);
		}

		listAdapter = new CartListAdapter(CartListMainActivity.this, productId,
				productName, productImage, productDescription, productPrice, op);
		list.setAdapter(listAdapter);
		list.setTextFilterEnabled(true);

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				listAdapter = (CartListAdapter) parent.getAdapter();

				/*ArrayList<String> productData = new ArrayList<String>();
				productData.add(productId[position]);
				productData.add(productName[position]);
				productData.add(productDescription[position]);
				productData.add(productPrice[position]);
				productData.add(productImage[position]);
				// String product = (String) adapter.getItem(position);
				// pass Data to other Activity
				System.out.println("productID:-"+productId[position]);
				*/
				Intent i = new Intent(CartListMainActivity.this,
						BookDetailsActivity.class);
				i.putExtra("productId", productId[position]);
				//i.putStringArrayListExtra("productData", productData);
				startActivity(i);
			}
		});

		grand_Total = (TextView) findViewById(R.id.grand_total);

		System.out.println(totalAmount);
		grand_Total.setText(totalAmount.toString());
		
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

		// case R.id.ab_deleteFromCart:
		// DatabaseHandler db = new DatabaseHandler(CartListMainActivity.this);
		// Wishlist w = new Wishlist();
		// CartListAdapter bAdapter;

		// db.addCartList(w);
		/*
		 * w.setProductId(s.get(0)); w.setProductName(s.get(1));
		 * w.setDescription(s.get(2)); w.setPrice(s.get(3));
		 * w.setImageUrl(s.get(4)); w.setCreatedDate(new
		 * SimpleDateFormat("dd MMM yyyy").format(new Date()));
		 * db.addCartList(w); Toast.makeText(BookDetailsActivity.this,
		 * "Your Item is Added to Cart", Toast.LENGTH_SHORT).show();
		 */
		// return true;
		case R.id.ab_purChaseItem:
			grandTotal = totalAmount.toString();
			String allAmount = "Total Amount";
			ArrayList<String> s1 = new ArrayList<String>();
			s1.add(productId[position]);
			s1.add(allAmount);
			s1.add(grandTotal);

			Intent in = new Intent(CartListMainActivity.this,
					PaymentMainActivity.class);
			in.putStringArrayListExtra("cartItemsBuy", s1);
			startActivity(in);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}