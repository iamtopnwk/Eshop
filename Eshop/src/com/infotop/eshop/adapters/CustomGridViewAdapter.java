package com.infotop.eshop.adapters;

import com.infotop.eshop.R;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.sidefragment.BooksFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGridViewAdapter extends ArrayAdapter<String>{

	private final Activity context;
    private final String[] productName;
    private final Integer[] imageId;
    private final String[] description;
    private final String[] price;
    

    public CustomGridViewAdapter(Activity context,String[] productName, Integer[] imageId,String[] description,String[] price) {

    	super(context, R.layout.custom_gridview, productName);
    	this.context = context;
    	this.productName = productName;
    	this.imageId = imageId;
    	this.description=description;
    	this.price=price;
}

@SuppressLint({ "ViewHolder", "InflateParams" })
@Override
public View getView(int position, View view, ViewGroup parent) {

	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.custom_gridview, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.product_Name);
	TextView txtAuthor=(TextView) rowView.findViewById(R.id.product_description);
	TextView txtPrice=(TextView) rowView.findViewById(R.id.product_price);
	ImageView imageView = (ImageView) rowView.findViewById(R.id.productImg);
	
	txtTitle.setText(productName[position]);
	txtAuthor.setText(description[position]);
	txtPrice.setText(price[position]);
	imageView.setImageResource(imageId[position]);
	
	return rowView;
}


}