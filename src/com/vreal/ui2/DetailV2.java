package com.vreal.ui2;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.vrealapp.R;
import com.vreal.adapter.DetailsBannerAdapter;
import com.vreal.libs.DepthPageTransformer;
import com.vreal.libs.HotdealUtilities;
import com.vreal.pageindicator.CirclePageIndicator;
import com.vreal.pageindicator.PageIndicator;

public class DetailV2 extends Fragment {
	PageIndicator mIndicator;
	private ViewPager pageSlide;
	private RelativeLayout rlSlide;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.detailv2, container, false);
		initView(rootView);
		return rootView;
	}

	private void initView(View rootView) {
		pageSlide = (ViewPager) rootView.findViewById(R.id.pageSlide);
		mIndicator = (CirclePageIndicator) rootView.findViewById(R.id.indicator);
		rlSlide = (RelativeLayout) rootView.findViewById(R.id.rlSlide);
		
		HotdealUtilities.setHeight(rlSlide, 3);

		ArrayList<String> list = new ArrayList<>();
		list.add("http://img.v3.news.zdn.vn/w660/Uploaded/wyhktpu/2016_04_11/Hai_mau_xe_long_lay_dam_tinh_thoi_trang_cua_Yamaha_1.jpg");
		list.add("http://img.v3.news.zdn.vn/w660/Uploaded/wyhktpu/2016_04_11/Hai_mau_xe_long_lay_dam_tinh_thoi_trang_cua_Yamaha_6.jpg");
		list.add("http://img.v3.news.zdn.vn/w660/Uploaded/wyhktpu/2016_04_11/Can_canh_nakedbike_Yamaha_MT03_5.jpg");
		list.add("http://img.v3.news.zdn.vn/w660/Uploaded/wyhktpu/2016_04_11/Can_canh_nakedbike_Yamaha_MT03_8.jpg");
		DetailsBannerAdapter adapter = new DetailsBannerAdapter(getActivity(), list);
		pageSlide.setAdapter(adapter);
		mIndicator.setViewPager(pageSlide);
		pageSlide.setPageTransformer(true, new DepthPageTransformer());

	}

}
