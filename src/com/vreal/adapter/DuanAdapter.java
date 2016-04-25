package com.vreal.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.squareup.picasso.Picasso;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifySomesDataListener;
import com.vreal.model.StateModel;
import com.vreal.model.VrealModel;

public class DuanAdapter extends BaseAdapter {
	private ArrayList<VrealModel> listData;
	private Activity ac;
	ViewHolder holder;
	NotifySomesDataListener notify;

	static class ViewHolder {
		TextView tvName;
		ImageView imgPic;
		RelativeLayout a;
		TextView tvDiachi;
		TextView tvGia;
	}

	public DuanAdapter(final Activity _ac, ArrayList<VrealModel> _list, NotifySomesDataListener no) {
		this.listData = _list;
		this.ac = _ac;
		notify = no;

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
			convertView = inflater.inflate(R.layout.duan_item, null);
			holder.a = (RelativeLayout) convertView.findViewById(R.id.a);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvTen);
			holder.tvDiachi = (TextView) convertView.findViewById(R.id.tvDiachi);
//			holder.tvDiachi = (TextView) convertView.findViewById(R.id.tvDiachi);
			holder.tvGia= (TextView) convertView.findViewById(R.id.tvGia);
			HotdealUtilities.setHeight(holder.a, 3);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final VrealModel ca = listData.get(position);
		holder.tvName.setText(ca.getProvinceName());
		holder.tvGia.setText(ca.getPrice());
		holder.tvDiachi.setText(ca.getAddress());
		// Picasso.with(ac).load(ca.getImage()).placeholder(R.drawable.img_thumb).error(R.drawable.noimage).into(holder.imgPic);
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (notify != null) {
					notify.onReturnData(position);

				}

			}
		});
		return convertView;
	}

	public void setItemList(ArrayList<VrealModel> countryList) {
		this.listData = new ArrayList<VrealModel>(countryList);
		notifyDataSetChanged();
	}

}
