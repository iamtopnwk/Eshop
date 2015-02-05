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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.infotop.eshop.R;
import com.infotop.eshop.login.EshopLoginActivity;
import com.infotop.eshop.main.activity.EshopMainActivity;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.main.activity.ZoomActivity;
import com.infotop.eshop.model.ImageList;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.payment.PaymentMainActivity;
import com.infotop.eshop.specification.SpecificationAgricultureActivity;
import com.infotop.eshop.specification.SpecificationAirConditionerActivity;
import com.infotop.eshop.specification.SpecificationComputerActivity;
import com.infotop.eshop.specification.SpecificationCricketBatActivity;
import com.infotop.eshop.specification.SpecificationLanguageActivity;
import com.infotop.eshop.specification.SpecificationLaptopActivity;
import com.infotop.eshop.specification.SpecificationMobileActivity;
import com.infotop.eshop.specification.SpecificationMouseActivity;
import com.infotop.eshop.specification.SpecificationTelevisionActivity;
import com.infotop.eshop.specification.SpecificationTennisBatActivity;
import com.infotop.eshop.specification.SpecificationWashingMachinActivity;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.GetOperation;
import com.infotop.eshop.utilities.HorizontalListView;
import com.infotop.eshop.utilities.JsonHelper;
import com.infotop.eshop.utilities.PostOperation;
import com.infotop.eshop.utilities.UserSessionManager;
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
	/*
	 * private String productId; private String productName; private String
	 * productDescription;
	 */
	private String productPrice;
	ArrayList<String> mediumimageUrls = new ArrayList<String>();

	// private String emailId;
	Product pdata;
	Button btn;

	UserSessionManager usMgr;

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
	    childCategoryName = getIntent().getExtras().getString("childCategoryName");

		System.out.println("productUUid:=======" + productUUid);
		System.out.println("ChildCategoryName:=======" + childCategoryName);
		/*
		 * Product pdt = new Product(); pdt.setServiceUrl(UrlInfo.INDVProduct +
		 * productUUid);
		 */

		String serverURL = UrlInfo.INDVProduct + productUUid;
		AsyncTask<String, Void, String> productListData = new GetOperation()
				.execute(serverURL);
		// Use AsyncTask execute Method To Prevent ANR Problem
		try {

			// final ArrayList<String> bigimageUrls = new ArrayList<String>();
			System.out.println(productListData.get());
			pdata = (Product) JsonHelper.toObject(productListData.get(),
					Product.class);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) findViewById(R.id.bookName1);
			holder.txtTitle1 = (TextView) findViewById(R.id.authorName);
			holder.txtTitle2 = (TextView) findViewById(R.id.price);
			holder.imageView = (ImageView) findViewById(R.id.logo);

			holder.txtTitle.setText(pdata.getProductName());
			holder.txtTitle1.setText(pdata.getProductDescription());
			holder.txtTitle2.setText(pdata.getProductPrice());

			ImageList[] images = pdata.getImageList();
			System.out.println("images length---------" + images.length);

			for (int i = 0; i < images.length; i++) {

				if (images[i].getImageType().equals("2")) {
					mediumimageUrls.add(images[i].getImageValue());
				}
			}
			loader.displayImage(mediumimageUrls.get(0), holder.imageView, op,
					null);
			System.out.println("jksssssssssssssssssssssssssssssssss"
					+ mediumimageUrls.get(0));

			hAdapter = new ProductDetailsHorizontalAdapter(
					ProductDetailsActivity.this, mediumimageUrls, op);

			list = (HorizontalListView) findViewById(R.id.detailshorizontal);
			list.setAdapter(hAdapter);

			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					System.out.println("position==================" + position);
					loader.displayImage(mediumimageUrls.get(position),
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
					i.putExtra("list", mediumimageUrls.get(pos));

					// i.putExtra("count", count);

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

	}

	private class ViewHolder {
		public TextView txtTitle;
		public TextView txtTitle1;
		public TextView txtTitle2;
		public ImageView imageView;

	}

	// functionalities for wishlistBtn
	/*@SuppressLint("NewApi")
	public void addToWishlist(View view) {

		UserSessionManager usMgr = new UserSessionManager(
				ProductDetailsActivity.this);
		if (usMgr.isUserLoggedIn()) {
			Product pdt = new Product();
			pdt.setServiceUrl(UrlInfo.ADDWishlist);
			pdt.setEmailId(usMgr.getUserDetails().get("email"));

			pdt.setId(pdata.getId());
			pdt.setUuid(pdata.getUuid());
			pdt.setProductName(pdata.getProductName());
			pdt.setProductDescription(pdata.getProductDescription());
			pdt.setImage(mediumimageUrls.get(0));
			pdt.setProductPrice(pdata.getProductPrice());
            System.out.println("UUIDOOOOOOOO===="+pdt.getUuid());
            System.out.println("UUIDOOOOOOOO===="+pdata.getUuid());
			AsyncTask<Object, Void, String> wishtListData = new PostOperation()
					.execute(pdt);
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

			DatabaseHandler db = new DatabaseHandler(
					ProductDetailsActivity.this);

			Product p = new Product();
			p.setUuid(pdata.getUuid());
			p.setProductName(pdata.getProductName());
			p.setProductDescription(pdata.getProductDescription());
			p.setProductPrice(pdata.getProductPrice());
			p.setImage(mediumimageUrls.get(0));
			System.out.println("jjjjjjjjjjjjjjjjjjjj" + p.getImage());
			// pdata.setImage(mediumimageUrls.get(0));

			System.out.println("uuuuuiiiiiddddddddddddd" + pdata.getUuid());

			Product[] pList = db.getAllWishListItems();
			if (pList==null) {
				db.addWishList(p);
			} else {
				System.out.println("llllleeeennngggtthhh" + pList.length);
				int counter = 0;
				for (int i = 0; i < pList.length; i++) {

					
					if (pList[i].getUuid().equals(p.getUuid())) {
						counter++;
					}
				}
				if (counter > 0) {
					Toast.makeText(ProductDetailsActivity.this,
							"Your item is already added to Wish List",
							Toast.LENGTH_SHORT).show();
				} else {

					db.addWishList(p);
					System.out.println("wiishlissstttgetpdata---------::::"
							+ pdata.getProductName());
					System.out.println("Image URL" + pdata.getImage());
					Toast.makeText(ProductDetailsActivity.this,
							"Your item is added to Wish List",
							Toast.LENGTH_SHORT).show();
				}
			}
		}
	}*/
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
		if ("Television".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationTelevisionActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
	    }
		if ("Washing machine".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationWashingMachinActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
	    }
		if ("Air Conditioner".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationAirConditionerActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
	    }
		/*if ("Radio".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationRadioActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
	    }*/
		if ("Agriculture".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationAgricultureActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
	    }
		if ("Computer".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationComputerActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
	    }
		if ("Language".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationLanguageActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
	    }
		
		if ("Cricket Bat".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationCricketBatActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
	    }
		if ("Tennis bat".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationTennisBatActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
	    }
		/*if ("Shoes".equalsIgnoreCase(childCategoryName)) {
			Intent intSpecification = new Intent(this,
					SpecificationShoesActivity.class);
			// intSpecification.putExtra("idspec", productId);
			startActivity(intSpecification);
	    }*/
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

	
	
	/* User Comments Button*/
	public void postComment(View view){
		EditText editText=(EditText) findViewById(R.id.comEdit);
		TextView textView=(TextView) findViewById(R.id.userComments2);
		String string=editText.getText().toString();
		textView.setText(string);
		//Intent intent=new Intent(this,ProductDetailsActivity.class);
		//startActivity(intent);
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
			w.setProductName(pdata.getProductName());
			Intent sharingIntent = new Intent(Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

			sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Sending from Esop");
			sharingIntent.putExtra(Intent.EXTRA_TEXT, w.getProductName());

			startActivity(Intent.createChooser(sharingIntent, "Share link!"));

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

				DatabaseHandler db = new DatabaseHandler(
						ProductDetailsActivity.this);
				Product p = new Product();
				p.setUuid(pdata.getUuid());
				p.setProductName(pdata.getProductName());
				p.setProductDescription(pdata.getProductDescription());
				p.setProductPrice(pdata.getProductPrice());
				p.setMainimage(mediumimageUrls.get(0));
				System.out.println("jjjjjjjjjjjjjjjjjjjj" + p.getMainimage());
				// pdata.setImage(mediumimageUrls.get(0));

				System.out.println("uuuuuiiiiiddddddddddddd" + pdata.getUuid());

				Product[] pList = db.getAllCartListItems();
				if (pList==null) {
					db.addCartList(p);;
				} else {
					System.out.println("llllleeeennngggtthhh" + pList.length);
					int counter = 0;
					for (int i = 0; i < pList.length; i++) {

						/*
						 * System.out.println("IDidddddddddddd"+pList[i].getUuid());
						 * System.out.println("IDidddddddddddd"+pdata.getUuid());
						 */
						if (pList[i].getUuid().equals(p.getUuid())) {
							counter++;
						}
					}
					if (counter > 0) {
						Toast.makeText(ProductDetailsActivity.this,
								"Your item is already added to Wish List",
								Toast.LENGTH_SHORT).show();
					} else {

						db.addCartList(p);
						System.out.println("wiishlissstttgetpdata---------::::"
								+ pdata.getProductName());
						System.out.println("Image URL" + pdata.getMainimage());
						Toast.makeText(ProductDetailsActivity.this,
								"Your item is added to Wish List",
								Toast.LENGTH_SHORT).show();
					}
				}
			//}
		
				//pdata.setImage(mediumimageUrls.get(0));
				//db.addCartList(pdata);

			} else {
				Product pdt = new Product();

				pdt.setServiceUrl(UrlInfo.ADDCartlist);
				pdt.setEmailId(usMgr.getUserDetails().get("email"));

				pdt.setId(pdata.getId());

				pdt.setUuid(pdata.getUuid());
				pdt.setProductName(pdata.getProductName());
				pdt.setProductPrice(pdata.getProductPrice());
				pdt.setProductDescription(pdata.getProductDescription());
				System.out.println("pdata product price::::::"+pdata.getProductPrice());
				pdt.setMainimage(mediumimageUrls.get(0));

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

			s.add(pdata.getUuid());
			s.add(pdata.getProductName());
			s.add(pdata.getProductDescription());
		    s.add(pdata.getProductPrice());

			s.add(mediumimageUrls.get(0));
			UserSessionManager us = new UserSessionManager(this);
			Boolean result = us.checkLogin();
			if (result == false) {

				Intent in = new Intent(ProductDetailsActivity.this,
						PaymentMainActivity.class);
				in.putStringArrayListExtra("purChaseItem", s);
				startActivity(in);
			}
			return true;
		case R.id.home_details:
			Intent homeIntent=new Intent(ProductDetailsActivity.this,EshopMainActivity.class);
			startActivity(homeIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

}
