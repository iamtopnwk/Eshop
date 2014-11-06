package com.infotop.eshop.adapters;

import com.infotop.eshop.R;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.sidefragment.BooksFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGridViewAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] productName;
	private final String[] imageUrl;
	private final String[] description;
	private final String[] price;
	private final DisplayImageOptions op;
	protected ImageLoader loader = ImageLoader.getInstance();

	public CustomGridViewAdapter(Activity context, String[] productName,
			String[] imageUrl, String[] description, String[] price,
			DisplayImageOptions op) {

		super(context, R.layout.custom_gridview, productName);
		this.context = context;
		this.productName = productName;
		this.imageUrl = imageUrl;
		this.description = description;
		this.price = price;
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
			holder.txtTitle = (TextView) rowView.findViewById(R.id.product_Name);
			holder.txtTitle1 = (TextView) rowView
					.findViewById(R.id.product_description);
			holder.txtTitle2 = (TextView) rowView
					.findViewById(R.id.product_price);
			holder.imageView = (ImageView) rowView
					.findViewById(R.id.productImg);
			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
		}
		holder.txtTitle.setText(productName[position]);
		holder.txtTitle1.setText(description[position]);
		holder.txtTitle2.setText(price[position]);
		loader.displayImage(imageUrl[position], holder.imageView, op, null);
		return rowView;
	}

	private class ViewHolder {
		public TextView txtTitle;
		public TextView txtTitle1;
		public TextView txtTitle2;
		public ImageView imageView;
	}

}