package com.vreal.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.vreal.libs.VrealUtilities;
import com.vreal.libs.TwoWayView;
import com.vreal.model.DealHomeModel;
import com.vrealvn.vrealapp.VrealApp;

public class NewsAdapter extends BaseAdapter {
	private ArrayList<DealHomeModel> listData;
	private Activity ac;
	ViewHolder holder;

	static class ViewHolder {
		TextView tvDeal;
		TwoWayView lvDealhot;
	}

	public NewsAdapter(final Activity _ac, ArrayList<DealHomeModel> _list) {
		this.listData = _list;
		this.ac = _ac;

	}

	@Override
	public int getCount() {
		// return 10;
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
			convertView = inflater.inflate(R.layout.tintuc_item, null);
			holder.tvDeal = (TextView) convertView.findViewById(R.id.tvDeal);
			holder.lvDealhot = (TwoWayView) convertView.findViewById(R.id.lvDealhot);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final DealHomeModel ca = listData.get(position);
		holder.tvDeal.setText(ca.getName());

		final DealHomeItemAdapter adapterList = new DealHomeItemAdapter(ac, ca.getListTintuc());
		holder.lvDealhot.setAdapter(adapterList);
		holder.lvDealhot.setHorizontalScrollBarEnabled(false);
		holder.lvDealhot.setItemMargin(10);

		return convertView;
	}

}
