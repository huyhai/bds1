package com.vreal.ui2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.vrealapp.R;
import com.vreal.libs.HotdealUtilities;
import com.vreal.pageindicator.IconPagerAdapter;
import com.vreal.pageindicator.TabPageIndicator;
import com.vreal.ui.NhabanF;

public class SearchF extends Fragment {
	private static final String[] CONTENT = new String[] { "Chi tiết", "Tiện ích" };
	private static final int[] ICONS = new int[] { R.drawable.ic_chitiet, R.drawable.ic_tienich };

	// private RelativeLayout rlRefresh;
	ViewPager pager;
	TabPageIndicator indicator;
	GoogleMusicAdapter adapter;
	int pos;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		if (savedInstanceState != null) {
//			fragment = (CustomFragment) getSupportFragmentManager().findFragmentByTag("customtag");
//		} else {
//			fragment = new CustomFragment();
//			getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, "customtag").commit();
//		}
		View rootView = inflater.inflate(R.layout.simple_tabs, container, false);
		initView(rootView);
		setData();
		HotdealUtilities.showALog("S CREATE");
		return rootView;
	}

	private void initView(View rootView) {
		// rlRefresh = (RelativeLayout)
		// getActivity().findViewById(R.id.rlRefresh);
		pos=HotdealUtilities.parseInt(HotdealUtilities.getDataFragment(this));
		pager = (ViewPager) rootView.findViewById(R.id.pager);
		indicator = (TabPageIndicator) rootView.findViewById(R.id.indicator);

	}

	private void setData() {
		adapter = new GoogleMusicAdapter(getChildFragmentManager());
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
		HotdealUtilities.showALog("S RESUME");
	}

	@Override
	public void onPause() {
		super.onPause();
		// rlRefresh.setVisibility(View.GONE);
		HotdealUtilities.showALog("S PAUSE");
	}

	class GoogleMusicAdapter extends FragmentPagerAdapter  implements IconPagerAdapter {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return new DetailV2(pos);
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
