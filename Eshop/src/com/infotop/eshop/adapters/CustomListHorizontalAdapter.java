package com.infotop.eshop.adapters;

import com.infotop.eshop.R;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.activities.ProductDetailsActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListHorizontalAdapter extends ArrayAdapter<String>{
	
	private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    private final String[] price;
    

    public CustomListHorizontalAdapter(Activity context,String[] web, Integer[] imageId,String[] price) {

    	super(context, R.layout.custom_list_horizontal, web);
    	this.context = context;
    	this.web = web;
    	this.imageId = imageId;
    	this.price=price;
}
    

@SuppressLint({ "ViewHolder", "InflateParams" })
@Override
public View getView(int position, View view, ViewGroup parent) {

	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.custom_list_horizontal, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.books_name);
	TextView txtPrice=(TextView) rowView.findViewById(R.id.books_price);
	ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
	
	txtTitle.setText(web[position]);
	txtPrice.setText(price[position]);
	imageView.setImageResource(imageId[position]);
	
	return rowView;
}


}
