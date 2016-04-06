package com.hotdealapp.ui2;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.vrealapp.R;
import com.hotdeal.adapter.MenuAdapter;
import com.hotdeal.libs.HotdealUtilities;
import com.hotdeal.model.StateModel;

public class MenuSlide extends Fragment {
	private LinearLayout llTop;
	private ListView lvMenu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.menuslide, container, false);
		initView(rootView);
		return rootView;
	}

	private void initView(View rootView) {
		llTop = (LinearLayout) rootView.findViewById(R.id.llTop);
		lvMenu = (ListView) rootView.findViewById(R.id.lvMenu);

		HotdealUtilities.setHeight(llTop, 5);

		ArrayList<StateModel> listData = new ArrayList<>();
		StateModel md;

		md = new StateModel();
		md.setStateName("BĐS bán");
		md.setImage(R.drawable.ic_banthuelen);
		listData.add(md);
		
		md = new StateModel();
		md.setStateName("BĐS cho thuê");
		md.setImage(R.drawable.ic_banthue);
		listData.add(md);
		
		md = new StateModel();
		md.setStateName("Chung cư cao cấp");
		md.setImage(R.drawable.ic_caocap);
		listData.add(md);
		
		md = new StateModel();
		md.setStateName("Tin tức");
		md.setImage(R.drawable.ic_tintuc);
		listData.add(md);
		
		md = new StateModel();
		md.setStateName("Tìm môi giới");
		md.setImage(R.drawable.ic_moigioi);
		listData.add(md);
		
		md = new StateModel();
		md.setStateName("Feedback");
		md.setImage(R.drawable.ic_fb);
		listData.add(md);
		
		md = new StateModel();
		md.setStateName("Trợ giúp");
		md.setImage(R.drawable.ic_trogiup);
		listData.add(md);
		
		md = new StateModel();
		md.setStateName("Đăng xuất");
		md.setImage(R.drawable.ic_dangxuat);
		listData.add(md);
		
		MenuAdapter adapter=new MenuAdapter(getActivity(), listData, null);
		lvMenu.setAdapter(adapter);
	}

}
