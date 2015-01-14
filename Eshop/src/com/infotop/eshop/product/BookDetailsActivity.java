/*
 * Author: Pabitra
 */

package com.infotop.eshop.product;

//import com.infotop.eshop.activities.ProductDetailsHorizontalActivity;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.infotop.eshop.R;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.db.Wishlist;
import com.infotop.eshop.httpservice.HttpServiceHandler;
import com.infotop.eshop.httpservice.HttpUrl;
import com.infotop.eshop.login.EshopLoginActivity;
import com.infotop.eshop.main.ZoomActivity;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.payment.PaymentMainActivity;
import com.infotop.eshop.specification.SpecificationLaptopActivity;
import com.infotop.eshop.specification.SpecificationMouseActivity;
import com.infotop.eshop.utilities.CustomListHorizontalAdapter;
import com.infotop.eshop.utilities.HorizontalListView;
import com.infotop.eshop.utilities.UserSessionManager;
import com.infotop.eshop.wishlist.PostOperation;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.infotop.bookslistview.BooksListViewActivity;
//import com.infotop.bookslistview.R;

@SuppressLint("SimpleDateFormat")
public class BookDetailsActivity extends Activity {

	
	
	 ProductDetailsHorizontalAdapter hAdapter;
	// adding CartButton,WishlistButton,BuyButton
	ArrayList<String> s;
    View rootView;
    HorizontalListView list;
	ViewHolder holder;
	DisplayImageOptions op;
	String productIdSpecification;
	private static final String TAG_PNAME = "productName";
	private static final String TAG_IMGTYPE = "imageType";
	private static final String TAG_PDESC = "productDescription";
	private static final String TAG_PPRICE = "productPrice";
	private static final String TAG_PID = "uuid";
	private static final String TAG_IMGURL = "image";
	private static final String TAG_IMAGELIST="imageList";
	private static final String TAG_IMGVALUE="imageValue";
	private static final int SELECT_PICTURE = 1;
	ImageLoader loader = ImageLoader.getInstance();
private String productId;	
private String productName;
private String productDescription;
private String productPrice;
private String emailId;
	UserSessionManager usMgr;
	 ArrayList<String> imageUrls=new ArrayList<String>();

	int count=0;

	String childCategoryName;
    String productUUid;
    int pos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//final ViewHolder holder;
		/*final DisplayImageOptions op;*/
		//final ImageLoader loader = ImageLoader.getInstance();
		setContentView(R.layout.activity_book_details);
		op = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.notavailable)
				.showImageForEmptyUri(R.drawable.notavailable)
				.showImageOnFail(R.drawable.notavailable).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();
				
		// Get data from EshopMainActivity
		//s = getIntent().getExtras().getStringArrayList("productData");
		productUUid=getIntent().getExtras().getString("productId");
		childCategoryName=getIntent().getExtras().getString("childCategoryName");
		System.out.println("ChildCategoryName:"+childCategoryName);
		
		String serverURL = new HttpUrl().getUrl()+"/eshop/rest/productByuuid/"+productUUid;

		// Use AsyncTask execute Method To Prevent ANR Problem
		new LongOperation().execute(serverURL);
		
		
	}
		
	private class LongOperation extends AsyncTask<String, Void, Void> {

		/*ProductListAdapter listAdapter;
		String[] pdct;
		String[] pdctId;
		String[] pdesc;
		String[] price;
		//String[] imageUrl;
*/	
		private ProgressDialog dialog = new ProgressDialog(
				BookDetailsActivity.this);

		protected void onPreExecute() {
			// NOTE: You can call UI Element here.

			// Start Progress Dialog (Message)

			dialog.setMessage("Please wait..");
			dialog.show();

		}

		@Override
		protected Void doInBackground(String... urls) {
			JSONArray jsonArray = null;
			String pcontent;
			// Send data
			try {
				HttpServiceHandler hs = new HttpServiceHandler();
				pcontent = hs.httpContent(urls[0]);
				JSONObject jsonObj;
				jsonObj=new JSONObject(pcontent);
				jsonArray=jsonObj.getJSONArray(TAG_IMAGELIST);
				System.out.println("JsonArray:"+jsonArray);
				//jsonObj = new JSONObject(pcontent).getJSONObject(TAG_IMAGELIST);
				productId=jsonObj.getString(TAG_PID);
				productName=jsonObj.getString(TAG_PNAME);
				productDescription=jsonObj.getString(TAG_PDESC);
				productPrice=jsonObj.getString(TAG_PPRICE);
				int size=jsonArray.length();
				System.out.println("ArraySize is:"+size);
				for(int i=0;i<size;i++){
					JSONObject pc = jsonArray.getJSONObject(i);
					if(pc.getString(TAG_IMGTYPE).equals("2")){
						//System.out.println("Image Url's of Imagelist:"+pc.getString(TAG_IMGVALUE));
						imageUrls.add(pc.getString(TAG_IMGVALUE));
					}
					//if(jsonArray.getString(TAG_IMGTYPE))
					
				}
				
				
			} catch (Exception ex) {
				System.out.println("Exception e:" + ex.getMessage());
			}
			/*****************************************************/
			return null;
		}

		protected void onPostExecute(Void unused) {
			
			
			
			holder = new ViewHolder();
			holder.txtTitle = (TextView) findViewById(R.id.bookName1);
			holder.txtTitle1 = (TextView) findViewById(R.id.authorName);
			holder.txtTitle2 = (TextView) findViewById(R.id.price);
			holder.imageView = (ImageView) findViewById(R.id.logo);
	   
			holder.txtTitle.setText(productName);
			holder.txtTitle1.setText(productDescription);
			holder.txtTitle2.setText(productPrice);
			loader.displayImage(imageUrls.get(0), holder.imageView, op, null);
			hAdapter=new ProductDetailsHorizontalAdapter(BookDetailsActivity.this, imageUrls,op);
			list = (HorizontalListView) findViewById(R.id.detailshorizontal);
			list.setAdapter(hAdapter);
			
			
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					loader.displayImage(imageUrls.get(position), holder.imageView, op, null); 
					pos=position;
				}
			});
			

			holder.imageView.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                Intent i = new Intent(BookDetailsActivity.this, ZoomActivity.class);
	         
	                System.out.println("position=======================++++++"+pos);
	                i.putExtra("list", imageUrls.get(pos));
	                System.out.println("position=========="+imageUrls.get(pos));
	               // i.putExtra("count", count);
	               
	                startActivity(i);
	            }

				
	        });
			
			dialog.dismiss();
			
		}
 
	}	
		
		
	private class ViewHolder {
		public TextView txtTitle;
		public TextView txtTitle1;
		public TextView txtTitle2;
		public ImageView imageView;
		
	}
	
		
		
	// functionalities for wishlistBtn
	public void addToWishlist(View view) {
		
		UserSessionManager usMgr = new UserSessionManager(BookDetailsActivity.this);
		 if(usMgr.isUserLoggedIn()){
				emailId = usMgr.getUserDetails().get("email");
				Product p=new Product();
				p.setServiceUrl(new HttpUrl().getUrl()
						+ "/eshop/rest/addwishlist");
				p.setProductId(productId);
				p.setProductName(productName);
				p.setDescription(productDescription);
				p.setImageUrl(imageUrls.get(0));
				p.setPrice(productPrice);
				p.setEmailId(emailId);
				AsyncTask<Object, Void, String> respData=new PostOperation().execute(p);
				String pcontent;
				try {
					pcontent = respData.get();
					if (pcontent.equalsIgnoreCase("Success")) {
						Toast.makeText(getBaseContext(), "Your item is added to Wish List",
								Toast.LENGTH_SHORT).show();
					}else if(pcontent.equalsIgnoreCase("Exist")){
						Toast.makeText(getBaseContext(), "Your item is already added to Wish List", Toast.LENGTH_SHORT)
						.show();
					}
						else {
						Toast.makeText(getBaseContext(), "Connection error", Toast.LENGTH_SHORT)
								.show();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
		
	}else{
		 Intent intent = new Intent(BookDetailsActivity.this,EshopLoginActivity.class);
			startActivity(intent);
		
	 }
		 
}
	
	/*private class WishLongOperation extends AsyncTask<String, Void, Void> {
		private String pcontent;

		@Override
		protected Void doInBackground(String... urls) {
			String jsonData = "";
			// Send data
			try {
				HttpServiceHandler hs = new HttpServiceHandler();
				JSONObject json = new JSONObject();
				json.accumulate("productId", productId);
				json.accumulate("productName", productName);
				json.accumulate("description", productDescription);
				json.accumulate("price", productPrice);
				json.accumulate("imageUrl", imageUrls.get(0));
				json.accumulate("emailId", emailId);
				jsonData = json.toString();
				pcontent = hs.httpPost(urls[0], jsonData);
				
				System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" + productId);
				System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" + productName);
				System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" + productDescription);
				System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" + imageUrls);
				System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" + emailId);
				
				System.out.println("Executed data:mmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" + pcontent);
			} catch (Exception ex) {
				System.out.println("Exception e:" + ex.getMessage());
			}
			*//*****************************************************//*
			return null;
		}

		protected void onPostExecute(Void unused) {
			if (pcontent.equalsIgnoreCase("Success")) {
				Toast.makeText(BookDetailsActivity.this, "Your item is added to Wish List",
						Toast.LENGTH_SHORT).show();
			}else if(pcontent.equalsIgnoreCase("Exist")){
				Toast.makeText(BookDetailsActivity.this, "Your item is already added to Wish List", Toast.LENGTH_SHORT)
				.show();
			}
				else {
				Toast.makeText(getApplicationContext(), "Connection error", Toast.LENGTH_SHORT)
						.show();
			}
			// Close progress dialog
		}

	}*/
	
	public void getSpecification(View view){
		 if("Mobiles".equalsIgnoreCase(childCategoryName)){
		 Intent intSpecification= new Intent(this,SpecificationMouseActivity.class);
		 intSpecification.putExtra("idspec", productId);
		 startActivity(intSpecification);
		 }
		 else if("Laptops".equalsIgnoreCase(childCategoryName)){
			 Intent intSpecification= new Intent(this,SpecificationLaptopActivity.class);
			 intSpecification.putExtra("idspec", productId);
			 startActivity(intSpecification);
		 }
		 else if("Tablets".equalsIgnoreCase(childCategoryName)){
			 Intent intSpecification= new Intent(this,SpecificationMouseActivity.class);
			 intSpecification.putExtra("idspec", productId);
			 startActivity(intSpecification);
		 }
		
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
			
			Wishlist w = new Wishlist();
			w.setProductName(productName);
			Intent sharingIntent = new Intent(Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
	 
	     
			sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Sending from Esop");
			sharingIntent.putExtra(Intent.EXTRA_TEXT, w.getProductName());
	 
	        startActivity(Intent.createChooser(sharingIntent, "Share link!"));
			
			//Wishlist w = new Wishlist();
			
			//sharingIntent.setType("image/*");


			//w.setImageUrl(s.get(0));
			 
			String imagePath = Environment.getExternalStorageDirectory()
			            + "w.getImageUrl()";
			
			File imageFileToShare = new File(imagePath);
			Uri screenshotUri = Uri.fromFile(imageFileToShare);
			
			
			sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
			
		
			System.out.println("ooooooooooooooooooooooooooooooo"+screenshotUri);
			startActivity(Intent.createChooser(sharingIntent, "Share image using"));
			        

			
			return true;
		case R.id.ab_addToCart:
			usMgr = new UserSessionManager(this);
			 if(!usMgr.isUserLoggedIn()){
				 
				 Intent lgn1 = new Intent(this, EshopLoginActivity.class);
				 startActivity(lgn1);
			 } else{
				 DatabaseHandler db1 = new DatabaseHandler(BookDetailsActivity.this);
					Wishlist w1 = new Wishlist();
					w1.setProductId(productId);
					w1.setProductName(productName);
					w1.setDescription(productDescription);
					w1.setPrice(productPrice);
					w1.setImageUrl(imageUrls.get(0));
					w1.setCreatedDate(new SimpleDateFormat("dd MMM yyyy")
							.format(new Date()));
					
					List<Wishlist> s = db1.getAllCartListItems();
					int counter=0;
					for(int i=0;i<s.size();i++){
						if(s.get(i).getProductId().equals(productId)){
							counter++;
						}
					}
					if(counter>0){
						Toast.makeText(BookDetailsActivity.this, "Your item is already Added to Cart",
								Toast.LENGTH_SHORT).show();
					}else{
						
						db1.addCartList(w1);;
					Toast.makeText(BookDetailsActivity.this, "Your item is Added to Cart",
							Toast.LENGTH_SHORT).show();
						
					}
					
					
					/*db1.addCartList(w1);
					Toast.makeText(BookDetailsActivity.this,
							"Your Item is Added to Cart", Toast.LENGTH_SHORT).show();*/
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
				
				Intent in = new Intent(BookDetailsActivity.this,
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
