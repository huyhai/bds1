package com.vreal.ui2;

import com.android.vrealapp.R;
import com.vreal.adapter.DuanAdapter;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifySomesDataListener;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.HotDealFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DuAnMoiF extends Fragment implements OnClickListener {
	private ListView lvDuan;
	private RelativeLayout rlFilter;
	private RelativeLayout rlMap;
	private TextView tvKq;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.duanmoi, container, false);
		initView(rootView);
		setData();
		return rootView;
	}

	private void setData() {
		tvKq.setText(DataManager2.getInstance().getListSearch().size()+" kết quả");
		DuanAdapter adapter = new DuanAdapter(getActivity(), DataManager2.getInstance().getListSearch(), no);
		lvDuan.setAdapter(adapter);
		
	}

	@Override
	public void onResume() {
		super.onResume();
		rlFilter.setVisibility(View.VISIBLE);
		rlMap.setVisibility(View.VISIBLE);
		Main.setTextTop("Dự án mới");
	}

	private void initView(View rootView) {
		tvKq = (TextView) rootView.findViewById(R.id.tvKq);
		lvDuan = (ListView) rootView.findViewById(R.id.lvDuan);
		rlFilter = (RelativeLayout) getActivity().findViewById(R.id.rlFilter);
		rlMap = (RelativeLayout) getActivity().findViewById(R.id.rlMap);

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
			((HotDealFragmentActivity) getActivity()).startFragment(new SearchF(), id+"");
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
			((HotDealFragmentActivity) getActivity()).startFragment(new FilterF(), "");
		} else if (v == rlMap) {
			((HotDealFragmentActivity) getActivity()).startFragment(new MapF(-1), "");
		}

	}
}
