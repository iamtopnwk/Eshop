package com.infotop.eshop.category.adapter;


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
public class CategoryListAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] web;
	private final Integer[] imageId;
	private final String[] desc;

	public CategoryListAdapter(Activity context, String[] web,
			Integer[] imageId, String[] desc) {

		super(context, R.layout.category_list, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
		this.desc = desc;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.category_list, null);
		System.out.println("Cate Context value is:" + context);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.subCategory);
		TextView txtTitle1 = (TextView) rowView.findViewById(R.id.subCategoryDesc);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.subimg);
		imageView.setImageResource(imageId[position]);
		txtTitle.setText(web[position]);
		txtTitle1.setText(desc[position]);
		return rowView;
	}

}