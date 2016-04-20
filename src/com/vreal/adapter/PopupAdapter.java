package com.vreal.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import com.android.vrealapp.R;
import com.vreal.libs.SessionManager;
import com.vreal.model.VrealModel;

public class PopupAdapter extends BaseAdapter {
	private ArrayList<VrealModel> listData;
	private Activity ac;
	ViewHolder holder;

	static class ViewHolder {
		RadioButton radioButton1;
	}

	public PopupAdapter(final Context _ac, ArrayList<VrealModel> arrayList) {
		this.listData = arrayList;
		this.ac = (Activity) _ac;

	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listData.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(ac);
			convertView = inflater.inflate(R.layout.location_item, null);
			holder.radioButton1 = (RadioButton) convertView
					.findViewById(R.id.radioButton1);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final VrealModel ca = listData.get(position);
		holder.radioButton1.setText(ca.getProvinceName());
		holder.radioButton1.setTag(ca);

		holder.radioButton1.setChecked(ca.isChoosen());
		holder.radioButton1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = 0; i < listData.size(); i++) {
					listData.get(i).setChoosen(false);
				}
				RadioButton cb = (RadioButton) v;
				VrealModel item = (VrealModel) cb.getTag();
				item.setChoosen(true);
				notifyDataSetChanged();
//				ac.finish();
			}
		});
		return convertView;
	}

}
