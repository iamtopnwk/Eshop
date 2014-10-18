package com.infotop.eshop.sidefragment;

import com.infotop.eshop.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.TabHost;

public class HomeFragment extends Fragment {

	private TabHost mTabHost;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_home, container,
				false);

		return rootView;
	}
}
