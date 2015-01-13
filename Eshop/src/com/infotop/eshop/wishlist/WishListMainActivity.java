package com.infotop.eshop.wishlist;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.infotop.eshop.R;
import com.infotop.eshop.R.drawable;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.db.Wishlist;
import com.infotop.eshop.httpservice.HttpUrl;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.product.BookDetailsActivity;
import com.infotop.eshop.product.ProductListAdapter;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class WishListMainActivity extends Activity {
	String[] productId, productName, productDescription, productPrice,
			productImage;
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
				.showImageOnFail(R.drawable.notavailable).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();

		list = (ListView) findViewById(R.id.wishListViewItems);
		Product pdt=new Product();
		pdt.setServiceUrl(new HttpUrl().getUrl()
				+ "/eshop/rest/getAllWishlistItemsByAccount");
		pdt.setEmailId("test@info.com");
		AsyncTask<Object, Void, String> data=new PostOperation().execute(pdt);
		try {
			System.out.println("return data:"+data.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*DatabaseHandler db = new DatabaseHandler(WishListMainActivity.this);
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
			System.out.println("Wishlist product Image Url's:"
					+ productImage[i]);
		}*/
		
		listAdapter = new WishListAdapter(WishListMainActivity.this, productId,
				productName, productImage, productDescription, productPrice, op);
		list.setAdapter(listAdapter);
		list.setTextFilterEnabled(true);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/*ArrayList<String> productData = new ArrayList<String>();
				productData.add(productId[position]);
				productData.add(productName[position]);
				productData.add(productDescription[position]);
				productData.add(productPrice[position]);
				productData.add(productImage[position]);
				// String product = (String) adapter.getItem(position);
				// pass Data to other Activity
				System.out.println("productId:-"+productId[position]);
				*/
				Intent i = new Intent(WishListMainActivity.this,
						BookDetailsActivity.class);
				i.putExtra("productId", productId[position]);
				//i.putStringArrayListExtra("productData", productData);
				startActivity(i);
				
			}
		});
		System.gc();
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
