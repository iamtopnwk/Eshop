package com.infotop.eshop.activities;

//import com.infotop.bookslistview.ProductDetailsActivity;
import com.infotop.eshop.R;
import com.infotop.eshop.R.drawable;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.adapters.CustomListAdapter;

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

public class ProductDetailsActivity extends Activity {

	String bookName = null;

	// adding CartButton,WishlistButton,BuyButton
	ImageButton cartBtn1;
	ImageButton buyBtn1;
	ImageButton wishlistBtn1;

	ListView list;
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

	CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_details);

		// method declaration for corresponding buttons
		addToCart();
		buyItem();
		addToWishlist();

		TextView textView = (TextView) findViewById(R.id.bookName1);
		TextView textView1 = (TextView) findViewById(R.id.authorName);
		TextView textView2 = (TextView) findViewById(R.id.description);
		TextView textView3 = (TextView) findViewById(R.id.price);
		ImageView imageView = (ImageView) findViewById(R.id.logo);

		// Get data from EshopMainActivity
		Bundle extras = getIntent().getExtras();
		bookName = extras.getString("book_item");

		System.out.println(bookName);
		if (bookName.equals("Oxford Dictionary")) {
			imageView.setImageResource(R.drawable.ic_book1);
			textView.setText("Oxford Dictionary");
			textView1.setText("Author:Pabitra");
			textView2.setText("Description: It's a famous Dictionary");
			textView3.setText("Price: $72");
		}

		adapter = new CustomListAdapter(this, web, imageId, author, price);
		// Adapter Object set to a list
		list = (ListView) findViewById(R.id.listhorizontal);
		list.setAdapter(adapter);
		// Click to any item
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String product = (String) adapter.getItem(position);
				// pass Data to other Activity
				Intent i = new Intent(ProductDetailsActivity.this,
						ProductDetailsActivity.class);
				i.putExtra("book_item", product);
				startActivity(i);

				// Toast.makeText(BooksListViewActivity.this, "You Clicked at "
				// +web[+ position], Toast.LENGTH_SHORT).show();
			}
		});

	}

	// functionalities for cartBtn
	public void addToCart() {

		cartBtn1 = (ImageButton) findViewById(R.id.cartBtn);

		cartBtn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(ProductDetailsActivity.this,
						"Your Item is Added to Cart", Toast.LENGTH_SHORT)
						.show();

			}
		});
	}

	// functionalities for buyBtn
	public void buyItem() {

		buyBtn1 = (ImageButton) findViewById(R.id.buyBtn);
		buyBtn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Toast.makeText(ProductDetailsActivity.this,
						"your item is booked and go to payment details",
						Toast.LENGTH_SHORT).show();

			}
		});

	}

	// functionalities for wishlistBtn
	public void addToWishlist() {

		wishlistBtn1 = (ImageButton) findViewById(R.id.wishlistBtn);
		wishlistBtn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Toast.makeText(ProductDetailsActivity.this,
						"Your item is added to Wish List", Toast.LENGTH_SHORT)
						.show();
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
