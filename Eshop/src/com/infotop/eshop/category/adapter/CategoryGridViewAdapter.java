package com.infotop.eshop.category.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.infotop.eshop.R;
import com.infotop.eshop.model.Category;



public class CategoryGridViewAdapter extends ArrayAdapter<Category>{
	private final Activity context;
	private final Category[] cdata;
    private final int Imageid;
	
	public CategoryGridViewAdapter(Activity context,Category[] cdata,int Imageid) {

		super(context, R.layout.category_grid_items, cdata);
		this.context = context;
		this.cdata=cdata;
		this.Imageid=Imageid;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.category_grid_items, null);
        }
         

        TextView txtTitle = (TextView) convertView.findViewById(R.id.category_Name);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.grid_image);
        System.out.println("CDATA CATEGORY NAME "+cdata[position].getCategoryName());
        txtTitle.setText(cdata[position].getCategoryName());
        imageView.setImageResource(Imageid);
        return convertView;
}
}
