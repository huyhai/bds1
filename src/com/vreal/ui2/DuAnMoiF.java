package com.vreal.ui2;

import com.android.vrealapp.R;
import com.vreal.adapter.DuanAdapter;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifySomesDataListener;
import com.vrealvn.vrealapp.HotDealFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class DuAnMoiF extends Fragment implements OnClickListener {
	private ListView lvDuan;
	private RelativeLayout rlFilter;
	private RelativeLayout rlMap;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.duanmoi, container, false);
		initView(rootView);
		return rootView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		rlFilter.setVisibility(View.VISIBLE);
		rlMap.setVisibility(View.VISIBLE);
		Main.setTextTop("Dự án mới");
	}

	private void initView(View rootView) {
		lvDuan = (ListView) rootView.findViewById(R.id.lvDuan);
		rlFilter = (RelativeLayout) getActivity().findViewById(R.id.rlFilter);
		rlMap = (RelativeLayout) getActivity().findViewById(R.id.rlMap);

		DuanAdapter adapter = new DuanAdapter(getActivity(), null, no);
		lvDuan.setAdapter(adapter);

		rlFilter.setOnClickListener(this);
		rlMap.setOnClickListener(this);
	}

	NotifySomesDataListener no = new NotifySomesDataListener() {

		@Override
		public void onReturnDataString(String id) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReturnData(int id) {
			((HotDealFragmentActivity) getActivity()).startFragment(new SearchF());
			// HotdealUtilities.startActivity(getActivity(), DetailF.class, "");

		}
	};

	@Override
	public void onPause() {
		super.onPause();
		rlFilter.setVisibility(View.GONE);
		rlMap.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		if (v == rlFilter) {
			((HotDealFragmentActivity) getActivity()).startFragment(new FilterF());
		} else if (v == rlMap) {
			((HotDealFragmentActivity) getActivity()).startFragment(new MapF());
		}

	}
}
