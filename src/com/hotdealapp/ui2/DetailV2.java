package com.hotdealapp.ui2;

import com.android.vrealapp.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetailV2 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.detailv2, container, false);
		initView(rootView);
		return rootView;
	}

	private void initView(View rootView) {
		// TODO Auto-generated method stub
		
	}

}
