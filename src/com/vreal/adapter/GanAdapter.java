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
import com.vreal.libs.VrealUtilities;
import com.vreal.libs.NotifySomesDataListener;
import com.vreal.model.StateModel;

public class GanAdapter extends BaseAdapter {
	private ArrayList<StateModel> listData;
	private Activity ac;
	ViewHolder holder;
	NotifySomesDataListener notify;

	static class ViewHolder {
		TextView tvName;
		ImageView imgPic;
		TextView tvDistance;
		TextView tvTime;
		RelativeLayout rlName1;
		ImageView imgAvatara;
	}

	public GanAdapter(final Activity _ac, ArrayList<StateModel> _list, NotifySomesDataListener no) {
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
			convertView = inflater.inflate(R.layout.gan_item, null);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.imgPic = (ImageView) convertView.findViewById(R.id.imgAvatar);
			holder.tvDistance = (TextView) convertView.findViewById(R.id.tvDistance);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
			holder.rlName1 = (RelativeLayout) convertView.findViewById(R.id.rlName1);
			holder.imgAvatara = (ImageView) convertView.findViewById(R.id.imgAvatara);
			VrealUtilities.setHeight(holder.rlName1, 13);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final StateModel ca = listData.get(position);
		holder.tvName.setText(ca.getStateName());
		holder.tvDistance.setText(ca.getSort());
		holder.tvTime.setText(ca.getStateID());
		if (ca.getStateID().equals("")) {
			holder.imgAvatara.setBackgroundResource(R.drawable.ic_muitendo);
		} else {
			holder.imgAvatara.setBackgroundResource(R.drawable.ic_oto);
		}

		Picasso.with(ac).load(ca.getImage()).placeholder(R.drawable.noimage).error(R.drawable.noimage).into(holder.imgPic);
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (notify != null) {
					notify.onReturnData(position);
					notify.onReturnDataString(ca.getStateID());

				}

			}
		});
		return convertView;
	}

	public void setItemList(ArrayList<StateModel> countryList) {
		this.listData = new ArrayList<StateModel>(countryList);
		notifyDataSetChanged();
	}

}
