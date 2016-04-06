package com.hotdealapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.vrealapp.R;
import com.hotdeal.libs.HotdealUtilities;
import com.hotdealapp.ui2.LoaiNhaDat;

public class NhabanF extends Fragment implements OnClickListener {
	private RelativeLayout rlBatki;
	private RelativeLayout rl1;
	private RelativeLayout rl2;
	private RelativeLayout rl3;
	private RelativeLayout rl4;
	private RelativeLayout rl5;

	private RelativeLayout rlLoai;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.nhaban, container, false);
		initView(rootView);
		return rootView;
	}

	private void initView(View rootView) {
		rlBatki = (RelativeLayout) rootView.findViewById(R.id.rlBatki);
		rl1 = (RelativeLayout) rootView.findViewById(R.id.rl1);
		rl2 = (RelativeLayout) rootView.findViewById(R.id.rl2);
		rl3 = (RelativeLayout) rootView.findViewById(R.id.rl3);
		rl4 = (RelativeLayout) rootView.findViewById(R.id.rl4);
		rl5 = (RelativeLayout) rootView.findViewById(R.id.rl5);
		rlLoai = (RelativeLayout) rootView.findViewById(R.id.rlLoai);

		// int h=10;
		// HotdealUtilities.setHeight(rl1, h);
		// HotdealUtilities.setHeight(rl2, h);
		// HotdealUtilities.setHeight(rl3, h);
		// HotdealUtilities.setHeight(rl4, h);
		// HotdealUtilities.setHeight(rl5, h);
		// HotdealUtilities.setHeight(rlBatki, h);
		rl1.setOnClickListener(this);
		rlLoai.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v == rl1) {
		} else if (v == rlLoai) {
			HotdealUtilities.startActivity(getActivity(), LoaiNhaDat.class, "");

		}

	}
}
