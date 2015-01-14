    package com.infotop.eshop.cartlist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.infotop.eshop.R;
import com.infotop.eshop.db.DatabaseHandler;
import com.infotop.eshop.httpservice.HttpUrl;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.utilities.UserSessionManager;
import com.infotop.eshop.wishlist.PostOperation;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

	@SuppressLint({ "ViewHolder", "InflateParams" })
	public class CartListAdapter extends ArrayAdapter<String> {
		
		private final Activity context;
		private final String[] productName;
		private final String[] desc;
		private final String[] imageUrl;
		private final String[] price;
		private final String[] productId;
		private final String[] cartListId;
		private final DisplayImageOptions op;
		protected ImageLoader loader = ImageLoader.getInstance();

		public CartListAdapter(Activity context, String[] productId,String[] cartListId,
				String[] productName, String[] imageUrl, String[] desc,
				String[] price, DisplayImageOptions op) {

			super(context, R.layout.cart_list_adapter, productName);
			this.context = context;
			this.productName = productName;
			this.imageUrl = imageUrl;
			this.desc = desc;
			this.price = price;
			this.productId = productId;
			this.cartListId=cartListId;
			this.op = op;
		}
		
		
	@Override
	public View getView(int position, View view, ViewGroup parent) {
			View rowView = view;
			final ViewHolder holder;
			if (rowView == null) {
				rowView = context.getLayoutInflater().inflate(
						R.layout.cart_list_adapter, parent, false);
				holder = new ViewHolder();
				holder.txtTitle = (TextView) rowView.findViewById(R.id.productName);
				holder.txtTitle1 = (TextView) rowView
						.findViewById(R.id.productDesc);
				holder.txtTitle2 = (TextView) rowView
						.findViewById(R.id.productprice);
				holder.imageView = (ImageView) rowView
						.findViewById(R.id.productImg);
				/*//holder.imgwishlistbtn = (ImageView) rowView
						.findViewById(R.id.imgwishlistbtn);*/
				holder.imageView1 = (ImageView) rowView
						 .findViewById(R.id.delete);
				//Toast.makeText(view.getContext() + isCheckedOrNot(holder.checkBox ), Toast.LENGTH_LONG).show();
				rowView.setTag(holder);
			} else {
				holder = (ViewHolder) rowView.getTag();
			}
			 final int id = position;
			holder.txtTitle.setText(productName[position]);
			holder.txtTitle1.setText(desc[position]);
			holder.txtTitle2.setText(price[position]);
			loader.displayImage(imageUrl[position], holder.imageView, op, null);
			holder.imageView1.setOnClickListener( new View.OnClickListener() { 
			     public void onClick(View v) { 
			    	 AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								context);
						alertDialog.setTitle("Confirm Delete...");
						alertDialog.setMessage("Are you sure you want delete this?");
						alertDialog.setPositiveButton("YES",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										UserSessionManager usMgr=new UserSessionManager(context);
										Product p=new Product();
										p.setServiceUrl(new HttpUrl().getUrl()
												+ "/eshop/rest/deletecartlist");
//										DatabaseHandler db = new DatabaseHandler(
//												context);
//										db.deleteCartListItem(productId[id]);
										// myadapter.notifyDataSetChanged();
										p.setEmailId(usMgr.getUserDetails().get("email"));
										p.setCategoryId(cartListId[id]);
										System.out.println("cartList id:===="+cartListId[id]);
										AsyncTask<Object, Void, String> respData=new PostOperation().execute(p);
										String pcontent;
										try {
											pcontent = respData.get();
											if (pcontent.equalsIgnoreCase("Success")) {
												Toast.makeText(context, "Your item is deleted from Wish List",
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
										
										((Activity) context).finish();
										Intent intent = new Intent(context,
												CartListMainActivity.class);
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
			//public ImageView imgwishlistbtn;
		}
	

}
