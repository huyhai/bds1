package com.vreal.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.squareup.picasso.Picasso;
import com.vreal.libs.NotifySomesDataListener;
import com.vreal.model.StateModel;

public class MenuAdapter extends BaseAdapter {
	private ArrayList<StateModel> listData;
	private Activity ac;
	ViewHolder holder;
	NotifySomesDataListener notify;

	static class ViewHolder {
		TextView tvName;
		ImageView imgPic;
	}

	public MenuAdapter(final Activity _ac, ArrayList<StateModel> _list, NotifySomesDataListener no) {
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
			convertView = inflater.inflate(R.layout.menu_item, null);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.imgPic = (ImageView) convertView.findViewById(R.id.imgPic);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final StateModel ca = listData.get(position);
		holder.tvName.setText(ca.getStateName());
		Picasso.with(ac).load(ca.getImage()).placeholder(R.drawable.img_thumb).error(R.drawable.noimage).into(holder.imgPic);
//		convertView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if (notify != null) {
//					notify.onReturnData(position);
//					notify.onReturnDataString(ca.getStateID());
//
//				}
//
//			}
//		});
		return convertView;
	}

	public void setItemList(ArrayList<StateModel> countryList) {
		this.listData = new ArrayList<StateModel>(countryList);
		notifyDataSetChanged();
	}

}
