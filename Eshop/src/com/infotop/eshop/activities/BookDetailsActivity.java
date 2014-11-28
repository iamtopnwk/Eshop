/*
 * Author: Pabitra
 */

package com.infotop.eshop.activities;

//import com.infotop.eshop.activities.ProductDetailsHorizontalActivity;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.infotop.eshop.R;
import com.infotop.eshop.adapters.CustomListHorizontalAdapter;
import com.infotop.eshop.adapters.HorizontalListView;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.model.Wishlist;
import com.infotop.eshop.utilities.UserSessionManager;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.infotop.bookslistview.BooksListViewActivity;
//import com.infotop.bookslistview.R;

@SuppressLint("SimpleDateFormat")
public class BookDetailsActivity extends Activity {

	// adding CartButton,WishlistButton,BuyButton
	ArrayList<String> s;

	String productIdSpecification;

	private static final int SELECT_PICTURE = 1;

	UserSessionManager usMgr;

	int count=0;

	String childCategoryName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ViewHolder holder;
		final DisplayImageOptions op;
		final ImageLoader loader = ImageLoader.getInstance();
		setContentView(R.layout.activity_book_details);
		op = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.notavailable)
				.showImageForEmptyUri(R.drawable.notavailable)
				.showImageOnFail(R.drawable.notavailable).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();
				
		// Get data from EshopMainActivity
		s = getIntent().getExtras().getStringArrayList("productData");
		childCategoryName=getIntent().getExtras().getString("childCategoryName");
		System.out.println("ChildCategoryName:"+childCategoryName);
		holder = new ViewHolder();
		holder.txtTitle = (TextView) findViewById(R.id.bookName1);
		holder.txtTitle1 = (TextView) findViewById(R.id.authorName);
		holder.txtTitle2 = (TextView) findViewById(R.id.price);
		holder.imageView = (ImageView) findViewById(R.id.logo);
        holder.imageView1=(ImageView) findViewById(R.id.logo1);
        holder.imageView2=(ImageView) findViewById(R.id.logo2);
        holder.imageView3=(ImageView) findViewById(R.id.logo3);
        holder.imageView4=(ImageView) findViewById(R.id.logo4);
		holder.txtTitle.setText(s.get(1));
		holder.txtTitle1.setText(s.get(2));
		holder.txtTitle2.setText(s.get(3));
		/*Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.productimg);
		holder.imageView1.setImageBitmap(bMap);
		holder.imageView2.setImageBitmap(bMap);
		holder.imageView3.setImageBitmap(bMap);
		holder.imageView4.setImageBitmap(bMap);*/
		loader.displayImage(s.get(4), holder.imageView, op, null);

		System.out.println("pabitra id:"+s.get(0));

		
		loader.displayImage(s.get(4), holder.imageView1, op, null);
		loader.displayImage(s.get(5), holder.imageView2, op, null);
		loader.displayImage(s.get(6), holder.imageView3, op, null);
		loader.displayImage(s.get(7), holder.imageView4, op, null);
		
		

		holder.imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(BookDetailsActivity.this, ZoomActivity.class);
            
                ArrayList<String> sl=new ArrayList<String>();
                sl.add(s.get(4));
                sl.add(s.get(5));
                sl.add(s.get(6));
                sl.add(s.get(7));
                i.putStringArrayListExtra("list", sl);
                i.putExtra("count", count);
               
                startActivity(i);
            }
        });
		
		productIdSpecification=s.get(0);
		
		 holder.imageView1.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	count=0;
	            	loader.displayImage(s.get(4), holder.imageView, op, null); 	
             }
         });
		
		 holder.imageView2.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	count=1;
	            	loader.displayImage(s.get(5), holder.imageView, op, null); 	
                }
            });
		 
		 holder.imageView3.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	count=2;
	            	loader.displayImage(s.get(6), holder.imageView, op, null); 	
             }
         });
		 holder.imageView4.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	count=3;
	            	loader.displayImage(s.get(7), holder.imageView, op, null); 	
             }
         });
		
	}
	
	

	private class ViewHolder {
		public TextView txtTitle;
		public TextView txtTitle1;
		public TextView txtTitle2;
		public ImageView imageView;
		public ImageView imageView1;
		public ImageView imageView2;
		public ImageView imageView3;
		public ImageView imageView4;
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
	 public void getSpecification(View view){
		 if("Pendrives".equalsIgnoreCase(childCategoryName)){
		 Intent intSpecification= new Intent(this,SpecificationMouseActivity.class);
		 intSpecification.putExtra("idspec", productIdSpecification);
		 startActivity(intSpecification);
		 }
		 else if("Laptops".equalsIgnoreCase(childCategoryName)){
			 Intent intSpecification= new Intent(this,SpecificationLaptopActivity.class);
			 intSpecification.putExtra("idspec", productIdSpecification);
			 startActivity(intSpecification);
		 }
		 else if("Mouse".equalsIgnoreCase(childCategoryName)){
			 Intent intSpecification= new Intent(this,SpecificationMouseActivity.class);
			 intSpecification.putExtra("idspec", productIdSpecification);
			 startActivity(intSpecification);
		 }
		 else if("Smart Phones".equalsIgnoreCase(childCategoryName)){
			 Intent intSpecification= new Intent(this,SpecificationMobileActivity.class);
			 intSpecification.putExtra("idspec", productIdSpecification);
			 startActivity(intSpecification);
		 }
		 else if("Feature Phones".equalsIgnoreCase(childCategoryName)){
			 Intent intSpecification= new Intent(this,SpecificationMobileActivity.class);
			 intSpecification.putExtra("idspec", productIdSpecification);
			 startActivity(intSpecification);
		 }
		 else if("Sarees".equalsIgnoreCase(childCategoryName)){
			 Intent intSpecification= new Intent(this,SpecificationClothActivity.class);
			 intSpecification.putExtra("idspec", productIdSpecification);
			 startActivity(intSpecification);
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
			
			Wishlist w = new Wishlist();
			w.setProductName(s.get(1));
			Intent sharingIntent = new Intent(Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
	 
	     
			sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Sending from Esop");
			sharingIntent.putExtra(Intent.EXTRA_TEXT, w.getProductName());
	 
	        startActivity(Intent.createChooser(sharingIntent, "Share link!"));
			
			//Wishlist w = new Wishlist();
			
			//sharingIntent.setType("image/*");


			//w.setImageUrl(s.get(0));
			 
			/*String imagePath = Environment.getExternalStorageDirectory()
			            + "w.getImageUrl()";
			
			File imageFileToShare = new File(imagePath);
			Uri screenshotUri = Uri.fromFile(imageFileToShare);
			
			
			sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
			
		
			System.out.println("ooooooooooooooooooooooooooooooo"+screenshotUri);
			startActivity(Intent.createChooser(sharingIntent, "Share image using"));
			        */

			
			return true;
		case R.id.ab_addToCart:
			usMgr = new UserSessionManager(this);
			 if(!usMgr.isUserLoggedIn()){
				 
				 Intent lgn1 = new Intent(this, EshopLoginActivity.class);
				 startActivity(lgn1);
			 } else{
				 DatabaseHandler db1 = new DatabaseHandler(BookDetailsActivity.this);
					Wishlist w1 = new Wishlist();
					w1.setProductId(s.get(0));
					w1.setProductName(s.get(1));
					w1.setDescription(s.get(2));
					w1.setPrice(s.get(3));
					w1.setImageUrl(s.get(4));
					w1.setCreatedDate(new SimpleDateFormat("dd MMM yyyy")
							.format(new Date()));
					db1.addCartList(w1);
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
