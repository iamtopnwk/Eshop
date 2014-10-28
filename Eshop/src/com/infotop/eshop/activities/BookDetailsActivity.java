package com.infotop.eshop.activities;

//import com.infotop.eshop.activities.ProductDetailsHorizontalActivity;
import java.util.ArrayList;

import com.infotop.eshop.R;
import com.infotop.eshop.adapters.CustomListHorizontalAdapter;
import com.infotop.eshop.adapters.HorizontalListView;

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

	HorizontalListView list;
	String[] web = { "Oxford Dictionary", "Telugu Dictionary",
			"Oriya Dictionary", "Oracle 10g", "C I O", "FootSteps",
			"Church & Home", "Kill Zumbiles", "Doctor's Guide", "Oil Traders",
			"Mordern Love", "Flex Youth ", "Physics", "Multiply Bussiness",
			"Money Savers", "It's your Time", "Redeployed", "Simple Learning",
			"Still Soul", "BareFoot", "Miracle Morning", "Hooked",
			"Blase Flaming", "Creating Games", "#D Book Mock up",
			"Never Look back", "Ebola", "Transformation", "Game Art Me" };

	Integer[] imageId = { R.drawable.ic_book1, R.drawable.ic_book2,
			R.drawable.ic_book3, R.drawable.ic_book4, R.drawable.ic_book5,
			R.drawable.ic_book6, R.drawable.ic_book7, R.drawable.ic_book8,
			R.drawable.ic_book9, R.drawable.ic_book10, R.drawable.ic_book11,
			R.drawable.ic_book12, R.drawable.ic_book13, R.drawable.ic_book14,
			R.drawable.ic_book15, R.drawable.ic_book16, R.drawable.ic_book17,
			R.drawable.ic_book18, R.drawable.ic_book19, R.drawable.ic_book20,
			R.drawable.ic_book21, R.drawable.ic_book22, R.drawable.ic_book23,
			R.drawable.ic_book24, R.drawable.ic_book25, R.drawable.ic_book26,
			R.drawable.ic_book27, R.drawable.ic_book28, R.drawable.ic_book29 };

	String[] author = { "Author:Pabitra", "Author:Balchander",
			"Author: S.K Dutta", "Author: Devid", "Author: Dephenar L",
			"Author: Rajesh P", "Author: Rakesh Menon", "Author: Snthil Kumar",
			"Author: PrabaKaran", "Author: Rajesh", "Author: Arun K.",
			"Author:Tian Xiangly", "Author: Mrs Leo", "Author: Chian Yang",
			"Author: Sudeep ", "Author: Summy Darien", "Author:Petor Burg",
			"Author: L.Kluzner", "Author: P.Sepherd", "Author: Sakil",
			"Author: L.M Nahama", "Author: S.Babu", "Author: N Murthy",
			"Author: S.pati", "Author: Z.Nelson", "Author: P.S Panda",
			"Author: J K Mantri", "Author: J.M Tripathy", "Author: R.K Burma" };

	String[] price = { "Price: $102", "Price: $110", "Price: $40",
			"Price: $65", "Price: $73", "Price: $86", "Price: $89",
			"Price: $320", "Price: $326", "Price: $560", "Price: $143",
			"Price: $150", "Price: $120", "Price: $430", "Price: $210",
			"Price: $540", "Price: $15", "Price: $45", "Price: $42",
			"Price: $75", "Price: $65", "Price: $143", "Price: $320",
			"Price: $70", "Price: $156", "Price: $436", "Price: $210",
			"Price: $301", "Price: $430" };

	CustomListHorizontalAdapter hAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_details);
		// Get data from EshopMainActivity
		ArrayList<String> s=getIntent().getExtras().getStringArrayList("productData");
		System.out.println("Product Name:"+ s.get(0));
		TextView tv = (TextView) findViewById(R.id.bookName1);
		TextView tv1 = (TextView) findViewById(R.id.authorName);
		TextView tv2 = (TextView) findViewById(R.id.price);
		ImageView iv = (ImageView) findViewById(R.id.logo);
		tv.setText(s.get(1));
		tv1.setText(s.get(2));
		tv2.setText(s.get(3));
		iv.setImageResource(Integer.valueOf(s.get(4)));
		
		hAdapter = new CustomListHorizontalAdapter(this, web, imageId, price);
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
				Intent i = new Intent(BookDetailsActivity.this,
						BookDetailsActivity.class);
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
