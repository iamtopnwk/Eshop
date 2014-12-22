package com.infotop.eshop.main;

import java.util.ArrayList;

import com.infotop.eshop.R;
import com.infotop.eshop.product.BookDetailsActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.FloatMath;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ZoomActivity extends Activity {
	BookDetailsActivity bd = new BookDetailsActivity();
	protected ImageLoader loader = ImageLoader.getInstance();
	ImageView imageDetail;
	//Matrix matrix = new Matrix();
	//Matrix savedMatrix = new Matrix();
	//PointF startPoint = new PointF();
	//PointF midPoint = new PointF();
	//float oldDist = 1f;
	//static final int NONE = 0;
	//static final int DRAG = 1;
	//static final int ZOOM = 2;
	//int mode = NONE;
	ViewPager viewPager;
	//MyPagerAdapter myPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zoom);
		viewPager = (ViewPager)findViewById(R.id.myviewpager);
		//myPagerAdapter = new MyPagerAdapter();
		//viewPager.setAdapter(myPagerAdapter);

		//Intent i = getIntent();
		String sl=getIntent().getExtras().getString("list");
		ImageView imageView = (ImageView) findViewById(R.id.imageView1);
		loader.displayImage(sl, imageView);
		
	}
	
	
	/*private class MyPagerAdapter extends PagerAdapter{
		int NumberOfPages = size;
		
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return NumberOfPages;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			ArrayList<String> sl1 = new ArrayList<String>();
			sl1 = getIntent().getExtras().getStringArrayList("list");
		    ImageView imageView = new ImageView(ZoomActivity.this);
		    loader.displayImage(sl1.get(0), imageView);
		    
		    LayoutParams imageParams = new LayoutParams(
		    		LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		    imageView.setLayoutParams(imageParams);
		    
		    LinearLayout layout = new LinearLayout(ZoomActivity.this);
		    layout.setOrientation(LinearLayout.VERTICAL);
		    LayoutParams layoutParams = new LayoutParams(
		    		LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		  
		    layout.setLayoutParams(layoutParams);
		  
		    layout.addView(imageView);
		////////////////////////////////////////////////////////////////////////
		    
		    
		   // final int page = position;
		    layout.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					Toast.makeText(MainActivity.this, 
						"Page " + page + " clicked", 
						Toast.LENGTH_LONG).show();
				}});
		    
		    container.addView(layout);
		    return layout;
		}
		
	/////////////////////////////////////////////////////////////	
		
		
		
		
		
		
		
		
		
		 * loader.displayImage(s, imageView); loader.displayImage(s1,
		 * imageView); loader.displayImage(s2, imageView);
		 * loader.displayImage(s3, imageView);
		 

		imageView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				ImageView view = (ImageView) v;
				System.out.println("matrix=" + savedMatrix.toString());
				switch (event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_DOWN:
					savedMatrix.set(matrix);
					startPoint.set(event.getX(), event.getY());
					mode = DRAG;
					break;

				case MotionEvent.ACTION_POINTER_DOWN:
					oldDist = spacing(event);
					if (oldDist > 10f) {
						savedMatrix.set(matrix);
						midPoint(midPoint, event);
						mode = ZOOM;
					}
					break;
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
					mode = NONE;
					break;
				case MotionEvent.ACTION_MOVE:
					if (mode == DRAG) {
						matrix.set(savedMatrix);
						matrix.postTranslate(event.getX() - startPoint.x,
								event.getY() - startPoint.y);
					} else if (mode == ZOOM) {
						float newDist = spacing(event);
						if (newDist > 10f) {
							matrix.set(savedMatrix);
							float scale = newDist / oldDist;
							matrix.postScale(scale, scale, midPoint.x,
									midPoint.y);
						}
					}
					break;
				}
				view.setImageMatrix(matrix);
				return true;
			}

			@SuppressLint("FloatMath")
			private float spacing(MotionEvent event) {
				float x = event.getX(0) - event.getX(1);
				float y = event.getY(0) - event.getY(1);
				return FloatMath.sqrt(x * x + y * y);
			}

			private void midPoint(PointF point, MotionEvent event) {
				float x = event.getX(0) + event.getX(1);
				float y = event.getY(0) + event.getY(1);
				point.set(x / 2, y / 2);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zoom, menu);
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
	}*/
//}
}