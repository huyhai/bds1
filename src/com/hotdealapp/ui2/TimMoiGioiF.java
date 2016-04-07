package com.hotdealapp.ui2;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.vrealapp.R;
import com.hotdeal.adapter.TimMoiGioiAdapter;
import com.hotdeal.libs.ConstantValue;
import com.hotdeal.libs.HotdealUtilities;
import com.hotdeal.model.StateModel;
import com.hotdealapp.ui2.LoaiNhaDat;

public class TimMoiGioiF extends Fragment implements OnClickListener, OnItemClickListener {
	private ListView lvTimAgent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.timmoigioi, container, false);
		initView(rootView);
		return rootView;
	}

	private void initView(View rootView) {
		lvTimAgent = (ListView) rootView.findViewById(R.id.lvTimAgent);
//		rl1 = (RelativeLayout) rootView.findViewById(R.id.rl1);
//		rl2 = (RelativeLayout) rootView.findViewById(R.id.rl2);
//		rl3 = (RelativeLayout) rootView.findViewById(R.id.rl3);
//		rl4 = (RelativeLayout) rootView.findViewById(R.id.rl4);
//		rl5 = (RelativeLayout) rootView.findViewById(R.id.rl5);
//		rlLoai = (RelativeLayout) rootView.findViewById(R.id.rlLoai);


		// int h=10;
		// HotdealUtilities.setHeight(rl1, h);
		// HotdealUtilities.setHeight(rl2, h);
		// HotdealUtilities.setHeight(rl3, h);
		// HotdealUtilities.setHeight(rl4, h);
		// HotdealUtilities.setHeight(rl5, h);
		// HotdealUtilities.setHeight(rlBatki, h);
//		rl1.setOnClickListener(this);
//		rlLoai.setOnClickListener(this);
		ArrayList<StateModel> list=new ArrayList<>();
		StateModel md;
		
		md=new StateModel();
		md.setStateName("Môi giới tháng");
		list.add(md);
		
		md=new StateModel();
		md.setStateName("Môi giới chung cư");
		list.add(md);
		
		md=new StateModel();
		md.setStateName("Môi giới HDB");
		list.add(md);
		
		md=new StateModel();
		md.setStateName("Môi giới căn hộ cao cấp");
		list.add(md);
		
		md=new StateModel();
		md.setStateName("Môi giới nhà đất");
		list.add(md);
		
		md=new StateModel();
		md.setStateName("Môi giới khác");
		list.add(md);
		
		
		TimMoiGioiAdapter adapter=new TimMoiGioiAdapter(getActivity(), list, null);
		lvTimAgent.setAdapter(adapter);
		lvTimAgent.setOnItemClickListener(this);

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		HotdealUtilities.startActivity(getActivity(), ListAgent.class, "");
		
	}
}
