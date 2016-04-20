package com.vreal.ui2;

import java.util.ArrayList;

import com.android.vrealapp.R;
import com.vreal.adapter.NewsAdapter;
import com.vreal.libs.HotdealUtilities;
import com.vreal.model.DealHomeModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TintucF extends Fragment {
	private ListView lvNews;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.tintuc, container, false);
		initView(rootView);
		return rootView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Main.setTextTop("Tin tức bất động sản");
	}
	private void initView(View rootView) {
		lvNews = (ListView) rootView.findViewById(R.id.lvNews);

		ArrayList<DealHomeModel> _list = new ArrayList<>();
		DealHomeModel md;
		md = new DealHomeModel();
		md.setName("Tin mới nhất");
		_list.add(md);
		md = new DealHomeModel();
		md.setName("Chợ bất động sản");
		_list.add(md);
		md = new DealHomeModel();
		md.setName("Chợ bất động sản");
		_list.add(md);
		NewsAdapter adapter = new NewsAdapter(getActivity(), _list);
		lvNews.setAdapter(adapter);

	}
}
