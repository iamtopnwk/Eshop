/*
 * Author: Pabitra
 */

package com.infotop.eshop.activities;

//import com.infotop.eshop.activities.ProductDetailsHorizontalActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.infotop.eshop.R;
import com.infotop.eshop.Utilities.UserSessionManager;
import com.infotop.eshop.adapters.CustomListHorizontalAdapter;
import com.infotop.eshop.adapters.HorizontalListView;
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
	//ImageButton wishlistBtn1;
	ArrayList<String> s;
	DisplayImageOptions op;
	UserSessionManager usMgr;
	protected ImageLoader loader = ImageLoader.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ViewHolder holder;
		setContentView(R.layout.activity_book_details);
		op = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.notavailable)
				.showImageForEmptyUri(R.drawable.notavailable)
				.showImageOnFail(R.drawable.notavailable).cacheInMemory()
				.cacheOnDisc().displayer(new RoundedBitmapDisplayer(20))
				.build();
		// Get data from EshopMainActivity
		s = getIntent().getExtras().getStringArrayList("productData");
		holder = new ViewHolder();
		holder.txtTitle = (TextView) findViewById(R.id.bookName1);
		holder.txtTitle1 = (TextView) findViewById(R.id.authorName);
		holder.txtTitle2 = (TextView) findViewById(R.id.price);
		holder.imageView = (ImageView) findViewById(R.id.logo);

		holder.txtTitle.setText(s.get(1));
		holder.txtTitle1.setText(s.get(2));
		holder.txtTitle2.setText(s.get(3));
		loader.displayImage(s.get(4), holder.imageView, op, null);

	}

	private class ViewHolder {
		public TextView txtTitle;
		public TextView txtTitle1;
		public TextView txtTitle2;
		public ImageView imageView;
	}

	// functionalities for wishlistBtn
	public void addToWishlist(View view) {
		
		UserSessionManager usMgr = new UserSessionManager(BookDetailsActivity.this);
		 if(usMgr.isUserLoggedIn()){
		DatabaseHandler db = new DatabaseHandler(BookDetailsActivity.this);
		Wishlist w = new Wishlist();
		w.setProductId(s.get(0));
		w.setProductName(s.get(1));
		w.setDescription(s.get(2));
		w.setPrice(s.get(3));
		w.setImageUrl(s.get(4));
		w.setCreatedDate(new SimpleDateFormat("dd MMM yyyy").format(new Date()));

		db.addWishList(w);
		System.out.println("Add WishList Button");

		Toast.makeText(BookDetailsActivity.this,
				"Your item is added to Wish List", Toast.LENGTH_SHORT).show();
	}else{
		 Intent intent = new Intent(BookDetailsActivity.this,EshopLoginActivity.class);
			startActivity(intent);
		
	 }
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
		switch (item.getItemId()) {
		case R.id.ab_abShareApp1:
			return true;
		case R.id.ab_addToCart:
			usMgr = new UserSessionManager(this);
			 if(!usMgr.isUserLoggedIn()){
				 
				 Intent lgn1 = new Intent(this, NoItemFoundActivity.class);
				 startActivity(lgn1);
			 } else{
				 DatabaseHandler db = new DatabaseHandler(BookDetailsActivity.this);
					Wishlist w = new Wishlist();
					w.setProductId(s.get(0));
					w.setProductName(s.get(1));
					w.setDescription(s.get(2));
					w.setPrice(s.get(3));
					w.setImageUrl(s.get(4));
					w.setCreatedDate(new SimpleDateFormat("dd MMM yyyy")
							.format(new Date()));
					db.addCartList(w);
					Toast.makeText(BookDetailsActivity.this,
							"Your Item is Added to Cart", Toast.LENGTH_SHORT).show();
			 }
			
			return true;
		case R.id.ab_purChaseItem:
			UserSessionManager us = new UserSessionManager(this);
			Boolean result = us.checkLogin();
			if (result == false) {
				Intent in = new Intent(BookDetailsActivity.this,
						PaymentMainActivity.class);
				in.putStringArrayListExtra("purChaseItem", s);
				startActivity(in);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
