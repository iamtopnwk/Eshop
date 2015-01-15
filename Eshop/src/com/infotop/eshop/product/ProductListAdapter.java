package com.infotop.eshop.product;

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
import com.infotop.eshop.login.EshopLoginActivity;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.UserSessionManager;
import com.infotop.eshop.wishlist.PostOperation;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

@SuppressLint({ "ViewHolder", "InflateParams", "SimpleDateFormat" })
public class ProductListAdapter extends ArrayAdapter<String> {
	private int selectedId;
	private String emailId;
	private final Activity context;
	private final String[] productName;
	private final String[] desc;
	private final String[] imageUrl;
	private final String[] price;
	private final String[] pdctId;
	private final DisplayImageOptions op;
	protected ImageLoader loader = ImageLoader.getInstance();

	public ProductListAdapter(Activity context, String[] pdctId,
			String[] productName, String[] imageUrl, String[] desc,
			String[] price, DisplayImageOptions op) {

		super(context, R.layout.product_list, productName);
		this.context = context;
		this.productName = productName;
		this.imageUrl = imageUrl;
		this.desc = desc;
		this.price = price;
		this.pdctId = pdctId;
		this.op = op;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		View rowView = view;
		final ViewHolder holder;
		if (rowView == null) {
			rowView = context.getLayoutInflater().inflate(
					R.layout.product_list, parent, false);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) rowView.findViewById(R.id.productName);
			holder.txtTitle1 = (TextView) rowView
					.findViewById(R.id.productDesc);
			holder.txtTitle2 = (TextView) rowView
					.findViewById(R.id.productprice);
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
		holder.txtTitle1.setText(desc[position]);
		holder.txtTitle2.setText(price[position]);
		loader.displayImage(imageUrl[position], holder.imageView, op, null);
		holder.imgwishlistbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				UserSessionManager usMgr = new UserSessionManager(context);
				if (usMgr.isUserLoggedIn()) {
					selectedId = id;
					emailId = usMgr.getUserDetails().get("email");
					/*
					 * new LongOperation().execute(new HttpUrl().getUrl() +
					 * "/eshop/rest/addwishlist");
					 */
					Product p = new Product();
					p.setServiceUrl(UrlInfo.ADDWishlist);
					p.setProductId(pdctId[id]);
					p.setProductName(productName[id]);
					p.setDescription(desc[id]);
					p.setImageUrl(imageUrl[id]);
					p.setPrice(price[id]);
					p.setEmailId(emailId);
					AsyncTask<Object, Void, String> respData = new PostOperation()
							.execute(p);
					String pcontent;
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