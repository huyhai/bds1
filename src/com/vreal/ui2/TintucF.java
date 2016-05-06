package com.vreal.ui2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.vrealapp.R;
import com.vreal.adapter.NewsAdapter;
import com.vreal.libs.NotifyDataListener;
import com.vrealvn.vrealapp.DataManager2;

public class TintucF extends Fragment {
	private ListView lvNews;
	NewsAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

		if (DataManager2.getInstance().getListTintuc().size() == 0) {
			getData();
		} else {
			setAdapter();
		}

	}

	private void setAdapter() {
		adapter = new NewsAdapter(getActivity(), DataManager2.getInstance().getListTintuc());
		lvNews.setAdapter(adapter);
	}

	private void getData() {
		DataManager2.getInstance().getTintuc(getActivity(), true, false, new NotifyDataListener() {

			@Override
			public void onNotify(String api, int id) {
				if (id == NOTIFY_OK) {
					setAdapter();
					// adapter.notifyDataSetChanged();
				} else {

				}

			}
		});
	}
}
