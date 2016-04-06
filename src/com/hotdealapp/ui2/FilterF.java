package com.hotdealapp.ui2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;

public class FilterF extends Fragment {
	private RelativeLayout rlRefresh;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.filter, container, false);
		initView(rootView);
		return rootView;
	}

	private void initView(View rootView) {
		rlRefresh = (RelativeLayout) getActivity().findViewById(R.id.rlRefresh);

	}

	@Override
	public void onResume() {
		super.onResume();
		rlRefresh.setVisibility(View.VISIBLE);
		Main.setTextTop("Bộ lọc");
	}

	@Override
	public void onPause() {
		super.onPause();
		rlRefresh.setVisibility(View.GONE);
	}
}
