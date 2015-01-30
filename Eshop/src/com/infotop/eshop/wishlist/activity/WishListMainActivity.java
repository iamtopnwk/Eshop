package com.infotop.eshop.wishlist.activity;

import java.util.concurrent.ExecutionException;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.infotop.eshop.R;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.product.ProductDetailsActivity;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.JsonHelper;
import com.infotop.eshop.utilities.PostOperation;
import com.infotop.eshop.utilities.UserSessionManager;
import com.infotop.eshop.wishlist.adapter.WishListAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class WishListMainActivity extends Activity {
	String[] productId, productName, productDescription, productPrice;
	//productImage;
	DisplayImageOptions op;
	Product pdt=new Product();
	Product[] pdata;
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
		UserSessionManager usMgr = new UserSessionManager(this);
		
		if (usMgr.isUserLoggedIn()) {
		Product pdt = new Product();
		pdt.setServiceUrl(UrlInfo.GET_ALLWISHLIST);
		pdt.setEmailId(usMgr.getUserDetails().get("email"));
		
		AsyncTask<Object, Void, String> data = new PostOperation().execute(pdt);
	
		try {
		    pdata= (Product[]) JsonHelper.toObject(data.get(), Product[].class);
			
			listAdapter = new WishListAdapter(WishListMainActivity.this,pdata,op);
			list.setAdapter(listAdapter);
		
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					

					Intent i = new Intent(WishListMainActivity.this,
							ProductDetailsActivity.class);
					i.putExtra("productId", pdata[position].getUuid());
					startActivity(i);

				}
			});

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else{
			
			DatabaseHandler db=new DatabaseHandler(WishListMainActivity.this);
			final Product[] wishlistItems = db.getAllWishListItems();
			listAdapter = new WishListAdapter(WishListMainActivity.this,wishlistItems,op);
			list.setAdapter(listAdapter);
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					

					Intent i = new Intent(WishListMainActivity.this,
							ProductDetailsActivity.class);
					i.putExtra("productId", wishlistItems[position].getUuid());
					startActivity(i);

				}
			});	
				
		}
			
		

		//System.gc();
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
