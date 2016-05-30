package com.vreal.ui2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.android.vrealapp.R;
import com.vreal.libs.ConstantValue;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vreal.libs.NotifySomesDataListener;
import com.vreal.libs.SessionManager;
import com.vreal.model.VrealModel;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.HotDealFragmentActivity;
import com.vrealvn.vrealapp.HotdealApp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DuAnMoiSearch extends Fragment implements OnClickListener {
	private RelativeLayout rlLoai;
	private RelativeLayout rlTinh;
	private RelativeLayout rlQuan;

	private TextView tvLoai;
	private TextView tvTinh;
	private TextView tvQuan;
	private Button btnTimkiem;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.duanmoi_seach, container, false);
		initView(rootView);
		initData();
		return rootView;
	}

	private void initData() {
		if (null != DataManager2.getInstance().getListProvices() && DataManager2.getInstance().getListProvices().size() > 0) {
			notifyData.onNotify(ConstantValue.GET_CITY, NotifyDataListener.NOTIFY_OK);
		} else {
			getProvice();
		}

	}

	private void initView(View rootView) {
		rlLoai = (RelativeLayout) rootView.findViewById(R.id.rlLoai);
		rlTinh = (RelativeLayout) rootView.findViewById(R.id.rlTinh);
		rlQuan = (RelativeLayout) rootView.findViewById(R.id.rlQuan);
		tvLoai = (TextView) rootView.findViewById(R.id.tvLoai);
		tvTinh = (TextView) rootView.findViewById(R.id.tv);
		tvQuan = (TextView) rootView.findViewById(R.id.tva);
		btnTimkiem = (Button) rootView.findViewById(R.id.btnTimkiem);

		rlLoai.setOnClickListener(this);
		rlTinh.setOnClickListener(this);
		rlQuan.setOnClickListener(this);
		btnTimkiem.setOnClickListener(this);

	}

	private void getProvice() {
		DataManager2.getInstance().getProvice(getActivity(), false, false, notifyData);
	}

	private void getDistric() {
		DataManager2.getInstance().getDistrict(getActivity(), false, false, notifyData, "");
	}

	NotifyDataListener notifyData = new NotifyDataListener() {

		@Override
		public void onNotify(String api, int id) {
			if (api.equals(ConstantValue.GET_CITY)) {
				if (null == DataManager2.getInstance().getListDistrict() || DataManager2.getInstance().getListDistrict().size() == 0) {
					getDistric();
				}
			} else if (api.equals(ConstantValue.GET_DIS)) {

			} else if (api.equals(ConstantValue.SEARCH_PROJECT)) {
				if (id == NOTIFY_OK) {
					HotdealUtilities.startActivity(getActivity(), DuAnMoiF.class, "");
//					((HotDealFragmentActivity) getActivity()).startFragment(new DuAnMoiF(), "");
				} else {
					
				}
			}

		}
	};

	@Override
	public void onClick(View v) {
		if (v == rlLoai) {
			HotdealUtilities.startActivityForResult(getActivity(), LoaiNhaDat.class, 1, "-1");
		} else if (v == rlTinh) {
			HotdealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListProvices(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
				}

				@Override
				public void onReturnData(int id) {

					try {
						proviceID = DataManager2.getInstance().getListProvices().get(id).getProvinceID();
						proviceName = DataManager2.getInstance().getListProvices().get(id).getProvinceName();
						notifyData();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});

		} else if (v == rlQuan) {
			final ArrayList<VrealModel> listDisFilter = new ArrayList<>();
			for (VrealModel md : DataManager2.getInstance().getListDistrict()) {
				if (md.getProvinceID().equals(proviceID)) {
					listDisFilter.add(md);
				}
			}
			HotdealUtilities.showDialogCustomListView(getActivity(), listDisFilter, new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
				}

				@Override
				public void onReturnData(int id) {
					try {
						disID = listDisFilter.get(id).getDistrictID();
						disName = listDisFilter.get(id).getProvinceName();
						notifyData();
					} catch (Exception e) {
					}

				}
			});
		} else if (v == btnTimkiem) {
			DataManager2.getInstance().seach_project(getActivity(), true, false, notifyData, loaiID, proviceID, disID, 0, 100);
		}

	}

	private String defauldID = "";
	private String loaiID = defauldID;
	private String loaiName = "Chọn loại";
	private String proviceID = defauldID;
	private String proviceName = HotdealApp.getContext().getString(R.string.str_province);
	private String disID = defauldID;
	private String disName = HotdealApp.getContext().getString(R.string.str_district);
	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equalsIgnoreCase("ABC")) {
				HotdealUtilities.showALog(intent.getExtras().get(ConstantValue.IS_SUCCESS));
				try {
					String loai = (String) intent.getExtras().get(ConstantValue.IS_SUCCESS);
					StringTokenizer tokens = new StringTokenizer(loai, "-");
					loaiName = tokens.nextToken();
					loaiID = tokens.nextToken();
				} catch (Exception e) {
					// TODO: handle exception
				}
				notifyData();
			}
		}

	};

	private void notifyData() {
		tvTinh.setText(proviceName);
		tvQuan.setText(disName);
		tvLoai.setText(loaiName);

	}

	@Override
	public void onResume() {
		super.onResume();
		IntentFilter intentGetKeySend = new IntentFilter();
		intentGetKeySend.addAction("ABC");
		getActivity().registerReceiver(receiver, intentGetKeySend);
	}

	@Override
	public void onDestroy() {
		try {
			getActivity().unregisterReceiver(receiver);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		super.onDestroy();
	}

}
