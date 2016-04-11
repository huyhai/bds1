package com.hotdealapp.ui2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.vrealapp.R;
import com.hotdeal.libs.HotdealUtilities;
import com.hotdeal.pageindicator.IconPagerAdapter;
import com.hotdeal.pageindicator.TabPageIndicator;
import com.hotdealapp.ui.NhabanF;

public class SearchF extends Fragment {
	private static final String[] CONTENT = new String[] { "Nhà bán",
			"Cho thuê" };
	private static final int[] ICONS = new int[] { R.drawable.ic_chitiet, R.drawable.ic_tienich};

	private RelativeLayout rlRefresh;
	ViewPager pager;
	TabPageIndicator indicator;
	GoogleMusicAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater
				.inflate(R.layout.simple_tabs, container, false);
		initView(rootView);
		setData();
		HotdealUtilities.showALog("S CREATE");
		return rootView;
	}

	private void initView(View rootView) {
		rlRefresh = (RelativeLayout) getActivity().findViewById(R.id.rlRefresh);
		pager = (ViewPager) rootView.findViewById(R.id.pager);
		indicator = (TabPageIndicator) rootView.findViewById(R.id.indicator);

	}

	private void setData() {
		adapter = new GoogleMusicAdapter(getChildFragmentManager());
		pager.setAdapter(adapter);
		indicator.setViewPager(pager);
	}

	@Override
	public void onResume() {
		super.onResume();
		rlRefresh.setVisibility(View.VISIBLE);
		HotdealUtilities.showALog("S RESUME");
	}

	@Override
	public void onPause() {
		super.onPause();
		rlRefresh.setVisibility(View.GONE);
		HotdealUtilities.showALog("S PAUSE");
	}

	class GoogleMusicAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter{
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return new DetailV2();
			case 1:
				return new NhabanF();
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
