package com.vreal.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.vreal.libs.HotdealUtilities;
import com.vreal.model.DetailsModel;

public class DealHomeItemAdapter extends BaseAdapter {
	private ArrayList<DetailsModel> listData;
	private Activity ac;
	ViewHolder holder;

	static class ViewHolder {
		ImageView imgPic;
		TextView tvName;
		RelativeLayout rlAll;
	}

	public DealHomeItemAdapter(final Activity _ac, ArrayList<DetailsModel> _list) {
		this.listData = _list;
		this.ac = _ac;
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
			convertView = inflater.inflate(R.layout.deal_tong_hop_items, null);
			holder.imgPic = (ImageView) convertView.findViewById(R.id.imgPic);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.rlAll = (RelativeLayout) convertView.findViewById(R.id.rlAll);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		HotdealUtilities.setHeight(holder.imgPic, 5);
		HotdealUtilities.setWidth(holder.rlAll, 2.5);

//		final DetailsModel md = listData.get(position);
//		HotdealUtilities.loadImage(md.getImage(), holder.imgPic, ac);
//		HotDealFragmentActivity.imageLoader.displayImage(md.getImage(), holder.imgPic);

//		holder.tvName.setText(md.getName());
//		holder.tvName.setEllipsize(TextUtils.TruncateAt.END);


		return convertView;
	}

}
