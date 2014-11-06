package com.infotop.eshop.activities;



import com.infotop.eshop.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;


public class SearchResultsActivity extends Activity {

	
	TextView txtQuery;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_results);
		
		// get the action bar
        ActionBar actionBar = getActionBar();
 
        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);
 
         txtQuery = (TextView) findViewById(R.id.txtQuery);
 
        handleIntent(getIntent());
		
		
	}


@Override
protected void onNewIntent(Intent intent) {
    setIntent(intent);
    handleIntent(intent);
}

/**
 * Handling intent data
 */
private void handleIntent(Intent intent) {
    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
        String query = intent.getStringExtra(SearchManager.QUERY);

        
		/**
         * Use this query to display search results like 
         * 1. Getting the data from SQLite and showing in listview 
         * 2. Making webrequest and displaying the data 
         * For now we just display the query only
         */
        txtQuery.setText("Search Query: " + query);

    }

}

	
@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_results, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

