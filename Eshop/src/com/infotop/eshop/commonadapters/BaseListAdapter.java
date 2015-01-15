package com.infotop.eshop.commonadapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.infotop.eshop.R;

@SuppressLint({ "ViewHolder", "InflateParams", "SimpleDateFormat" })
public class BaseListAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] categoryName;
	public TextView txtTitle;

	public BaseListAdapter(Activity context, String[] categoryName) {

		super(context, R.layout.baselist, categoryName);
		this.context = context;
		this.categoryName = categoryName;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.baselist, null, true);
		txtTitle = (TextView) rowView.findViewById(R.id.baseListText);
		txtTitle.setText(categoryName[position]);

		return rowView;

	}

}