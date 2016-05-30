package com.vreal.ui2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.vrealapp.R;
import com.vreal.libs.HotdealUtilities;
import com.vreal.pageindicator.IconPagerAdapter;
import com.vreal.pageindicator.TabPageIndicator;
import com.vreal.ui.NhabanF;
import com.vrealvn.vrealapp.HotDealFragmentActivity;

public class SearchF extends HotDealFragmentActivity {
	private static final String[] CONTENT = new String[] { "Chi tiết", "Tiện ích" };
	private static final int[] ICONS = new int[] { R.drawable.ic_chitiet, R.drawable.ic_tienich };

	private RelativeLayout rlRefresh;
	ViewPager pager;
	TabPageIndicator indicator;
	GoogleMusicAdapter adapter;
	int pos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_tabs);
		initView();
		setData();
	}

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View rootView = inflater.inflate(R.layout.simple_tabs, container, false);
	// initView(rootView);
	// setData();
	// HotdealUtilities.showALog("S CREATE");
	// return rootView;
	// }

	private void initView() {
//		rlRefresh = (RelativeLayout) getActivity().findViewById(R.id.rlRefresh);
//		pos = HotdealUtilities.parseInt(HotdealUtilities.getDataFragment(this));
		pos = HotdealUtilities.parseInt(HotdealUtilities.getDataBundle(this));
		llTopBar = (LinearLayout) findViewById(R.id.llTopBar);
		HotdealUtilities.setHeight(llTopBar, 11.5);
		
		pager = (ViewPager) findViewById(R.id.pager);
		indicator = (TabPageIndicator) findViewById(R.id.indicator);
		imgTooggle=(ImageView)findViewById(R.id.imgTooggle);
		HotdealUtilities.setWidthHeight(imgTooggle, 17, 35);
		findViewById(R.id.rlToogle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SearchF.this.finish();

			}
		});

	}

	private void setData() {
		adapter = new GoogleMusicAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
		indicator.setViewPager(pager);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onResume() {
		super.onResume();
		// rlRefresh.setVisibility(View.VISIBLE);
	}

	@Override
	public void onPause() {
		super.onPause();
		// rlRefresh.setVisibility(View.GONE);
	}

	class GoogleMusicAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return new DetailV2();
			case 1:
				return new MapF(pos);
			}
			return null;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length].toUpperCase();
		}

		@Override
		public int getCount() {
			return 2;
		}

		@Override
		public int getIconResId(int index) {
			return ICONS[index];
		}
	}
}
