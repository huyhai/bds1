package com.vreal.adapter;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.android.vrealapp.R;
import com.squareup.picasso.Picasso;
import com.vreal.libs.NotifyDataListener;
import com.vreal.libs.SessionManager;
import com.vreal.model.VrealModel;

public class ListImagesAdapter extends BaseAdapter {
	private ArrayList<Uri> listData;
	private Activity ac;
	ViewHolder holder;
	NotifyDataListener notify;

	static class ViewHolder {
		ImageView img;
		ImageView imgDel;
	}

	public ListImagesAdapter(final Context _ac, ArrayList<Uri> arrayList, NotifyDataListener no) {
		this.listData = arrayList;
		this.ac = (Activity) _ac;
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
			convertView = inflater.inflate(R.layout.list_image_item, null);
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.imgDel = (ImageView) convertView.findViewById(R.id.imgDel);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Uri uri = Uri.fromFile(new File(listData.get(position).getPath()));

		Picasso.with(ac).load(uri).resize(96, 96).centerCrop().into(holder.img);
		holder.imgDel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (notify != null) {
					notify.onNotify("", position);
				}

			}
		});
		return convertView;
	}

}
