package com.infotop.eshop.activities;

//import com.infotop.bookslistview.ProductDetailsActivity;
import com.infotop.eshop.R;
import com.infotop.eshop.R.drawable;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.infotop.bookslistview.BooksListViewActivity;
//import com.infotop.bookslistview.R;


public class ProductDetailsActivity extends Activity {
	
	String bookName=null;
	
	//adding CartButton,WishlistButton,BuyButton
	ImageButton cartBtn1;
	ImageButton buyBtn1;
	ImageButton wishlistBtn1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_details);
   
		//method declaration for corresponding buttons
		addToCart();
		buyItem();
		addToWishlist();
		
		TextView textView = (TextView) findViewById(R.id.bookName1);
		TextView textView1 = (TextView) findViewById(R.id.authorName);
		TextView textView2 = (TextView) findViewById(R.id.description);
		TextView textView3 = (TextView) findViewById(R.id.price);
		ImageView imageView = (ImageView) findViewById(R.id.logo);
		
		//Get  data from EshopMainActivity 
		Bundle extras = getIntent().getExtras();
		bookName=extras.getString("book_item");
		
		System.out.println(bookName);
		if (bookName.equals("Oxford Dictionary")) {
			imageView.setImageResource(R.drawable.ic_book1);
			textView.setText("Oxford Dictionary");
			textView1.setText("Author:Pabitra");
			textView2.setText("Description: It's a famous Dictionary");
			textView3.setText("Price: $72");
		}
	}

//functionalities for cartBtn	
public void addToCart(){
		
		cartBtn1=(ImageButton) findViewById(R.id.cartBtn);
		
		cartBtn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(ProductDetailsActivity.this,"Your Item is Added to Cart", Toast.LENGTH_SHORT).show();
				
			}
		});
	}
// functionalities for buyBtn	
	public void buyItem(){
		
		buyBtn1=(ImageButton) findViewById(R.id.buyBtn);
		buyBtn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

               Toast.makeText(ProductDetailsActivity.this,"your item is booked and go to payment details",Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}
	//  functionalities for wishlistBtn  
	public void addToWishlist(){
		
		wishlistBtn1=(ImageButton) findViewById(R.id.wishlistBtn);
		wishlistBtn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Toast.makeText(ProductDetailsActivity.this,"Your item is added to Wish List",Toast.LENGTH_SHORT).show();
			}
		});
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
