package com.hotdealapp.ui2;

import com.android.vrealapp.R;
import com.hotdeal.libs.HotdealUtilities;
import com.hotdeal.pageindicator.TabPageIndicator;
import com.hotdealapp.ui.NhabanF;

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
import android.widget.RelativeLayout;

public class SearchF extends Fragment {
	private static final String[] CONTENT = new String[] { "Nhà bán", "Cho thuê" };
	private RelativeLayout rlRefresh;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.simple_tabs, container, false);
		initView(rootView);

		HotdealUtilities.showALog("S CREATE");
		return rootView;
	}

	private void initView(View rootView) {
		rlRefresh = (RelativeLayout) getActivity().findViewById(R.id.rlRefresh);
		
		GoogleMusicAdapter adapter = new GoogleMusicAdapter(getActivity().getSupportFragmentManager());
		ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) rootView.findViewById(R.id.indicator);
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

	class GoogleMusicAdapter extends FragmentStatePagerAdapter {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return new NhabanF();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length].toUpperCase();
		}

		@Override
		public int getCount() {
			return 2;
		}
	}
}
