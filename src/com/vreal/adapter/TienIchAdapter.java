package com.vreal.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.squareup.picasso.Picasso;
import com.vreal.libs.VrealUtilities;
import com.vreal.libs.NotifySomesDataListener;
import com.vreal.model.StateModel;
import com.vreal.model.VrealModel;
import com.vrealvn.vrealapp.DataManager2;

public class TienIchAdapter extends BaseAdapter {
	private ArrayList<VrealModel> listData;
	private Activity ac;
	ViewHolder holder;
	NotifySomesDataListener notify;
	private boolean isStatic;

	static class ViewHolder {
		CheckBox cbTi;
	}

	public TienIchAdapter(final Activity _ac, ArrayList<VrealModel> _list, NotifySomesDataListener no, boolean istatic) {
		this.listData = _list;
		this.ac = _ac;
		notify = no;
		this.isStatic=istatic;

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
			convertView = inflater.inflate(R.layout.tienich_item, null);
			holder.cbTi = (CheckBox) convertView.findViewById(R.id.cbTi);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final VrealModel ca = listData.get(position);
		holder.cbTi.setText(ca.getProvinceName());
		if(isStatic){
			holder.cbTi.setChecked(true);
			holder.cbTi.setClickable(false);
		}
//		convertView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if (notify != null) {
//					notify.onReturnData(position);
//					DataManager2.getInstance().setVrealModel(ca);
//				}
//
//			}
//		});
		return convertView;
	}

	public void setItemList(ArrayList<VrealModel> countryList) {
		this.listData = new ArrayList<VrealModel>(countryList);
		notifyDataSetChanged();
	}

}
