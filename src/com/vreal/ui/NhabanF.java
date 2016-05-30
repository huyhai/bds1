package com.vreal.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.DTDHandler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.vreal.adapter.DealHomeItemAdapter;
import com.vreal.adapter.SoPhongAdapter;
import com.vreal.libs.ConstantValue;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vreal.libs.NotifySomesDataListener;
import com.vreal.libs.SessionManager;
import com.vreal.libs.TwoWayView;
import com.vreal.model.VrealModel;
import com.vreal.ui2.DuAnMoiF;
import com.vreal.ui2.LoaiNhaDat;
import com.vreal.ui2.VrealFragment;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.HotDealFragmentActivity;
import com.vrealvn.vrealapp.HotdealApp;

public class NhabanF extends VrealFragment implements OnClickListener, OnCheckedChangeListener {
	private RelativeLayout rlDuan;
	private RelativeLayout rlRefresh;
	private LinearLayout pdBar1;
	private TextView tvMorong;
	private RelativeLayout rlBatki;
	private RelativeLayout rl1;
	private RelativeLayout rl2;
	private RelativeLayout rl3;
	private RelativeLayout rl4;
	private RelativeLayout rl5;
	private TextView tvTP;
	private TextView tvQuan;
	private RelativeLayout rlLoai;

	private RelativeLayout rlTinh;

	private RelativeLayout rlQuan;
	private RelativeLayout rlDT;
	private RelativeLayout rlMG;
	private RelativeLayout rlHuong;
	private Switch swLuu;

	private TextView tvWard;
	private TextView tvKV;
	private TextView tvDuong;
	private RelativeLayout rlHuyen;
	private RelativeLayout rlKhuVuc;
	private RelativeLayout rlDuong;
	private TextView tvHinhthuc;
	private RelativeLayout rlHT;
	private TextView tvLoai;
	private TextView tvDientich;
	private TextView tvMucgia;
	private TextView tvHuongnha;
	// private TwoWayView lvSP;
	private Button btnTimkiem;
	private TextView tvDuanValue;
	// private TextView tvWard;
	private String idType;
	private EditText edKhuvuc;

	// DATA
	private String defauldID = "";
	private String proviceID = defauldID;
	private String proviceName = HotdealApp.getContext().getString(R.string.str_province);
	private String disID = defauldID;
	private String disName = HotdealApp.getContext().getString(R.string.str_district);
	private String areaName = "Diện tích";
	private String priceName = "Mức giá";
	private String wayID = defauldID;
	private String wayName = "Hướng nhà";
	private String KVID = defauldID;
	private String KVName = HotdealApp.getContext().getString(R.string.str_khuvuc);
	private String streetID = defauldID;
	private String streetName = HotdealApp.getContext().getString(R.string.str_street);
	private String wardID = defauldID;
	private String wardName = HotdealApp.getContext().getString(R.string.str_ward);
	private String loaiID = defauldID;
	private String loaiName = "Chọn loại";
	private String dientichFrom = defauldID;
	private String dientichTo = defauldID;
	private String dientichName = "Chọn diện tích";
	private String giaFrom = defauldID;
	private String giaTo = defauldID;
	private String giaName = "Chọn giá";
	private String huongID = defauldID;
	private String huongName = "Chọn hướng nhà";
	private String soPhong = "";
	private String duanID = defauldID;
	private String duanName = "Chọn dự án";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.nhaban, container, false);
		initView(rootView);
		initSaveData();
		return rootView;
	}

	private void setDefault() {
		defauldID = "";
		proviceID = defauldID;
		proviceName = HotdealApp.getContext().getString(R.string.str_province);
		disID = defauldID;
		disName = HotdealApp.getContext().getString(R.string.str_district);
		areaName = "Diện tích";
		priceName = "Mức giá";
		wayID = defauldID;
		wayName = "Hướng nhà";
		KVID = defauldID;
		KVName = HotdealApp.getContext().getString(R.string.str_khuvuc);
		streetID = defauldID;
		streetName = HotdealApp.getContext().getString(R.string.str_street);
		wardID = defauldID;
		wardName = HotdealApp.getContext().getString(R.string.str_ward);
		loaiID = defauldID;
		loaiName = "Chọn loại";
		dientichFrom = defauldID;
		dientichTo = defauldID;
		dientichName = "Chọn diện tích";
		giaFrom = defauldID;
		giaTo = defauldID;
		giaName = "Chọn giá";
		huongID = defauldID;
		huongName = "Chọn hướng nhà";
		soPhong = "0";
		duanID = defauldID;
		duanName = "Chọn dự án";
	}

	private void initSaveData() {
		if (sm.isSaveSetting()) {
			HashMap<String, String> edito = sm.getSettings();
			proviceID = edito.get(SessionManager.KEY_PROVICEID);
			proviceName = edito.get(SessionManager.KEY_PROVICE);
			disID = edito.get(SessionManager.KEY_DISID);
			disName = edito.get(SessionManager.KEY_DIS);
			areaName = edito.get(SessionManager.KEY_AREA);
			priceName = edito.get(SessionManager.KEY_PRICE);
			wayID = edito.get(SessionManager.KEY_WAYID);
			wayName = edito.get(SessionManager.KEY_WAY);
			KVID = edito.get(SessionManager.KEY_KHUVUCID);
			KVName = edito.get(SessionManager.KEY_KHUVUC);
			streetID = edito.get(SessionManager.KEY_DUONGID);
			streetName = edito.get(SessionManager.KEY_DUONG);
			wardID = edito.get(SessionManager.KEY_WARDID);
			wardName = edito.get(SessionManager.KEY_WARD);
			loaiID = edito.get(SessionManager.KEY_TYPEID);
			loaiName = edito.get(SessionManager.KEY_TYPE);
			dientichFrom = edito.get(SessionManager.KEY_DTFROM);
			dientichTo = edito.get(SessionManager.KEY_DTTO);
			dientichName = edito.get(SessionManager.KEY_DTNAME);
			giaFrom = edito.get(SessionManager.KEY_GIAFROM);
			giaTo = edito.get(SessionManager.KEY_GIATO);
			giaName = edito.get(SessionManager.KEY_GIANAME);
			huongID = edito.get(SessionManager.HUONGID);
			huongName = edito.get(SessionManager.HUONGNAME);
			soPhong = edito.get(SessionManager.SOPHONG);
			duanID = edito.get(SessionManager.DUANID);
			duanName = edito.get(SessionManager.DUANNAME);
		}
		notifyData();

	}

	@Override
	public void onDestroy() {
		rlRefresh.setVisibility(View.GONE);
		try {
			getActivity().unregisterReceiver(receiver);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if (sm.isSaveSetting()) {
			HashMap<String, String> editor = new HashMap<>();
			editor.put(SessionManager.KEY_PROVICE, proviceName);
			editor.put(SessionManager.KEY_PROVICEID, proviceID);
			editor.put(SessionManager.KEY_DIS, disName);
			editor.put(SessionManager.KEY_DISID, disID);
			editor.put(SessionManager.KEY_AREA, areaName);
			editor.put(SessionManager.KEY_AREAID, "");
			editor.put(SessionManager.KEY_PRICE, priceName);
			editor.put(SessionManager.KEY_PRICEID, "");
			editor.put(SessionManager.KEY_WAY, wayName);
			editor.put(SessionManager.KEY_WAYID, wayID);
			editor.put(SessionManager.KEY_KHUVUC, KVName);
			editor.put(SessionManager.KEY_KHUVUCID, KVID);
			editor.put(SessionManager.KEY_DUONG, streetName);
			editor.put(SessionManager.KEY_DUONGID, streetID);
			editor.put(SessionManager.KEY_WARDID, wardID);
			editor.put(SessionManager.KEY_WARD, wardName);
			editor.put(SessionManager.KEY_TYPEID, loaiID);
			editor.put(SessionManager.KEY_TYPE, loaiName);
			editor.put(SessionManager.KEY_DTFROM, dientichFrom);
			editor.put(SessionManager.KEY_DTTO, dientichTo);
			editor.put(SessionManager.KEY_DTNAME, dientichName);
			editor.put(SessionManager.KEY_GIAFROM, giaFrom);
			editor.put(SessionManager.KEY_GIATO, giaTo);
			editor.put(SessionManager.KEY_GIANAME, giaName);
			editor.put(SessionManager.HUONGID, huongID);
			editor.put(SessionManager.HUONGNAME, huongName);
			editor.put(SessionManager.SOPHONG, soPhong);
			editor.put(SessionManager.DUANID, duanID);
			editor.put(SessionManager.DUANNAME, duanName);
			sm.setSettings(editor);
		}
		super.onDestroy();
	}

	private void initView(View rootView) {
		idType = HotdealUtilities.getDataFragment(this);
		rlDuan = (RelativeLayout) rootView.findViewById(R.id.rlDuan);
		rlRefresh = (RelativeLayout) getActivity().findViewById(R.id.rlRefresh);
		tvMorong = (TextView) rootView.findViewById(R.id.tvMorong);

		rl1 = (RelativeLayout) rootView.findViewById(R.id.rl1);
		rl2 = (RelativeLayout) rootView.findViewById(R.id.rl2);
		rl3 = (RelativeLayout) rootView.findViewById(R.id.rl3);
		rl4 = (RelativeLayout) rootView.findViewById(R.id.rl4);
		rl5 = (RelativeLayout) rootView.findViewById(R.id.rl5);
		rlBatki = (RelativeLayout) rootView.findViewById(R.id.rlBatki);

		rlLoai = (RelativeLayout) rootView.findViewById(R.id.rlLoai);
		pdBar1 = (LinearLayout) rootView.findViewById(R.id.pdBar1);

		rlTinh = (RelativeLayout) rootView.findViewById(R.id.rlTinh);
		rlQuan = (RelativeLayout) rootView.findViewById(R.id.rlQuan);
		rlDT = (RelativeLayout) rootView.findViewById(R.id.rlDT);
		rlMG = (RelativeLayout) rootView.findViewById(R.id.rlMG);
		rlHuong = (RelativeLayout) rootView.findViewById(R.id.rlHuong);
		tvTP = (TextView) rootView.findViewById(R.id.tv);
		tvQuan = (TextView) rootView.findViewById(R.id.tva);
		tvWard = (TextView) rootView.findViewById(R.id.tvWard);

		tvKV = (TextView) rootView.findViewById(R.id.tvKV);
		tvDuong = (TextView) rootView.findViewById(R.id.tvDuong);
		rlHuyen = (RelativeLayout) rootView.findViewById(R.id.rlHuyen);
		rlKhuVuc = (RelativeLayout) rootView.findViewById(R.id.rlKhuVuc);
		rlDuong = (RelativeLayout) rootView.findViewById(R.id.rlDuong);
		tvHinhthuc = (TextView) rootView.findViewById(R.id.tvHinhthuc);
		rlHT = (RelativeLayout) rootView.findViewById(R.id.rlHT);
		// lvSP = (TwoWayView) rootView.findViewById(R.id.lvSP);
		btnTimkiem = (Button) rootView.findViewById(R.id.btnTimkiem);
		edKhuvuc = (EditText) rootView.findViewById(R.id.edKhuvuc);
		tvLoai = (TextView) rootView.findViewById(R.id.tvLoai);
		tvDientich = (TextView) rootView.findViewById(R.id.tvDientich);
		tvMucgia = (TextView) rootView.findViewById(R.id.tvMucgia);
		tvHuongnha = (TextView) rootView.findViewById(R.id.tvHuongnha);
		tvDuanValue = (TextView) rootView.findViewById(R.id.tvDuanValue);
		// tvHinhthuc = (TextView) rootView.findViewById(R.id.tvHinhthuc);
		// tvHinhthuc = (TextView) rootView.findViewById(R.id.tvHinhthuc);
		// tvHinhthuc = (TextView) rootView.findViewById(R.id.tvHinhthuc);

		swLuu = (Switch) rootView.findViewById(R.id.swLuu);

		// int h=10;
		// HotdealUtilities.setHeight(rl1, h);
		// HotdealUtilities.setHeight(rl2, h);
		// HotdealUtilities.setHeight(rl3, h);
		// HotdealUtilities.setHeight(rl4, h);
		// HotdealUtilities.setHeight(rl5, h);
		// HotdealUtilities.setHeight(rlBatki, h);
		rlTinh.setOnClickListener(this);
		rlLoai.setOnClickListener(this);
		rlQuan.setOnClickListener(this);
		rlDT.setOnClickListener(this);
		rlMG.setOnClickListener(this);
		rlHuong.setOnClickListener(this);
		pdBar1.setOnClickListener(this);
		tvMorong.setOnClickListener(this);
		rl1.setOnClickListener(this);
		rl2.setOnClickListener(this);
		rl3.setOnClickListener(this);
		rl4.setOnClickListener(this);
		rl5.setOnClickListener(this);
		rlBatki.setOnClickListener(this);
		rlKhuVuc.setOnClickListener(this);
		swLuu.setOnCheckedChangeListener(this);
		rlHuyen.setOnClickListener(this);
		rlDuong.setOnClickListener(this);
		rlHT.setOnClickListener(this);
		btnTimkiem.setOnClickListener(this);
		rlDuan.setOnClickListener(this);
		rlRefresh.setOnClickListener(this);
		// DataManager2.getInstance().showProgress(getActivity());
		if (DataManager2.getInstance().getListProvices().size() == 0) {
			if (sm.getProviceJson().equals("")) {
				getProvice();
			} else {
				JSONArray job = null;
				try {
					job = new JSONArray(sm.getProviceJson());
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				DataManager2.getInstance().handleProvice(job, notifyData);
			}
		}
		if (HotdealApp.isExpand) {
			HotdealApp.isExpand = true;
			pdBar1.setVisibility(View.VISIBLE);
		}
	}

	private void notifyData() {
		tvTP.setText(proviceName);
		tvQuan.setText(disName);
		tvWard.setText(wardName);
		tvKV.setText(KVName);
		tvDuong.setText(streetName);
		swLuu.setChecked(sm.isSaveSetting());
		tvLoai.setText(loaiName);
		tvDientich.setText(dientichName);
		tvMucgia.setText(giaName);
		tvHuongnha.setText(huongName);
		tvDuanValue.setText(duanName);
		String str = "";
		if (!streetID.equals("")) {
			str += streetName + ",";
		}
		if (!wardID.equals("")) {
			str += wardName + ",";
		}
		if (!disID.equals("")) {
			str += disName + ",";
		}
		if (!proviceID.equals("")) {
			str += proviceName + ",";
		}
		if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
			str = str.substring(0, str.length() - 1);
		}
		edKhuvuc.setText(str);
	}

	NotifyDataListener notifyData = new NotifyDataListener() {

		@Override
		public void onNotify(String api, int id) {
			if (api.equals(ConstantValue.GET_CITY)) {
				if (NotifyDataListener.NOTIFY_OK == id) {
					if (sm.getDisJson().equals("")) {
						getDistric();
					} else {
						JSONArray job = null;
						try {
							job = new JSONArray(sm.getDisJson());
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						DataManager2.getInstance().handleDistrict(job, notifyData);
					}
				} else {

				}
			} else if (api.equals(ConstantValue.GET_DIS)) {
				if (NotifyDataListener.NOTIFY_OK == id) {
					if (sm.getWardJson().equals("")) {
						getWard();
					} else {
						JSONArray job = null;
						try {
							job = new JSONArray(sm.getWardJson());
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						DataManager2.getInstance().handleWard(job, notifyData);
					}
				} else {

				}
			} else if (api.equals(ConstantValue.GET_WARD)) {
				if (NotifyDataListener.NOTIFY_OK == id) {
					if (sm.getStreetJson().equals("")) {
						getStreet();
					} else {
						JSONArray job = null;
						try {
							job = new JSONArray(sm.getStreetJson());
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						DataManager2.getInstance().handleStreet(job, notifyData);
					}
				} else {

				}
			} else if (api.equals(ConstantValue.GET_STREET)) {
				if (NotifyDataListener.NOTIFY_OK == id) {
					if (sm.getKhuVucJson().equals("")) {
						getKhuvuc();
					} else {
						JSONArray job = null;
						try {
							job = new JSONArray(sm.getKhuVucJson());
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						DataManager2.getInstance().handleKV(job, notifyData);
					}
				} else {

				}

			} else if (api.equals(ConstantValue.GET_KHUVUC)) {
				getHuong();

			} else if (api.equals(ConstantValue.GET_HUONG)) {
				getDuan();
			} else if (api.equals(ConstantValue.GET_DUAN)) {
				getGia();
			} else if (api.equals(ConstantValue.GET_GIA)) {
				getDienTich();
			} else if (api.equals(ConstantValue.GET_DIENTICH)) {
				setDataDienTichAndGia();

				DataManager2.getInstance().stopProgress();
			}
			// else if (api.equals(ConstantValue.GET_DIS)) {
			//
			// }

		}

	};

	// SoPhongAdapter adapterList;

	private void setDataDienTichAndGia() {
		// DataManager2.getInstance().getListGia().addAll(HotdealUtilities.setDataGia());
		// DataManager2.getInstance().getListDientich().addAll(HotdealUtilities.setDataDienTich());
		// adapterList = new SoPhongAdapter(getActivity(),
		// HotdealUtilities.setDataSoPhong(), notifySP,
		// Integer.parseInt(soPhong));
		// lvSP.setAdapter(adapterList);
		// lvSP.setHorizontalScrollBarEnabled(false);
		// lvSP.setItemMargin(10);
	}

	// NotifyDataListener notifySP = new NotifyDataListener() {
	//
	// @Override
	// public void onNotify(String api, final int id) {
	// soPhong = id + "";
	// lvSP.post(new Runnable() {
	//
	// @Override
	// public void run() {
	// lvSP.setSelection(id);
	//
	// }
	// });
	// // adapterList.notifyDataSetChanged();
	// }
	// };

	private void getDienTich() {
		DataManager2.getInstance().getDienTich(getActivity(), false, false, notifyData);
	}

	private void getGia() {
		DataManager2.getInstance().getGia(getActivity(), false, false, notifyData);
	}

	private void getProvice() {
		DataManager2.getInstance().getProvice(getActivity(), false, false, notifyData);
	}

	private void getDistric() {
		DataManager2.getInstance().getDistrict(getActivity(), false, false, notifyData, "");
	}

	private void getWard() {
		DataManager2.getInstance().getWard(getActivity(), false, false, notifyData);
	}

	private void getStreet() {
		DataManager2.getInstance().getStreet(getActivity(), false, false, notifyData);
	}

	private void getKhuvuc() {
		DataManager2.getInstance().getKhuvuc(getActivity(), false, false, notifyData);
	}

	private void getHuong() {
		DataManager2.getInstance().getHuong(getActivity(), false, false, notifyData);
	}

	private void getDuan() {
		DataManager2.getInstance().getDuan(getActivity(), false, false, notifyData);
	}

	private void search() {
		DataManager2.getInstance().seach(getActivity(), true, false, new NotifyDataListener() {

			@Override
			public void onNotify(String api, int id) {
				if (NotifyDataListener.NOTIFY_OK == id) {
					HotdealUtilities.startActivity(getActivity(), DuAnMoiF.class, idType);
					// ((HotDealFragmentActivity)
					// getActivity()).startFragment(new DuAnMoiF(), idType);
				} else {

				}

			}
		}, idType, loaiID, proviceID, disID, wardID, streetID, huongID, duanID, dientichFrom, soPhong, "", giaFrom, 0, 10);
	}

	// private void getTypeProperty() {
	// DataManager2.getInstance().getTypeProperty(getActivity(), false, false,
	// notifyData);
	// }

	private void setSP(int batki, int l1, int l2, int l3, int l4, int l5) {
		rlBatki.setBackgroundResource(batki);
		rl1.setBackgroundResource(l1);
		rl2.setBackgroundResource(l2);
		rl3.setBackgroundResource(l3);
		rl4.setBackgroundResource(l4);
		rl5.setBackgroundResource(l5);
	}

	// private ArrayList<VrealModel> getList(ArrayList<VrealModel> list, String
	// arg){
	//
	// return aaa;
	//
	// }
	//
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

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		rlRefresh.setVisibility(View.VISIBLE);
		IntentFilter intentGetKeySend = new IntentFilter();
		intentGetKeySend.addAction("ABC");
		getActivity().registerReceiver(receiver, intentGetKeySend);
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		rlRefresh.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		// HotdealUtilities.setClickAnim(v);
		if (v == rlLoai) {
			HotdealUtilities.startActivityForResult(getActivity(), LoaiNhaDat.class, 1, idType);

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
						// disID = "-1";
						// disName =
						// HotdealApp.getContext().getString(R.string.str_district);
						// wardID = "-1";
						// wardName =
						// HotdealApp.getContext().getString(R.string.str_ward);
						// KVID = "-1";
						// KVName =
						// HotdealApp.getContext().getString(R.string.str_khuvuc);
						// streetID = "-1";
						// streetName =
						// HotdealApp.getContext().getString(R.string.str_street);
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
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						disID = listDisFilter.get(id).getDistrictID();
						disName = listDisFilter.get(id).getProvinceName();
						// wardID = "-1";
						// wardName =
						// HotdealApp.getContext().getString(R.string.str_ward);
						// KVID = "-1";
						// KVName =
						// HotdealApp.getContext().getString(R.string.str_khuvuc);
						// streetID = "-1";
						// streetName =
						// HotdealApp.getContext().getString(R.string.str_street);
						notifyData();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});

		} else if (v == rlDT) {
			HotdealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListDientich(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						dientichFrom = DataManager2.getInstance().getListDientich().get(id).getId() + "";
						dientichTo = DataManager2.getInstance().getListDientich().get(id).getValue2() + "";
						dientichName = DataManager2.getInstance().getListDientich().get(id).getProvinceName();
						notifyData();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});

		} else if (v == rlMG) {
			HotdealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListGia(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						giaFrom = DataManager2.getInstance().getListGia().get(id).getId() + "";
						giaTo = DataManager2.getInstance().getListGia().get(id).getValue2() + "";
						giaName = DataManager2.getInstance().getListGia().get(id).getProvinceName();
						notifyData();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});

		} else if (v == rlHuong) {
			HotdealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListHuong(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
				}

				@Override
				public void onReturnData(int id) {
					try {
						huongID = DataManager2.getInstance().getListHuong().get(id).getId() + "";
						huongName = DataManager2.getInstance().getListHuong().get(id).getProvinceName();
						notifyData();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});

		} else if (v == rlHuyen) {
			final ArrayList<VrealModel> listDisFilter = new ArrayList<>();
			for (VrealModel md : DataManager2.getInstance().getListWard()) {
				if (md.getDistrictID().equals(disID)) {
					listDisFilter.add(md);
				}
			}
			HotdealUtilities.showDialogCustomListView(getActivity(), listDisFilter, new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						wardID = listDisFilter.get(id).getWardID();
						wardName = listDisFilter.get(id).getProvinceName();
						notifyData();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});

		} else if (v == rlKhuVuc) {
			final ArrayList<VrealModel> listDisFilter = new ArrayList<>();
			for (VrealModel md : DataManager2.getInstance().getListKhuvuc()) {
				if (null != md && md.getProvinceID().equals(proviceID)) {
					if (md.getDistrictID().equals(disID)) {
						listDisFilter.add(md);
					}
				}

			}
			HotdealUtilities.showDialogCustomListView(getActivity(), listDisFilter, new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						KVID = listDisFilter.get(id).getId();
						KVName = listDisFilter.get(id).getProvinceName();
						notifyData();
					} catch (Exception e) {
					}

				}
			});

		} else if (v == rlDuong) {
			HotdealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListStreet(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {

				}

				@Override
				public void onReturnData(int id) {
					try {
						streetID = DataManager2.getInstance().getListStreet().get(id).getStreetID();
						streetName = DataManager2.getInstance().getListStreet().get(id).getProvinceName();
						notifyData();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});

		} else if (v == rlHT) {

		} else if (v == btnTimkiem) {
			search();

		} else if (v == rlDuan) {
			HotdealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListDuAn(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						duanID = DataManager2.getInstance().getListDuAn().get(id).getId();
						duanName = DataManager2.getInstance().getListDuAn().get(id).getProvinceName();
						notifyData();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});

		} else if (v == rlRefresh) {
			setDefault();
			notifyData();
			// HotdealUtilities.showDialogCustomListView(getActivity());

		} else if (v == tvMorong) {
			if (pdBar1.isShown()) {
				HotdealApp.isExpand = false;
				pdBar1.setVisibility(View.GONE);
			} else {
				HotdealApp.isExpand = true;
				pdBar1.setVisibility(View.VISIBLE);
			}

		} else if (v == rlBatki) {
			soPhong="";
			setSP(R.drawable.ic_phongngu_choosen, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none);
		} else if (v == rl1) {
			soPhong="1";
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_choosen, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none);
		} else if (v == rl2) {
			soPhong="2";
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_choosen, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none);
		} else if (v == rl3) {
			soPhong="3";
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_choosen, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none);
		} else if (v == rl4) {
			soPhong="4";
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_choosen, R.drawable.ic_phongngu_none);
		} else if (v == rl5) {
			soPhong="5";
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_choosen);
		}

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			sm.setSaveSetting(true);
		} else {
			sm.setSaveSetting(false);
		}

	}
}
