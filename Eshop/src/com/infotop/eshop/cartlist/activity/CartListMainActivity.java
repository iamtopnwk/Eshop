package com.infotop.eshop.cartlist.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.infotop.eshop.R;
import com.infotop.eshop.cartlist.adapter.CartListAdapter;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.db.Wishlist;
import com.infotop.eshop.httpservice.HttpUrl;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.payment.PaymentMainActivity;
import com.infotop.eshop.product.BookDetailsActivity;
import com.infotop.eshop.utilities.UserSessionManager;
import com.infotop.eshop.wishlist.PostOperation;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class CartListMainActivity extends Activity {
	private static final String TAG_CARTLIST_ID="cartlistId";
	private static final String TAG_PID = "productId";
	private static final String TAG_PNAME = "productName";
	private static final String TAG_PDESC = "description";
	private static final String TAG_PPRICE = "price";
    private static final String TAG_IMGURL = "imageUrl";
	CartListAdapter listAdapter;
	String[] productId, productName, productDescription, productPrice,
			productImage,cartListId;
	String grandTotal;
	int position;
	Double totalAmount;
	DisplayImageOptions op;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart_list_main);
		ListView list;
		
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
		UserSessionManager usMgr=new UserSessionManager(this);
		Product pdt=new Product();
		pdt.setServiceUrl(new HttpUrl().getUrl()
				+ "/eshop/rest/getAllCartlistItemsByAccount");
		pdt.setEmailId(usMgr.getUserDetails().get("email"));
		
		AsyncTask<Object, Void, String> cartListData=new PostOperation().execute(pdt);
		String pcontent;
		
		try {
			pcontent=cartListData.get();
			System.out.println("return data:"+pcontent);
			JSONArray jsonArray=new JSONArray(pcontent);
			
			productId=new String[jsonArray.length()];
			cartListId=new String[jsonArray.length()];
			productName=new String[jsonArray.length()];
			productDescription=new String[jsonArray.length()];
			productPrice=new String[jsonArray.length()];
			productImage = new String[jsonArray.length()];
			
			System.out.println("js:"+jsonArray .length());
			
			totalAmount = 0D;
			for(int i=0;i<jsonArray.length();i++){
				JSONObject pc=jsonArray.getJSONObject(i);
				
				System.out.println("js1:"+pc);
                
				productId[i] = pc.getString(TAG_PID);
				cartListId[i]=pc.getString(TAG_CARTLIST_ID);
				productName[i] = pc.getString(TAG_PNAME);
				productDescription[i] = pc.getString(TAG_PDESC);
				productPrice[i] = pc.getString(TAG_PPRICE);
				
				productImage[i] = pc.getString(TAG_IMGURL);
				totalAmount=totalAmount
						+ Double.valueOf(pc.getString(TAG_PPRICE));
				
			}
				
			listAdapter = new CartListAdapter(CartListMainActivity.this, productId,cartListId,
				productName, productImage, productDescription, productPrice, op);
		list.setAdapter(listAdapter);
		list.setTextFilterEnabled(true);

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				listAdapter = (CartListAdapter) parent.getAdapter();

				ArrayList<String> productData = new ArrayList<String>();
				productData.add(productId[position]);
				productData.add(productName[position]);
				productData.add(productDescription[position]);
				productData.add(productPrice[position]);
				productData.add(productImage[position]);
				// String product = (String) adapter.getItem(position);
				// pass Data to other Activity
				System.out.println("productID:-"+productId[position]);
				
				Intent i = new Intent(CartListMainActivity.this,
						BookDetailsActivity.class);
				i.putExtra("productId", productId[position]);
				//i.putStringArrayListExtra("productData", productData);
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
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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