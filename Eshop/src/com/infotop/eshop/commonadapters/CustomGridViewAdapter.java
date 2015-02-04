package com.infotop.eshop.commonadapters;

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
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.login.EshopLoginActivity;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.PostOperation;
import com.infotop.eshop.utilities.UserSessionManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

@SuppressLint("SimpleDateFormat")
public class CustomGridViewAdapter extends ArrayAdapter<Product> {

	private final Activity context;
	private final Product[] pdata;
	private final DisplayImageOptions op;
	protected ImageLoader loader = ImageLoader.getInstance();

	public CustomGridViewAdapter(Activity context,Product[] pdata, DisplayImageOptions op) {

		super(context, R.layout.custom_gridview, pdata);
		this.context = context;
		this.pdata=pdata;
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
		/*	holder.imgwishlistbtn = (ImageView) rowView
					.findViewById(R.id.imgwishlistbtn);*/
			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
		}
		final int id = position;
		holder.txtTitle.setText(pdata[position].getProductName());
		holder.txtTitle1.setText(pdata[position].getProductDescription());
		holder.txtTitle2.setText(pdata[position].getProductPrice());
		loader.displayImage(pdata[position].getMainimage(), holder.imageView, op, null);
	/*	holder.imgwishlistbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				UserSessionManager usMgr = new UserSessionManager(context);
				if (usMgr.isUserLoggedIn()) {
					emailId = usMgr.getUserDetails().get("email");
					Product p = new Product();
					p.setServiceUrl(UrlInfo.ADDWishlist);

					p.setId(pdata[id].getId());
					p.setId(pdata[id].getUuid());
					p.setProductName(pdata[id].getProductName());
				    p.setProductDescription(pdata[id].getProductDescription());
					p.setImage(pdata[id].getImage());
			        p.setProductPrice(pdata[id].getProductPrice());
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
					    DatabaseHandler db=new DatabaseHandler(context);
					    Product p=new Product();
					    p.setUuid(pdata[id].getUuid());
						p.setProductName(pdata[id].getProductName());
						p.setProductDescription(pdata[id].getProductDescription());
						p.setImage(pdata[id].getImage());
						p.setProductPrice(pdata[id].getProductPrice());
					    db.addWishList(p);
					   
						Toast.makeText(getContext(), "Your item is added to Wish List",
								Toast.LENGTH_SHORT).show();
						

				}
			}
		});*/
		return rowView;
	}

	private class ViewHolder {
		public TextView txtTitle;
		public TextView txtTitle1;
		public TextView txtTitle2;
		public ImageView imageView;
		//public ImageView imgwishlistbtn;

	}

}
