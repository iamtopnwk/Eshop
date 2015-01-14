package com.infotop.eshop.wishlist;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.infotop.eshop.R;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.db.Wishlist;
import com.infotop.eshop.httpservice.HttpUrl;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.utilities.UserSessionManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WishListAdapter extends ArrayAdapter<String> {

	WishListAdapter myadapter;
	private final Activity context;
	private final String[] productName;
	private final String[] imageUrl;
	private final String[] desc;
	private final String[] price;
	private final String[] productId;
	private final String[] wishlistId;
	private final DisplayImageOptions op;
	protected ImageLoader loader = ImageLoader.getInstance();
	private String emailId;
	public WishListAdapter(Activity context, String[] wishlistId,String[] productId,
			String[] productName, String[] imageUrl, String[] desc,
			String[] price, DisplayImageOptions op) {

		super(context, R.layout.wish_list, productName);
		this.context = context;
		this.productName = productName;
		this.imageUrl = imageUrl;
		this.desc = desc;
		this.price = price;
		this.productId = productId;
		this.wishlistId = wishlistId;
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
		holder.txtTitle.setText(productName[position]);
		holder.txtTitle1.setText(desc[position]);
		holder.txtTitle2.setText(price[position]);
		// holder.imageView1.setImageDrawable(drawable);
		loader.displayImage(imageUrl[position], holder.imageView, op, null);
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
								
								//UserSessionManager usMgr = new UserSessionManager(context);
								//if (usMgr.isUserLoggedIn()) {
									
									//emailId = usMgr.getUserDetails().get("email");
								
									Product p=new Product();
									p.setServiceUrl(new HttpUrl().getUrl()
											+ "/eshop/rest/deletewishlist");
									
									p.setProductId(wishlistId[id]);
									System.out.println("lllllllllllll"+wishlistId[id]);
									AsyncTask<Object, Void, String> respData=new PostOperation().execute(p);
									String pcontent;
									try {
										pcontent = respData.get();
										if (pcontent.equalsIgnoreCase("Success")) {
											Toast.makeText(context, "Your item is deleted",
													Toast.LENGTH_SHORT).show();
										
										}
											else {
											Toast.makeText(context, "Connection error", Toast.LENGTH_SHORT)
													.show();
										}
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (ExecutionException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								//}
								
								//DatabaseHandler db = new DatabaseHandler(
										//context);
								//db.deleteWishListItem(productId[id]);
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
