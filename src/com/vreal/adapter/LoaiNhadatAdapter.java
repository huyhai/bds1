package com.vreal.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.vreal.libs.VrealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vreal.libs.NotifyVreal;
import com.vreal.model.VrealModel;

public class LoaiNhadatAdapter extends BaseExpandableListAdapter {
	private Activity _context;
	private ArrayList<VrealModel> _listDataHeader;
	private NotifyVreal notifi;

	public LoaiNhadatAdapter(Activity paramActivity, ArrayList<VrealModel> paramList, final NotifyVreal no) {
		this._context = paramActivity;
		this._listDataHeader = paramList;
		notifi = no;
	}

	public Object getChild(int groupPosition, int child) {
		return _listDataHeader.get(groupPosition).getListSub().get(child);
	}

	public long getChildId(int paramInt1, int paramInt2) {
		return paramInt2;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

		final VrealModel child = (VrealModel) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.menu_items, null);
		}

		TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
		txtListChild.setText(child.getProvinceName());
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != notifi) {
					notifi.onNotify(child);
				}

			}
		});
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return _listDataHeader.get(groupPosition).getListSub().size();

	}

	public Object getGroup(int paramInt) {
		return this._listDataHeader.get(paramInt);
	}

	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	public long getGroupId(int paramInt) {
		return paramInt;
	}

	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		final VrealModel header = (VrealModel) getGroup(groupPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group, null);
		}

		TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
		ImageView imgICDown = (ImageView) convertView.findViewById(R.id.imgICDown);
		LinearLayout llAll = (LinearLayout) convertView.findViewById(R.id.llAll);
		// Picasso.with(_context).load(header.getImage()).placeholder(R.drawable.img_thumb).error(R.drawable.noimage).into(imgIC);
		// llAll.setBackgroundResource(header.getBackground());
		// if (isExpanded&&header.getListSub().size()>0) {
		// imgICDown.setVisibility(View.VISIBLE);
		// } else {
		// imgICDown.setVisibility(View.GONE);
		// }
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (header.getListSub().size() == 0) {
					if (null != notifi) {
						notifi.onNotify(header);
					}
				}

			}
		});

		lblListHeader.setText(header.getProvinceName());
		VrealUtilities.setWidthHeight(imgICDown, 22, 70);

		return convertView;
	}

	// public View getGroupView(int paramInt, boolean paramBoolean,
	// View paramView, ViewGroup paramViewGroup) {
	// String str = (String) getGroup(paramInt);
	// if (paramView == null)
	// paramView = ((LayoutInflater) this._context
	// .getSystemService("layout_inflater")).inflate(2130903053,
	// null);
	// TextView localTextView = (TextView) paramView.findViewById(2131296376);
	// localTextView.setTypeface(null, 1);
	// localTextView.setText(str);
	// return paramView;
	// }

	public boolean hasStableIds() {
		return false;
	}

	public boolean isChildSelectable(int paramInt1, int paramInt2) {
		return true;
	}
}

/*
 * Location: F:\huy hai\e\backup all
 * user\AndroidNangCao\sorfAndroidNangCao\com.egood-1_dex2jar.jar Qualified
 * Name: com.demo.adapter.ExpandableListAdapter JD-Core Version: 0.6.1
 */