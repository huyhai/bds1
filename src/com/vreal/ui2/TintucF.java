package com.vreal.ui2;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.vrealapp.R;
import com.vreal.adapter.HeaderTintucAdapter;
import com.vreal.adapter.NewsAdapter;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vreal.model.DetailsModel;
import com.vrealvn.vrealapp.DataManager2;

public class TintucF extends Fragment {
	private ListView lvNews;
	NewsAdapter adapter;
	private ViewPager pageSlide;
	private RelativeLayout ll;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.tintuc, container, false);
		initView(rootView);
		return rootView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Main.setTextTop("Tin tức bất động sản");
	}

	ArrayList<DetailsModel> list = new ArrayList<>();
	HeaderTintucAdapter adapter1;

	private void initView(View rootView) {
		lvNews = (ListView) rootView.findViewById(R.id.lvNews);
		ll = (RelativeLayout) rootView.findViewById(R.id.ll);
		HotdealUtilities.setHeight(ll, 2.5);
		// LayoutInflater inflater = getActivity().getLayoutInflater();
		// View header = inflater.inflate(R.layout.header_tintuc, lvNews,
		// false);
		// lvNews.addHeaderView(header, null, false);
		pageSlide = (ViewPager) rootView.findViewById(R.id.pageSlide);

		if (DataManager2.getInstance().getListTintuc().size() == 0) {
			getData();
		} else {
			setAdapter();
		}

	}

	private void setAdapter() {

		for (int i = 0; i < DataManager2.getInstance().getListTintuc().size(); i++) {
			if (null != DataManager2.getInstance().getListTintuc().get(i).getListTintuc() && DataManager2.getInstance().getListTintuc().get(i).getListTintuc().size() != 0) {
				list.add(DataManager2.getInstance().getListTintuc().get(i).getListTintuc().get(0));
			}

		}
		// ScreenSlidePagerAdapter mPagerAdapter = new
		// ScreenSlidePagerAdapter(getChildFragmentManager());
		// pageSlide.setAdapter(mPagerAdapter);
		adapter1 = new HeaderTintucAdapter(getActivity(), list);
		pageSlide.setAdapter(adapter1);

		adapter = new NewsAdapter(getActivity(), DataManager2.getInstance().getListTintuc());
		lvNews.setAdapter(adapter);
		HotdealUtilities.setListViewHeightBasedOnChildren(lvNews);

	}

	private void getData() {
		DataManager2.getInstance().getTintuc(getActivity(), true, false, new NotifyDataListener() {

			@Override
			public void onNotify(String api, int id) {
				if (id == NOTIFY_OK) {
					setAdapter();
					// adapter.notifyDataSetChanged();
				} else {

				}

			}
		});
	}
}
