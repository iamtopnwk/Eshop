package com.infotop.eshop.adapters;


import com.infotop.eshop.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint({ "ViewHolder", "InflateParams" })
public class ProductListAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] productName;
	private final Integer[] imageId;
	private final String[] desc;
	private final String[] price;

	public ProductListAdapter(Activity context, String[] productName,
			Integer[] imageId, String[] desc, String[] price) {

		super(context, R.layout.product_list, productName);
		this.context = context;
		this.productName = productName;
		this.imageId = imageId;
		this.desc = desc;
		this.price = price;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.product_list, null);
		System.out.println("Cate Context value is:" + context);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.productName);
		TextView txtTitle1 = (TextView) rowView.findViewById(R.id.productDesc);
		TextView txtTitle2 = (TextView) rowView.findViewById(R.id.productprice);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.productImg);
		imageView.setImageResource(imageId[position]);
		txtTitle.setText(productName[position]);
		txtTitle1.setText(desc[position]);
		txtTitle2.setText(price[position]);
		return rowView;
	}

}