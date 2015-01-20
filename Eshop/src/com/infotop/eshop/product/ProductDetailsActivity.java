/*
 * Author: Pabitra
 */

package com.infotop.eshop.product;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.infotop.eshop.R;
import com.infotop.eshop.login.EshopLoginActivity;
import com.infotop.eshop.main.activity.ZoomActivity;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.payment.PaymentMainActivity;
import com.infotop.eshop.specification.SpecificationLaptopActivity;
import com.infotop.eshop.specification.SpecificationMobileActivity;
import com.infotop.eshop.specification.SpecificationMouseActivity;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.GetOperation;
import com.infotop.eshop.utilities.HorizontalListView;
import com.infotop.eshop.utilities.HttpServiceHandler;
import com.infotop.eshop.utilities.JsonHelper;
import com.infotop.eshop.utilities.UserSessionManager;
import com.infotop.eshop.wishlist.PostOperation;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

@SuppressLint("SimpleDateFormat")
public class ProductDetailsActivity extends Activity {

	ProductDetailsHorizontalAdapter hAdapter;
	// adding CartButton,WishlistButton,BuyButton
	ArrayList<String> s;
	View rootView;
	HorizontalListView list;
	ViewHolder holder;
	DisplayImageOptions op;
	String productIdSpecification;
	
	ImageLoader loader = ImageLoader.getInstance();
	private String productId;
	private String productName;
	private String productDescription;
	private String productPrice;

	private String emailId;

	Button btn;

	UserSessionManager usMgr;
	ArrayList<String> imageUrls = new ArrayList<String>();

	int count = 0;

	String childCategoryName;
	String productUUid;
	int pos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_details);

		btn = (Button) findViewById(R.id.getSpecs);
		op = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.notavailable)
				.showImageForEmptyUri(R.drawable.notavailable)
				.showImageOnFail(R.drawable.notavailable).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();

		productUUid = getIntent().getExtras().getString("productId");
		childCategoryName = getIntent().getExtras().getString(
				"childCategoryName");
		System.out.println("ChildCategoryName:=======" + childCategoryName);
		/*Product pdt = new Product();
		pdt.setServiceUrl(UrlInfo.INDVProduct + productUUid);*/
		
		String serverURL = UrlInfo.INDVProduct + productUUid;
		AsyncTask<String, Void, String> productListData = new GetOperation().execute(serverURL);
		// Use AsyncTask execute Method To Prevent ANR Problem
		try {
			System.out.println(productListData.get());
			final Product[] pdata= (Product[]) JsonHelper.toObject(productListData.get(), Product[].class);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) findViewById(R.id.bookName1);
			holder.txtTitle1 = (TextView) findViewById(R.id.authorName);
			holder.txtTitle2 = (TextView) findViewById(R.id.price);
			holder.imageView = (ImageView) findViewById(R.id.logo);

			holder.txtTitle.setText(pdata[0].getProductName());
			holder.txtTitle1.setText(pdata[0].getProductDescription());
			holder.txtTitle2.setText(pdata[0].getProductPrice());
			//loader.displayImage(imageUrls.get(0), holder.imageView, op, null);
			
			loader.displayImage(pdata[0].getImageList()[0].getImageValue(), holder.imageView, op, null);
			System.out.println("mmmmmm"+pdata[0].getImageList()[0].getImageValue());
			
		/*	hAdapter = new ProductDetailsHorizontalAdapter(
					ProductDetailsActivity.this, pdata[0].getImageList(), op);
			list = (HorizontalListView) findViewById(R.id.detailshorizontal);
			list.setAdapter(hAdapter);

			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					loader.displayImage(imageUrls.get(position),
							holder.imageView, op, null);
					pos = position;
				}
			});

			holder.imageView.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Intent i = new Intent(ProductDetailsActivity.this,
							ZoomActivity.class);

					System.out.println("position=======================++++++"
							+ pos);
					i.putExtra("list", imageUrls.get(pos));
					System.out.println("position=========="
							+ imageUrls.get(pos));
					// i.putExtra("count", count);

					startActivity(i);
				}

			});*/
		

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private class ViewHolder {
		public TextView txtTitle;
		public TextView txtTitle1;
		public TextView txtTitle2;
		public ImageView imageView;

	}

	/*private class LongOperation extends AsyncTask<String, Void, Void> {

		private ProgressDialog dialog = new ProgressDialog(
				ProductDetailsActivity.this);

		protected void onPreExecute() {
			// NOTE: You can call UI Element here.

			// Start Progress Dialog (Message)

			dialog.setMessage("Please wait..");
			dialog.show();

		}
*/
		/*@Override
		protected Void doInBackground(String... urls) {
			JSONArray jsonArray = null;
			String pcontent;
			// Send data
			try {
				HttpServiceHandler hs = new HttpServiceHandler();
				pcontent = hs.httpContent(urls[0]);
				JSONObject jsonObj;
				jsonObj = new JSONObject(pcontent);
				jsonArray = jsonObj.getJSONArray(TAG_IMAGELIST);
				System.out.println("JsonArray:" + jsonArray);
				// jsonObj = new
				// JSONObject(pcontent).getJSONObject(TAG_IMAGELIST);
				productId = jsonObj.getString(TAG_PID);
				productName = jsonObj.getString(TAG_PNAME);
				productDescription = jsonObj.getString(TAG_PDESC);
				productPrice = jsonObj.getString(TAG_PPRICE);
				int size = jsonArray.length();
				System.out.println("ArraySize is:" + size);
				for (int i = 0; i < size; i++) {
					JSONObject pc = jsonArray.getJSONObject(i);
					if (pc.getString(TAG_IMGTYPE).equals("2")) {
						// System.out.println("Image Url's of Imagelist:"+pc.getString(TAG_IMGVALUE));
						imageUrls.add(pc.getString(TAG_IMGVALUE));
					}
					// if(jsonArray.getString(TAG_IMGTYPE))

				}

			} catch (Exception ex) {
				System.out.println("Exception e:" + ex.getMessage());
			}
			*//*****************************************************//*
			return null;
		}*/

		/*protected void onPostExecute(Void unused) {

			holder = new ViewHolder();
			holder.txtTitle = (TextView) findViewById(R.id.bookName1);
			holder.txtTitle1 = (TextView) findViewById(R.id.authorName);
			holder.txtTitle2 = (TextView) findViewById(R.id.price);
			holder.imageView = (ImageView) findViewById(R.id.logo);

			holder.txtTitle.setText(productName);
			holder.txtTitle1.setText(productDescription);
			holder.txtTitle2.setText(productPrice);
			loader.displayImage(imageUrls.get(0), holder.imageView, op, null);
			hAdapter = new ProductDetailsHorizontalAdapter(
					ProductDetailsActivity.this, imageUrls, op);
			list = (HorizontalListView) findViewById(R.id.detailshorizontal);
			list.setAdapter(hAdapter);

			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					loader.displayImage(imageUrls.get(position),
							holder.imageView, op, null);
					pos = position;
				}
			});

			holder.imageView.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Intent i = new Intent(ProductDetailsActivity.this,
							ZoomActivity.class);

					System.out.println("position=======================++++++"
							+ pos);
					i.putExtra("list", imageUrls.get(pos));
					System.out.println("position=========="
							+ imageUrls.get(pos));
					// i.putExtra("count", count);

					startActivity(i);
				}

			});

			dialog.dismiss();

		}
*/
	//}

	

	// functionalities for wishlistBtn
	public void addToWishlist(View view) {

		UserSessionManager usMgr = new UserSessionManager(
				ProductDetailsActivity.this);
		if (usMgr.isUserLoggedIn()) {
			//emailId = usMgr.getUserDetails().get("email");
			Product pdt = new Product();
			pdt.setServiceUrl(UrlInfo.ADDWishlist);
			pdt.setEmailId(usMgr.getUserDetails().get("email"));
			AsyncTask<Object, Void, String> wishtListData = new PostOperation().execute(pdt);
			
		/*	pdt.setProductId(productId);
			pdt.setProductName(productName);
			pdt.setDescription(productDescription);
			pdt.setImageUrl(imageUrls.get(0));
			pdt.setPrice(productPrice);
			pdt.setEmailId(emailId);*/
		
			String pcontent;
			try {
				pcontent = wishtListData.get();
				if (pcontent.equalsIgnoreCase("Success")) {
					Toast.makeText(getBaseContext(),
							"Your item is added to Wish List",
							Toast.LENGTH_SHORT).show();
				} else if (pcontent.equalsIgnoreCase("Exist")) {
					Toast.makeText(getBaseContext(),
							"Your item is already added to Wish List",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getBaseContext(), "Connection error",
							Toast.LENGTH_SHORT).show();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Intent intent = new Intent(ProductDetailsActivity.this,
					EshopLoginActivity.class);
			startActivity(intent);

		}

	}

	public void getSpecifications(View view) {
		if ("Mobiles".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationMobileActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
		} else if ("Laptops".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationLaptopActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
		} else if ("Tablets".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationMouseActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
		}
	}
	
	public void getBarChart(View view) {
		BarGraph bar = new BarGraph();
        Intent barIntent = bar.getIntent(this);
        startActivity(barIntent);
	}
	
	public void getPieChart(View view) {
		PieChart pie = new PieChart();
        Intent pieIntent = pie.getIntent(this);
        startActivity(pieIntent);
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

			Product w = new Product();
			w.setProductName(productName);
			Intent sharingIntent = new Intent(Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

			sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Sending from Esop");
			sharingIntent.putExtra(Intent.EXTRA_TEXT, w.getProductName());

			startActivity(Intent.createChooser(sharingIntent, "Share link!"));

			// Wishlist w = new Wishlist();

			// sharingIntent.setType("image/*");

			// w.setImageUrl(s.get(0));

			String imagePath = Environment.getExternalStorageDirectory()
					+ "w.getImageUrl()";

			File imageFileToShare = new File(imagePath);
			Uri screenshotUri = Uri.fromFile(imageFileToShare);

			sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);

			System.out.println("ooooooooooooooooooooooooooooooo"
					+ screenshotUri);
			startActivity(Intent.createChooser(sharingIntent,
					"Share image using"));

			return true;
		case R.id.ab_addToCart:
			usMgr = new UserSessionManager(this);
			if (!usMgr.isUserLoggedIn()) {
				Intent lgn1 = new Intent(this, EshopLoginActivity.class);
				startActivity(lgn1);
			} else {
				Product pdt = new Product();

				pdt.setServiceUrl(UrlInfo.ADDCartlist);
				pdt.setEmailId(usMgr.getUserDetails().get("email"));
				pdt.setId(productId);
				pdt.setProductName(productName);
				pdt.setProductPrice(productPrice);
				pdt.setProductDescription(productDescription);
				pdt.setImage(imageUrls.get(0));

				AsyncTask<Object, Void, String> respDataCartItem = new PostOperation()
						.execute(pdt);
				String pcontent;
				try {
					pcontent = respDataCartItem.get();
					if (pcontent.equalsIgnoreCase("Success")) {
						Toast.makeText(ProductDetailsActivity.this,
								"Your item is added to Cart List",
								Toast.LENGTH_SHORT).show();
					} else if (pcontent.equalsIgnoreCase("Exist")) {
						Toast.makeText(
								ProductDetailsActivity.this,
								"Your item is already added to Cart List and and buy other one.",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(ProductDetailsActivity.this,
								"Connection error", Toast.LENGTH_SHORT).show();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			return true;
		case R.id.ab_purChaseItem:
			s = new ArrayList<String>();
			s.add(productId);
			s.add(productName);
			s.add(productDescription);
			s.add(productPrice);
			s.add(imageUrls.get(0));
			UserSessionManager us = new UserSessionManager(this);
			Boolean result = us.checkLogin();
			if (result == false) {

				Intent in = new Intent(ProductDetailsActivity.this,
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
