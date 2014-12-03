package com.infotop.eshop.adapters;

import java.util.List;

import com.infotop.eshop.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ProductDetailsHorizontalAdapter extends ArrayAdapter<String> {
	private final List<String> imageUrls;
	private final Activity context;
	private final DisplayImageOptions op;
	protected ImageLoader loader = ImageLoader.getInstance();
	
	public ProductDetailsHorizontalAdapter(Activity context,List<String> imageUrls,DisplayImageOptions op) {
		super(context,R.layout.products_details_horozontal, imageUrls);
		
		this.context=context;
		this.imageUrls=imageUrls;
		this.op=op;
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageUrls.size();
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.products_details_horozontal, null);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img1);
	    int size=getCount();
		loader.displayImage(imageUrls.get(position), imageView, op, null);		
		// TODO Auto-generated method stub
		return rowView;
	}
	
	
	

}
