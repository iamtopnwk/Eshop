package com.infotop.eshop.cartlist.activity;

import java.util.ArrayList;
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
import android.widget.TextView;

import com.infotop.eshop.R;
import com.infotop.eshop.cartlist.adapter.CartListAdapter;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.payment.PaymentMainActivity;
import com.infotop.eshop.product.ProductDetailsActivity;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.JsonHelper;
import com.infotop.eshop.utilities.PostOperation;
import com.infotop.eshop.utilities.UserSessionManager;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class CartListMainActivity extends Activity {
	
	CartListAdapter listAdapter;
    int position;
	DisplayImageOptions op;
	Double totalAmount;
	String grandTotal;
	Product[] pdata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart_list_main);
		ListView list;
         TextView grand_Total;
	
		op = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.notavailable)
				.showImageForEmptyUri(R.drawable.notavailable)
				.showImageOnFail(R.drawable.notavailable).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();

		list = (ListView) findViewById(R.id.cartListViewItems);
		UserSessionManager usMgr = new UserSessionManager(this);
	if (usMgr.isUserLoggedIn()) {
		
        Product pdt = new Product();
		pdt.setServiceUrl(UrlInfo.GET_ALLCARTLIST);
		pdt.setEmailId(usMgr.getUserDetails().get("email"));

		AsyncTask<Object, Void, String> cartListData = new PostOperation().execute(pdt);
          
		try {
			pdata= (Product[]) JsonHelper.toObject(cartListData.get(), Product[].class);

			totalAmount=0D;
			for(int i=0;i<pdata.length;i++){
				System.out.println("Idddddddd:"+pdata.length);
			
			System.out.println("Idddddddd:"+pdata[i].getProductPrice());
				totalAmount = totalAmount
						+ Double.valueOf(pdata[i].getProductPrice());
				System.out.println("totalAmount====="+totalAmount);
			}

			listAdapter = new CartListAdapter(CartListMainActivity.this,pdata, op);
			list.setAdapter(listAdapter);
			list.setTextFilterEnabled(true);

			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					Intent i = new Intent(CartListMainActivity.this,
							ProductDetailsActivity.class);
					i.putExtra("productId",  pdata[position].getUuid());
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

		}else{
			
			DatabaseHandler db=new DatabaseHandler(CartListMainActivity.this);
			final Product[] cartlistItems = db.getAllCartListItems();
			
			totalAmount=0D;
			for(int i=0;i<cartlistItems.length;i++){
				System.out.println("Idddddddd:"+cartlistItems.length);
			
			System.out.println("Idddddddd:"+cartlistItems[i].getProductPrice());
				totalAmount = totalAmount
						+ Double.valueOf(cartlistItems[i].getProductPrice());
				System.out.println("totalAmount====="+totalAmount);
			}
			listAdapter = new CartListAdapter(CartListMainActivity.this,cartlistItems, op);
			list.setAdapter(listAdapter);
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					Intent i = new Intent(CartListMainActivity.this,
							ProductDetailsActivity.class);
					i.putExtra("productId",  cartlistItems[position].getUuid());
					startActivity(i);
				}
			});
			
			
			
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
			ArrayList<String> s1 = new ArrayList<String>();
			//System.out.println("iinn mmaaiinn"+pdata);
			//s1.add(pdata[position].getProductId());
			s1.add("Total Amount");
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
