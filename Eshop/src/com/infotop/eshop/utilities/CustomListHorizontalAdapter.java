package com.infotop.eshop.utilities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.infotop.eshop.R;

public class CustomListHorizontalAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] web;
	private final Integer[] imageId;
	private String[] price;

	public CustomListHorizontalAdapter(Activity context, String[] web,
			Integer[] imageId, String[] price) {

		super(context, R.layout.custom_list_horizontal, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
		this.price = price;
	}

	public CustomListHorizontalAdapter(Activity context, String[] web,
			Integer[] imageId) {

		super(context, R.layout.home_horizontal_list, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;

	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View view, ViewGroup parent) {

		View rowView = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.custom_list_horizontal, null);

		View rowView1 = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.home_horizontal_list, null);

		TextView txtTitle = (TextView) rowView.findViewById(R.id.books_name);
		// TextView txtPrice = (TextView)
		// rowView.findViewById(R.id.books_price);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

		txtTitle.setText(web[position]);
		// txtPrice.setText(price[position]);
		imageView.setImageResource(imageId[position]);

		TextView txtTitle1 = (TextView) rowView1
				.findViewById(R.id.product_name);
		ImageView imageView1 = (ImageView) rowView1.findViewById(R.id.img);

		txtTitle1.setText(web[position]);
		imageView1.setImageResource(imageId[position]);
		if (view == rowView)
			return rowView;

		return rowView1;
	}

	@Override
	public int getCount() {
		return web.length;
	}

	@Override
	public String getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
