package com.infotop.eshop.utilities;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;

public class App extends Application {
	 @Override
	    public void onCreate() {
	 
	        super.onCreate();
	        initImageLoader(getApplicationContext());
	    }
	 
	    public static void initImageLoader(Context context) {
	      
	        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
	                .threadPriority(Thread.NORM_PRIORITY - 2)
	                .denyCacheImageMultipleSizesInMemory()
	                .memoryCache(new LruMemoryCache(1024 * 1024 * 1024))
	                .discCacheFileNameGenerator(new Md5FileNameGenerator())
	                .tasksProcessingOrder(QueueProcessingType.LIFO)
	                .build();
	 
	        ImageLoader.getInstance().init(config);
	    }
}
