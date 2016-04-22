package com.vreal.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vreal.model.DetailsModel;
import com.vreal.model.VrealModel;

public class SoPhongAdapter extends BaseAdapter {
	private ArrayList<VrealModel> listData;
	private Activity ac;
	ViewHolder holder;
	NotifyDataListener notify;
	int numselect;

	static class ViewHolder {
		TextView tvName;
		RelativeLayout rlBatki;
	}

	public SoPhongAdapter(final Activity _ac, ArrayList<VrealModel> _list,
			NotifyDataListener no, int num) {
		this.listData = _list;
		this.ac = _ac;
		notify = no;
		numselect = num;
		setCheck(num);

	}

	private void setCheck(int num) {
		for (int i = 0; i < listData.size(); i++) {
			if (i == num) {
				listData.get(i).setChoosen(true);
			} else {
				listData.get(i).setChoosen(false);
			}
		}
		notifyDataSetChanged();
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
			convertView = inflater.inflate(R.layout.sophong_item, null);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.rlBatki = (RelativeLayout) convertView
					.findViewById(R.id.rlBatki);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final VrealModel md = listData.get(position);
		// if (numselect != 0) {
		//
		// } else {
		if (md.isChoosen()) {
			holder.tvName.setTextColor(Color.WHITE);
			holder.rlBatki
					.setBackgroundResource(R.drawable.ic_phongngu_choosen);
		} else {
			holder.tvName.setTextColor(Color.BLACK);
			holder.rlBatki.setBackgroundResource(R.drawable.ic_phongngu_none);
		}
		// }

		// HotdealUtilities.loadImage(md.getImage(), holder.imgPic, ac);
		// HotDealFragmentActivity.imageLoader.displayImage(md.getImage(),
		// holder.imgPic);

		holder.tvName.setText(md.getProvinceName());
		// holder.tvName.setEllipsize(TextUtils.TruncateAt.END);
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (notify != null) {
					notify.onNotify("", md.getValue1());
				}
				setCheck(position);
			}
		});

		return convertView;
	}

}
