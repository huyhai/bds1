package com.vreal.ui2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.android.vrealapp.R;
import com.vreal.adapter.DuanAdapter;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifySomesDataListener;
import com.vreal.model.VrealModel;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.HotDealFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DuAnMoiF extends HotDealFragmentActivity implements OnClickListener {
	private ListView lvDuan;
	private RelativeLayout rlFilter;
	private RelativeLayout rlMap;
	private TextView tvKq;
	String idType;
	private TextView tvSX;
	private ArrayList<VrealModel> listData = new ArrayList<>();
	DuanAdapter adapter;
	private ImageView imgFilter;
	private ImageView imgLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.duanmoi);
		initView();
		setData();
	}

	private void setData() {
		listData = new ArrayList<>(DataManager2.getInstance().getListSearch());
		// listData.addAll(DataManager2.getInstance().getListSearch());
		// idType = HotdealUtilities.getDataFragment(this);
		idType = HotdealUtilities.getDataBundle(this);
		tvKq.setText(DataManager2.getInstance().getListSearch().size() + " kết quả");
		adapter = new DuanAdapter(this, listData, no);
		lvDuan.setAdapter(adapter);

	}

	@Override
	public void onResume() {
		super.onResume();
		// rlFilter.setVisibility(View.VISIBLE);
		// rlMap.setVisibility(View.VISIBLE);
		// Main.setTextTop("Dự án mới");
	}

	private void initView() {
		tvSX = (TextView) findViewById(R.id.tvSX);
		tvKq = (TextView) findViewById(R.id.tvKq);
		lvDuan = (ListView) findViewById(R.id.lvDuan);
		rlFilter = (RelativeLayout) this.findViewById(R.id.rlFilter);
		rlMap = (RelativeLayout) this.findViewById(R.id.rlMap);
		imgFilter = (ImageView) this.findViewById(R.id.imgFilter);
		imgLocation = (ImageView) this.findViewById(R.id.imgLocation);
		llTopBar = (LinearLayout) findViewById(R.id.llTopBar);
		HotdealUtilities.setHeight(llTopBar, 11.5);

		HotdealUtilities.setWidthHeight(imgLocation, 17, 24);
		HotdealUtilities.setWidthHeight(imgFilter, 15, 32);

		rlFilter.setOnClickListener(this);
		rlMap.setOnClickListener(this);
		tvSX.setOnClickListener(this);
	}

	NotifySomesDataListener no = new NotifySomesDataListener() {

		@Override
		public void onReturnDataString(String id) {
		}

		@Override
		public void onReturnData(int id) {
			if (idType.equals("")) {
				HotdealUtilities.startActivity(DuAnMoiF.this, DetailF.class, "");
				// ((HotDealFragmentActivity) this).startFragment(new
				// DetailF(), id + "");
			} else {
				HotdealUtilities.startActivity(DuAnMoiF.this, SearchF.class, id + "");
				// ((HotDealFragmentActivity) this).startFragment(new SearchF(),
				// id + "");
			}
			// HotdealUtilities.startActivity(this, DetailF.class, "");

		}
	};

	@Override
	public void onPause() {
		super.onPause();
		// rlFilter.setVisibility(View.GONE);
		// rlMap.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		if (v == rlFilter) {
			HotdealUtilities.startActivity(DuAnMoiF.this, FilterF.class, idType);
			// ((HotDealFragmentActivity) this).startFragment(new FilterF(),
			// idType);
		} else if (v == rlMap) {
			 HotdealUtilities.startActivity(DuAnMoiF.this, MapActivity.class, "-1");
//			startFragment(new MapF(-1), "");
		} else if (v == tvSX) {
			ArrayList<VrealModel> list = new ArrayList<>();
			list.add(new VrealModel("Tin mới nhất"));
			list.add(new VrealModel("Giá thấp nhất"));
			list.add(new VrealModel("Giá cao nhất"));
			list.add(new VrealModel("Diện tích lớn nhất"));
			list.add(new VrealModel("Diện tích nhỏ nhất"));
			HotdealUtilities.showDialogCustomListView(this, list, new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
				}

				@Override
				public void onReturnData(int id) {
					if (id == 0) {
						listData.clear();
						listData.addAll(DataManager2.getInstance().getListSearch());
					} else if (id == 1) {
						Collections.sort(listData, new Comparator<VrealModel>() {
							@Override
							public int compare(VrealModel s1, VrealModel s2) {
								if (s1.getPrice() < s2.getPrice()) {
									return -1;
								} else if (s1.getPrice() > s2.getPrice()) {
									return 1;
								} else {
									return 0;
								}

							}
						});
					} else if (id == 2) {
						Collections.sort(listData, new Comparator<VrealModel>() {
							@Override
							public int compare(VrealModel s1, VrealModel s2) {
								if (s2.getPrice() < s1.getPrice()) {
									return -1;
								} else if (s2.getPrice() > s1.getPrice()) {
									return 1;
								} else {
									return 0;
								}

							}
						});
					} else if (id == 3) {
						// Collections.sort(listData, new
						// Comparator<VrealModel>() {
						// @Override
						// public int compare(VrealModel s1, VrealModel s2) {
						// if (s2.getPrice() < s1.getPrice()) {
						// return -1;
						// } else if (s2.getPrice() > s1.getPrice()) {
						// return 1;
						// } else {
						// return 0;
						// }
						//
						// }
						// });
					} else if (id == 4) {
						// Collections.sort(listData, new
						// Comparator<VrealModel>() {
						// @Override
						// public int compare(VrealModel s1, VrealModel s2) {
						// if (s2.getPrice() < s1.getPrice()) {
						// return -1;
						// } else if (s2.getPrice() > s1.getPrice()) {
						// return 1;
						// } else {
						// return 0;
						// }
						//
						// }
						// });
					}

					adapter.notifyDataSetChanged();
				}
			});
		}

	}

}
