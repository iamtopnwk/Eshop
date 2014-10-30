/*
 * Author: Pabitra
 */

package com.infotop.eshop.activities;

//import com.infotop.eshop.activities.ProductDetailsHorizontalActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.infotop.eshop.R;
import com.infotop.eshop.adapters.CustomListHorizontalAdapter;
import com.infotop.eshop.adapters.HorizontalListView;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.model.Wishlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.infotop.bookslistview.BooksListViewActivity;
//import com.infotop.bookslistview.R;

public class BookDetailsActivity extends Activity {

	Long position = null;

	// adding CartButton,WishlistButton,BuyButton
	ImageButton cartBtn1;
	ImageButton buyBtn1;
	ImageButton wishlistBtn1;
	ArrayList<String> s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_details);
		// Get data from EshopMainActivity
		s = getIntent().getExtras().getStringArrayList("productData");
		System.out.println("Product Name:" + s.get(0));
		TextView tv = (TextView) findViewById(R.id.bookName1);
		TextView tv1 = (TextView) findViewById(R.id.authorName);
		TextView tv2 = (TextView) findViewById(R.id.price);
		ImageView iv = (ImageView) findViewById(R.id.logo);
		tv.setText(s.get(1));
		tv1.setText(s.get(2));
		tv2.setText(s.get(3));
		iv.setImageResource(Integer.valueOf(s.get(4)));

	}

	// functionalities for cartBtn
	public void addToCart(View view) {
		System.out.println("Add Cart Button");
		DatabaseHandler db = new DatabaseHandler(BookDetailsActivity.this);
		Wishlist w = new Wishlist();
		w.setProductId(s.get(0));
		w.setProductName(s.get(1));
		w.setDescription(s.get(2));
		w.setPrice(s.get(3));
		w.setCreatedDate(new SimpleDateFormat("dd MMM yyyy").format(new Date()));
		db.addCartList(w);
		Toast.makeText(BookDetailsActivity.this, "Your Item is Added to Cart",
				Toast.LENGTH_SHORT).show();

	}

	// functionalities for buyBtn
	public void buyItem(View view) {
		System.out.println("Add Buy Button");

		Toast.makeText(BookDetailsActivity.this,
				"your item is booked and go to payment details",
				Toast.LENGTH_SHORT).show();

	}

	// functionalities for wishlistBtn
	public void addToWishlist(View view) {

		DatabaseHandler db = new DatabaseHandler(BookDetailsActivity.this);
		Wishlist w = new Wishlist();
		w.setProductId(s.get(0));
		w.setProductName(s.get(1));
		w.setDescription(s.get(2));
		w.setPrice(s.get(3));
		w.setCreatedDate(new SimpleDateFormat("dd MMM yyyy").format(new Date()));

		db.addWishList(w);
		System.out.println("Add WishList Button");

		Toast.makeText(BookDetailsActivity.this,
				"Your item is added to Wish List", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_list_main, menu);
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
