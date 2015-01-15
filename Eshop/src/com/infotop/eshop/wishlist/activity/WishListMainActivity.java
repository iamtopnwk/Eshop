package com.infotop.eshop.wishlist.activity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import com.infotop.eshop.model.Product;
import com.infotop.eshop.product.BookDetailsActivity;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.UserSessionManager;
import com.infotop.eshop.wishlist.PostOperation;
import com.infotop.eshop.wishlist.adapter.WishListAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class WishListMainActivity extends Activity {
	private static final String TAG_WISHLIST_ID = "wishlistId";
	private static final String TAG_PID = "productId";
	private static final String TAG_PNAME = "productName";
	private static final String TAG_PDESC = "description";
	private static final String TAG_PPRICE = "price";
	private static final String TAG_IMGURL = "imageUrl";

	String[] wishlistId;
	String[] productId;
	String[] productName;
	String[] productDescription, productPrice, productImage;
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
		UserSessionManager usMgr = new UserSessionManager(this);
		Product pdt = new Product();
		pdt.setServiceUrl(UrlInfo.GET_ALLWISHLIST);
		pdt.setEmailId(usMgr.getUserDetails().get("email"));
		AsyncTask<Object, Void, String> data = new PostOperation().execute(pdt);
		try {
			String responseData = data.get();
			System.out.println("return data:pppppppppppppppppppppppppppppp"
					+ data.get());

			JSONArray jsonArray;
			jsonArray = new JSONArray(responseData);

			wishlistId = new String[jsonArray.length()];
			productId = new String[jsonArray.length()];
			productName = new String[jsonArray.length()];
			productDescription = new String[jsonArray.length()];
			productPrice = new String[jsonArray.length()];
			productImage = new String[jsonArray.length()];
			int size = jsonArray.length();

			for (int i = 0; i < size; i++) {
				JSONObject pc = jsonArray.getJSONObject(i);

				wishlistId[i] = pc.getString(TAG_WISHLIST_ID);
				productId[i] = pc.getString(TAG_PID);
				productName[i] = pc.getString(TAG_PNAME);
				productDescription[i] = pc.getString(TAG_PDESC);
				productPrice[i] = pc.getString(TAG_PPRICE);
				productImage[i] = pc.getString(TAG_IMGURL);
			}
			listAdapter = new WishListAdapter(WishListMainActivity.this,
					wishlistId, productId, productName, productImage,
					productDescription, productPrice, op);
			list.setAdapter(listAdapter);

			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					ArrayList<String> productData = new ArrayList<String>();
					productData.add(productId[position]);
					productData.add(productName[position]);
					productData.add(productDescription[position]);
					productData.add(productPrice[position]);
					productData.add(productImage[position]);
					// String product = (String) adapter.getItem(position);
					// pass Data to other Activity
					System.out.println("productId:-" + productId[position]);

					Intent i = new Intent(WishListMainActivity.this,
							BookDetailsActivity.class);
					i.putExtra("productId", productId[position]);
					// i.putStringArrayListExtra("productData", productData);
					startActivity(i);

				}
			});

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
