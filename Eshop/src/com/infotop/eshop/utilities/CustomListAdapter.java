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

public class CustomListAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] web;
	private final Integer[] imageId;
	private final String[] author;
	private final String[] price;

	public CustomListAdapter(Activity context, String[] web, Integer[] imageId,
			String[] author, String[] price) {

		super(context, R.layout.custom_list, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
		this.author = author;
		this.price = price;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View view, ViewGroup parent) {

		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.custom_list, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.books_name);
		TextView txtAuthor = (TextView) rowView.findViewById(R.id.books_author);
		TextView txtPrice = (TextView) rowView.findViewById(R.id.books_price);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

		txtTitle.setText(web[position]);
		txtAuthor.setText(author[position]);
		txtPrice.setText(price[position]);
		imageView.setImageResource(imageId[position]);

		return rowView;
	}

}