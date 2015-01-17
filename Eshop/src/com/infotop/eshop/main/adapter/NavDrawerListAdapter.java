package com.infotop.eshop.main.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.infotop.eshop.R;
import com.infotop.eshop.model.Category;

public class NavDrawerListAdapter extends ArrayAdapter<Category> {
	
	private Context context;
	private Category[] navDrawerItems;
	
	public NavDrawerListAdapter(Context context, Category[] navDrawerItems){
		super(context, R.layout.drawer_list_item,navDrawerItems);
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	/*@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {		
		return navDrawerItems.get(position);
	}*/

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }
         
       // ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
     
        //imgIcon.setImageResource(navDrawerItems.get(position).getIcon());        
        txtTitle.setText(navDrawerItems[position].getCategoryName());

        return convertView;
	}

}
