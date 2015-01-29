	package com.infotop.eshop.main.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.infotop.eshop.R;
import com.infotop.eshop.cartlist.activity.CartListMainActivity;
import com.infotop.eshop.category.activity.SubListCategoryActivity;
import com.infotop.eshop.login.ContactUsActivity;
import com.infotop.eshop.login.EshopLoginActivity;
import com.infotop.eshop.login.EshopPoliciesActivity;
import com.infotop.eshop.login.NoItemFoundActivity;
import com.infotop.eshop.main.adapter.NavDrawerListAdapter;
import com.infotop.eshop.model.Category;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.GetOperation;
import com.infotop.eshop.utilities.JsonHelper;
import com.infotop.eshop.utilities.UserSessionManager;
import com.infotop.eshop.wishlist.activity.WishListMainActivity;

//Main Activity
public class EshopMainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	// nav drawer title
	private CharSequence mDrawerTitle;
	// used to store app title
	private CharSequence mTitle;
	// slide menu items
	private String[] navMenuTitles;
	// private TypedArray navMenuIcons;
	UserSessionManager usMgr;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eshop_main);

		mTitle = mDrawerTitle = getTitle();
		usMgr = new UserSessionManager(this);
		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		// adding nav drawer items to array
		String serverURL = UrlInfo.HOMEPAGE_PATH;

		// Use AsyncTask execute Method To Prevent ANR Problem
		AsyncTask<String, Void, String> data = new GetOperation()
				.execute(serverURL);
		try {
			final Category[] navDrawerItems = (Category[]) JsonHelper.toObject(
					data.get(), Category[].class);
			adapter = new NavDrawerListAdapter(getApplicationContext(),
					navDrawerItems);
			mDrawerList.setAdapter(adapter);
			// Close progress dialog
			mDrawerList
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							Intent i = new Intent(getApplicationContext(),
									SubListCategoryActivity.class);
							i.putExtra("UUID",
									navDrawerItems[position].getUuid());
							i.putExtra("CategoryName",
									navDrawerItems[position].getCategoryName());
							// i.putExtra("jsonData", pcontent);
							startActivity(i);
							// System.out.println("Item id:"+position);
							// Toast.makeText(getApplicationContext(),"The position of child category:"+uuidPosition.get(position),
							// Toast.LENGTH_SHORT).show();
						}
					});

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
		System.gc();
	}

	/**
	 * Slide menu item click listener
	 * */
	@SuppressWarnings("unused")
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		// MenuInflater inflater = getMenuInflater();
		getMenuInflater().inflate(R.menu.eshop_main, menu);
		MenuItem logInitem = menu.findItem(R.id.abLogin);
		MenuItem logOutitem = menu.findItem(R.id.logOut);
		// usMgr = new UserSessionManager(this);
		if (!usMgr.isUserLoggedIn()) {
			logOutitem.setVisible(false);
		} else {
			logInitem.setTitle(usMgr.getUserDetails().get("name"));
		}
		return super.onCreateOptionsMenu(menu);
		// return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_search:
			return true;
		case R.id.abCartList:
		
				Intent cl = new Intent(this, CartListMainActivity.class);
				startActivity(cl);
			
			return true;
		case R.id.abLogin:
			if (!usMgr.isUserLoggedIn()) {
				Intent lgn = new Intent(this, EshopLoginActivity.class);
				startActivity(lgn);
			}
			return true;
		case R.id.abwishlist:

			
				Intent wl = new Intent(this, WishListMainActivity.class);
				startActivity(wl);
			
			return true;
		case R.id.abTrackOrder:
			return true;
		case R.id.abRateApp:
			return true;
		case R.id.abShareApp:
			return true;
		case R.id.abPolicies:
			Intent policy = new Intent(this, EshopPoliciesActivity.class);
			startActivity(policy);
			return true;
		case R.id.abContactUs:
			Intent cu = new Intent(this, ContactUsActivity.class);
			startActivity(cu);
			return true;
		case R.id.logOut:
			UserSessionManager us = new UserSessionManager(this);
			us.logoutUser();
			Intent lout = new Intent(this, EshopMainActivity.class);
			startActivity(lout);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
