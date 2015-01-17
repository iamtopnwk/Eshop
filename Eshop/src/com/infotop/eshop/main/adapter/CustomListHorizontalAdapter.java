package com.infotop.eshop.main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.infotop.eshop.R;
import com.infotop.eshop.model.Category;

public class CustomListHorizontalAdapter extends ArrayAdapter<Category> {

	private Context context;
	private Category[] category;
	private Integer[] productImage;

	public CustomListHorizontalAdapter(Context context, Category[] category,
			Integer[] productImage) {

		super(context, R.layout.drawer_list_item, category);
		this.context = context;
		this.category = category;
		this.productImage = productImage;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View view, ViewGroup parent) {

		View rowView = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.home_horizontal_list, null);

		TextView txtTitle1 = (TextView) rowView.findViewById(R.id.product_name);
		ImageView imageView1 = (ImageView) rowView.findViewById(R.id.img);

		txtTitle1.setText(category[position].getCategoryName());
		imageView1.setImageResource(productImage[position]);

		return rowView;
	}

}
