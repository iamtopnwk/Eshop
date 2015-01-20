package com.infotop.eshop.product;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.infotop.eshop.R;
import com.infotop.eshop.model.ImageList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProductDetailsHorizontalAdapter extends ArrayAdapter<String> {
	private final ArrayList<String> mediumimageUrls;
	private final Activity context;
	private final DisplayImageOptions op;
	protected ImageLoader loader = ImageLoader.getInstance();

	public ProductDetailsHorizontalAdapter(Activity context,
			ArrayList<String> mediumimageUrls, DisplayImageOptions op) {
		super(context, R.layout.products_details_horozontal,mediumimageUrls);

		this.context = context;
		this.mediumimageUrls = mediumimageUrls;
		this.op = op;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mediumimageUrls.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.products_details_horozontal, null);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img1);
		System.out.println("pppppppppppppppppppppppppppppppp" + position);
		loader.displayImage(mediumimageUrls.get(position), imageView, op, null);

		// TODO Auto-generated method stub
		return rowView;
	}

}
