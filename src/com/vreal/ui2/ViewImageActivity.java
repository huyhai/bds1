package com.vreal.ui2;

import com.android.vrealapp.R;
import com.vreal.adapter.ViewImagesAdapter;
import com.vreal.libs.ExtendedViewPager;
import com.vrealvn.vrealapp.DataManager2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;


public class ViewImageActivity extends Activity {
	private ExtendedViewPager pageSlide;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_images);
		pageSlide = (ExtendedViewPager) findViewById(R.id.view_pager);
		ViewImagesAdapter adapter = new ViewImagesAdapter(this, DataManager2.getInstance()
				.getVrealModel().getListPhoto());
		pageSlide.setAdapter(adapter);
	}

}
