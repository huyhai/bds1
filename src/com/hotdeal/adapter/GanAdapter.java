package com.hotdeal.adapter;

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
import com.hotdeal.libs.HotdealUtilities;
import com.hotdeal.libs.NotifySomesDataListener;
import com.hotdeal.model.StateModel;
import com.squareup.picasso.Picasso;

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
	}

	public GanAdapter(final Activity _ac, ArrayList<StateModel> _list,
			NotifySomesDataListener no) {
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
			holder.imgPic = (ImageView) convertView
					.findViewById(R.id.imgAvatar);
			holder.tvDistance = (TextView) convertView
					.findViewById(R.id.tvDistance);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
			holder.rlName1 = (RelativeLayout) convertView
					.findViewById(R.id.rlName1);
			HotdealUtilities.setHeight(holder.rlName1, 13);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final StateModel ca = listData.get(position);
		holder.tvName.setText(ca.getStateName());
		holder.tvDistance.setText(ca.getSort());
		if(ca.getStateID().equals("")){
			holder.tvTime.setText(ca.getStateID());
		}else{
			holder.tvTime.setText(ca.getStateID());	
		}
		
		Picasso.with(ac).load(ca.getImage()).placeholder(R.drawable.img_thumb)
				.error(R.drawable.noimage).into(holder.imgPic);
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
