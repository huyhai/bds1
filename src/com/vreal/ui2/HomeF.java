package com.vreal.ui2;

import com.android.vrealapp.R;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vreal.ui.NhabanF;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.HotDealFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HomeF extends Fragment implements OnClickListener {
	private ImageView img;
	private RelativeLayout llBanthue;
	private RelativeLayout llDuanmoi;
	private RelativeLayout llMenu4;
	private RelativeLayout llMenu3;
	private RelativeLayout llTintuc;
	private RelativeLayout llMenu4M;
	private LinearLayout llTopBar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.home_f, container, false);
		initView(rootView);
		HotdealUtilities.showALog("Home CREATE");
		return rootView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		HotdealUtilities.showALog("Home RESUME");
		Main.setTextTop("Vreal.vn");
		llTopBar.setBackgroundResource(R.drawable.bg_home11);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		llTopBar.setBackgroundResource(R.color.vreal_orange);
		HotdealUtilities.showALog("Home PAUSE");
	}

	private void initView(View rootView) {
		img = (ImageView) rootView.findViewById(R.id.img);
		llBanthue = (RelativeLayout) rootView.findViewById(R.id.llBanthue);
		llDuanmoi = (RelativeLayout) rootView.findViewById(R.id.llDuanmoi);
		llMenu4 = (RelativeLayout) rootView.findViewById(R.id.llMenu4);
		llMenu3 = (RelativeLayout) rootView.findViewById(R.id.llMenu3);
		llTintuc = (RelativeLayout) rootView.findViewById(R.id.llTintuc);
		llMenu4M = (RelativeLayout) rootView.findViewById(R.id.llMenu4M);
		llTopBar = (LinearLayout) getActivity().findViewById(R.id.llTopBar);
		HotdealUtilities.setHeight(img, 3);
		llBanthue.setOnClickListener(this);
		llDuanmoi.setOnClickListener(this);
		llMenu4.setOnClickListener(this);
		llMenu3.setOnClickListener(this);
		llTintuc.setOnClickListener(this);
		llMenu4M.setOnClickListener(this);

	}



	@Override
	public void onClick(View v) {
		HotdealUtilities.setClickAnim(v);
		if (v == llBanthue) {

			((HotDealFragmentActivity) getActivity()).startFragment(new NhabanF());
		} else if (v == llDuanmoi) {
			((HotDealFragmentActivity) getActivity()).startFragment(new NhabanF());
		} else if (v == llMenu4) {
			((HotDealFragmentActivity) getActivity()).startFragment(new DuAnMoiF());
		} else if (v == llMenu3) {
			((HotDealFragmentActivity) getActivity()).startFragment(new DuAnMoiF());
		} else if (v == llTintuc) {
			((HotDealFragmentActivity) getActivity()).startFragment(new TintucF());
		} else if (v == llMenu4M) {
			((HotDealFragmentActivity) getActivity()).startFragment(new TimMoiGioiF());
		}
	}
}
