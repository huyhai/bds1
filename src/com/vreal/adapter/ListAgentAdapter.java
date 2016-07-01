package com.vreal.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
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

public class ListAgentAdapter extends BaseAdapter {
	private ArrayList<StateModel> listData;
	private Activity ac;
	ViewHolder holder;
	NotifySomesDataListener notify;

	static class ViewHolder {
		TextView tvName;
		ImageView imgPic;
		TextView tvSDT;
		TextView tvTime;
		RelativeLayout rlName1;
	}

	public ListAgentAdapter(final Activity _ac, ArrayList<StateModel> _list,
			NotifySomesDataListener no) {
		this.listData = _list;
		this.ac = _ac;
		notify = no;

	}

	@Override
	public int getCount() {
		return 20;
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
			convertView = inflater.inflate(R.layout.list_agent_item, null);
			holder.tvSDT = (TextView) convertView.findViewById(R.id.tvSDT);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// Picasso.with(ac).load(ca.getImage()).placeholder(R.drawable.img_thumb)
		// .error(R.drawable.noimage).into(holder.imgPic);
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// if (notify != null) {
				// notify.onReturnData(position);
				// notify.onReturnDataString(ca.getStateID());
				//
				// }
				Intent smsIntent = new Intent(Intent.ACTION_VIEW);
				smsIntent.setType("vnd.android-dir/mms-sms");
				smsIntent
						.putExtra("address", holder.tvSDT.getText().toString());
				smsIntent.putExtra("sms_body", "Hi ..............");
				ac.startActivity(smsIntent);
			}
		});
		return convertView;
	}

	public void setItemList(ArrayList<StateModel> countryList) {
		this.listData = new ArrayList<StateModel>(countryList);
		notifyDataSetChanged();
	}

}
