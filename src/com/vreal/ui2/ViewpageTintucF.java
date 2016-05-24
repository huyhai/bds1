package com.vreal.ui2;

import java.util.ArrayList;

import com.android.vrealapp.R;
import com.vreal.adapter.HeaderTintucAdapter;
import com.vreal.model.DetailsModel;
import com.vrealvn.vrealapp.DataManager2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewpageTintucF extends Fragment {
	private ViewPager pageSlide;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.header_tintuc, container, false);
		initView(rootView);
		return rootView;
	}

	ArrayList<DetailsModel> list = new ArrayList<>();
	HeaderTintucAdapter adapter1;

	private void initView(View rootView) {
		pageSlide = (ViewPager) rootView.findViewById(R.id.pageSlide);
		for (int i = 0; i < DataManager2.getInstance().getListTintuc().size(); i++) {
			if (null != DataManager2.getInstance().getListTintuc().get(i).getListTintuc() && DataManager2.getInstance().getListTintuc().get(i).getListTintuc().size() != 0) {
				list.add(DataManager2.getInstance().getListTintuc().get(i).getListTintuc().get(0));
			}

		}
		adapter1 = new HeaderTintucAdapter(getActivity(), list);
		pageSlide.setAdapter(adapter1);
	}
}
