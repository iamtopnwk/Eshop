package com.infotop.eshop.wishlist.adapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.infotop.eshop.R;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.wishlist.PostOperation;
import com.infotop.eshop.wishlist.activity.WishListMainActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class WishListAdapter extends ArrayAdapter<Product> {

	WishListAdapter myadapter;
	private final Activity context;
	private final Product[] pdata;
	private final DisplayImageOptions op;
	protected ImageLoader loader = ImageLoader.getInstance();

	public WishListAdapter(Activity context,Product[] pdata, DisplayImageOptions op) {

		super(context, R.layout.wish_list,pdata);
		this.context = context;
		this.pdata=pdata;
		this.op = op;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		View rowView = view;
		final ViewHolder holder;
		if (rowView == null) {
			rowView = context.getLayoutInflater().inflate(R.layout.wish_list,
					parent, false);
			System.out.println("Cate Context value is:" + context);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) rowView.findViewById(R.id.productName);
			holder.txtTitle1 = (TextView) rowView
					.findViewById(R.id.productDesc);
			holder.txtTitle2 = (TextView) rowView
					.findViewById(R.id.productprice);
			holder.imageView = (ImageView) rowView
					.findViewById(R.id.productImg);
			holder.imageView1 = (ImageView) rowView.findViewById(R.id.delete);
			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
		}
		final int id = position;
		holder.txtTitle.setText(pdata[position].getProductName());
		holder.txtTitle1.setText(pdata[position].getDescription());
		holder.txtTitle2.setText(pdata[position].getPrice());
		// holder.imageView1.setImageDrawable(drawable);
		loader.displayImage(pdata[position].getImageUrl(), holder.imageView, op, null);
		holder.imageView1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						context);
				alertDialog.setTitle("Confirm Delete...");
				alertDialog.setMessage("Are you sure you want delete this?");
				alertDialog.setPositiveButton("YES",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {

								// UserSessionManager usMgr = new
								// UserSessionManager(context);
								// if (usMgr.isUserLoggedIn()) {

								// emailId =
								// usMgr.getUserDetails().get("email");

								Product p = new Product();
								p.setServiceUrl(UrlInfo.DELETE_WISHLIST);

								p.setProductId(pdata[id].getWishlistId());
								System.out.println("lllllllllllll"
										+ pdata[id]);
								AsyncTask<Object, Void, String> respData = new PostOperation()
										.execute(p);
								String pcontent;
								try {
									pcontent = respData.get();
									if (pcontent.equalsIgnoreCase("Success")) {
										Toast.makeText(context,
												"Your item is deleted",
												Toast.LENGTH_SHORT).show();

									} else {
										Toast.makeText(context,
												"Connection error",
												Toast.LENGTH_SHORT).show();
									}
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ExecutionException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								// }

								// DatabaseHandler db = new DatabaseHandler(
								// context);
								// db.deleteWishListItem(productId[id]);
								// myadapter.notifyDataSetChanged();
								((Activity) context).finish();
								Intent intent = new Intent(context,
										WishListMainActivity.class);
								context.startActivity(intent);
							}
						});

				alertDialog.setNegativeButton("NO",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Write your code here to execute after dialog
								// Toast.makeText(getApplicationContext(),
								// "You clicked on NO",
								// Toast.LENGTH_SHORT).show();
								dialog.cancel();
							}
						});

				// Showing Alert Message
				alertDialog.show();

				// TODO Auto-generated method stub

			}
		});

		return rowView;

	}

	private class ViewHolder {
		public TextView txtTitle;
		public TextView txtTitle1;
		public TextView txtTitle2;
		public ImageView imageView;
		public ImageView imageView1;
	}
}
