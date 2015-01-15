package com.infotop.eshop.utilities;

import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.infotop.eshop.R;
import com.infotop.eshop.httpservice.HttpUrl;
import com.infotop.eshop.login.EshopLoginActivity;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.wishlist.PostOperation;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

@SuppressLint("SimpleDateFormat")
public class CustomGridViewAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] productName;
	private final String[] imageUrl;
	private final String[] description;
	private final String[] price;
	private final String[] productId;
	private String emailId;
	private final DisplayImageOptions op;
	protected ImageLoader loader = ImageLoader.getInstance();

	public CustomGridViewAdapter(Activity context, String[] productId,
			String[] productName, String[] imageUrl, String[] description,
			String[] price, DisplayImageOptions op) {

		super(context, R.layout.custom_gridview, productName);
		this.context = context;
		this.productName = productName;
		this.imageUrl = imageUrl;
		this.description = description;
		this.price = price;
		this.productId = productId;
		this.op = op;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View view, ViewGroup parent) {

		View rowView = view;
		final ViewHolder holder;
		if (rowView == null) {
			rowView = context.getLayoutInflater().inflate(
					R.layout.custom_gridview, parent, false);
			System.out.println("Cate Context value is:" + context);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) rowView
					.findViewById(R.id.product_Name);
			holder.txtTitle1 = (TextView) rowView
					.findViewById(R.id.product_description);
			holder.txtTitle2 = (TextView) rowView
					.findViewById(R.id.product_price);
			holder.imageView = (ImageView) rowView
					.findViewById(R.id.productImg);
			holder.imgwishlistbtn = (ImageView) rowView
					.findViewById(R.id.imgwishlistbtn);
			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
		}
		final int id = position;
		holder.txtTitle.setText(productName[position]);
		holder.txtTitle1.setText(description[position]);
		holder.txtTitle2.setText(price[position]);
		loader.displayImage(imageUrl[position], holder.imageView, op, null);
		holder.imgwishlistbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				UserSessionManager usMgr = new UserSessionManager(context);
				if (usMgr.isUserLoggedIn()) {
					emailId = usMgr.getUserDetails().get("email");
					Product p = new Product();
					p.setServiceUrl(new HttpUrl().getUrl()
							+ "/eshop/rest/addwishlist");

					p.setProductId(productId[id]);
					p.setProductName(productName[id]);
					p.setDescription(description[id]);
					p.setImageUrl(imageUrl[id]);
					p.setPrice(price[id]);
					p.setEmailId(emailId);

					AsyncTask<Object, Void, String> respData = new PostOperation()
							.execute(p);
					String pcontent;

					/*
					 * w.setCreatedDate(new SimpleDateFormat("dd MMM yyyy")
					 * .format(new Date()));
					 */

					try {
						pcontent = respData.get();
						if (pcontent.equalsIgnoreCase("Success")) {
							Toast.makeText(context,
									"Your item is added to Wish List",
									Toast.LENGTH_SHORT).show();
						} else if (pcontent.equalsIgnoreCase("Exist")) {
							Toast.makeText(context,
									"Your item is already added to Wish List",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(context, "Connection error",
									Toast.LENGTH_SHORT).show();
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					/*
					 * List<Wishlist> s = db.getAllWishListItems(); int
					 * counter=0; for(int i=0;i<s.size();i++){
					 * if(s.get(i).getProductId().equals(productId[id])){
					 * counter++; } } if(counter>0){ Toast.makeText(context,
					 * "Your item is already added to Wish List",
					 * Toast.LENGTH_SHORT).show(); }else{
					 * 
					 * db.addWishList(w); Toast.makeText(context,
					 * "Your item is added to Wish List",
					 * Toast.LENGTH_SHORT).show();
					 * 
					 * }
					 * 
					 * db.addWishList(w); Toast.makeText(context,
					 * "Your item is added to Wish List",
					 * Toast.LENGTH_SHORT).show();
					 */
				} else {
					Intent intent = new Intent(context,
							EshopLoginActivity.class);
					context.startActivity(intent);

				}
			}
		});
		return rowView;
	}

	private class ViewHolder {
		public TextView txtTitle;
		public TextView txtTitle1;
		public TextView txtTitle2;
		public ImageView imageView;
		public ImageView imgwishlistbtn;

	}

}