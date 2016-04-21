package com.vreal.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.vreal.libs.ConstantValue;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vreal.libs.NotifySomesDataListener;
import com.vreal.libs.SessionManager;
import com.vreal.model.VrealModel;
import com.vreal.ui2.LoaiNhaDat;
import com.vreal.ui2.VrealFragment;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.HotdealApp;

public class NhabanF extends VrealFragment implements OnClickListener, OnCheckedChangeListener {
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
	// private TextView tvWard;
	// private TextView tvWard;
	// private TextView tvWard;
	// private TextView tvWard;
	// private TextView tvWard;
	// private TextView tvWard;
	// private TextView tvWard;
	// private TextView tvWard;

	// DATA
	private String proviceID = "-1";
	private String proviceName = HotdealApp.getContext().getString(R.string.str_province);
	private String disID = "-1";
	private String disName = HotdealApp.getContext().getString(R.string.str_district);
	// private String areaID = "-1";
	private String areaName = "Diện tích";
	// private String priceID = "-1";
	private String priceName = "Mức giá";
	private String wayID = "-1";
	private String wayName = "Hướng nhà";
	private String KVID = "-1";
	private String KVName = HotdealApp.getContext().getString(R.string.str_khuvuc);
	private String streetID = "-1";
	private String streetName = HotdealApp.getContext().getString(R.string.str_street);
	private String wardID = "-1";
	private String wardName = HotdealApp.getContext().getString(R.string.str_ward);
	private String tyoeID = "-1";
	private String typeName = "Chọn hình thức";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.nhaban, container, false);
		initView(rootView);
		initSaveData();
		return rootView;
	}

	private void initSaveData() {
		if (sm.isSaveSetting()) {
			HashMap<String, String> edito = sm.getSettings();
			proviceID = edito.get(SessionManager.KEY_PROVICEID);
			proviceName = edito.get(SessionManager.KEY_PROVICE);
			disID = edito.get(SessionManager.KEY_DISID);
			disName = edito.get(SessionManager.KEY_DIS);
			// areaID = "-1";
			areaName = edito.get(SessionManager.KEY_AREA);
			// priceID = "-1";
			priceName = edito.get(SessionManager.KEY_PRICE);
			wayID = edito.get(SessionManager.KEY_WAYID);
			wayName = edito.get(SessionManager.KEY_WAY);
			KVID = edito.get(SessionManager.KEY_KHUVUCID);
			KVName = edito.get(SessionManager.KEY_KHUVUC);
			streetID = edito.get(SessionManager.KEY_DUONGID);
			streetName = edito.get(SessionManager.KEY_DUONG);
			wardID = edito.get(SessionManager.KEY_WARDID);
			wardName = edito.get(SessionManager.KEY_WARD);
			tyoeID = edito.get(SessionManager.KEY_TYPEID);
			typeName = edito.get(SessionManager.KEY_TYPE);
		}
		notifyData();

	}

	@Override
	public void onDestroy() {
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
			editor.put(SessionManager.KEY_TYPEID, tyoeID);
			editor.put(SessionManager.KEY_TYPE, typeName);
			sm.setSettings(editor);
		}
		super.onDestroy();
	}

	private void initView(View rootView) {
		rlBatki = (RelativeLayout) rootView.findViewById(R.id.rlBatki);
		rl1 = (RelativeLayout) rootView.findViewById(R.id.rl1);
		rl2 = (RelativeLayout) rootView.findViewById(R.id.rl2);
		rl3 = (RelativeLayout) rootView.findViewById(R.id.rl3);
		rl4 = (RelativeLayout) rootView.findViewById(R.id.rl4);
		rl5 = (RelativeLayout) rootView.findViewById(R.id.rl5);
		rlLoai = (RelativeLayout) rootView.findViewById(R.id.rlLoai);

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
		// rl5 = (RelativeLayout) rootView.findViewById(R.id.rl5);
		// rlLoai = (RelativeLayout) rootView.findViewById(R.id.rlLoai);
		// rl5 = (RelativeLayout) rootView.findViewById(R.id.rl5);
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
		rlBatki.setOnClickListener(this);
		rl1.setOnClickListener(this);
		rl2.setOnClickListener(this);
		rl3.setOnClickListener(this);
		rl4.setOnClickListener(this);
		rl5.setOnClickListener(this);
		rlKhuVuc.setOnClickListener(this);
		swLuu.setOnCheckedChangeListener(this);
		rlHuyen.setOnClickListener(this);
		rlDuong.setOnClickListener(this);
		rlHT.setOnClickListener(this);
		// rlLoai.setOnClickListener(this);
		// rlLoai.setOnClickListener(this);
		// rlLoai.setOnClickListener(this);
		DataManager2.getInstance().showProgress(getActivity());
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

	private void notifyData() {
		tvTP.setText(proviceName);
		tvQuan.setText(disName);
		tvWard.setText(wardName);
		tvKV.setText(KVName);
		tvDuong.setText(streetName);
		swLuu.setChecked(sm.isSaveSetting());
		tvHinhthuc.setText(typeName);
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
				getTypeProperty();
				
			} else if (api.equals(ConstantValue.GET_TYPE)) {
				 DataManager2.getInstance().stopProgress();
			}
			// else if (api.equals(ConstantValue.GET_DIS)) {
			//
			// } else if (api.equals(ConstantValue.GET_DIS)) {
			//
			// }

		}

	};

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

	private void getTypeProperty() {
		DataManager2.getInstance().getTypeProperty(getActivity(), false, false, notifyData);
	}

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

	@Override
	public void onClick(View v) {
		// HotdealUtilities.setClickAnim(v);
		if (v == rlLoai) {
			HotdealUtilities.startActivity(getActivity(), LoaiNhaDat.class, "");

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
						disID = listDisFilter.get(id).getId();
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
			HotdealUtilities.showDialogCustomListView(getActivity(), null, new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					// TODO Auto-generated method stub

				}
			});

		} else if (v == rlMG) {
			HotdealUtilities.showDialogCustomListView(getActivity(), null, new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					// TODO Auto-generated method stub

				}
			});

		} else if (v == rlHuong) {
			HotdealUtilities.showDialogCustomListView(getActivity(), null, new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					// TODO Auto-generated method stub

				}
			});

		} else if (v == rlBatki) {
			setSP(R.drawable.ic_phongngu_choosen, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none);

		} else if (v == rl1) {
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_choosen, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none);

		} else if (v == rl2) {
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_choosen, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none);

		} else if (v == rl3) {
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_choosen, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none);

		} else if (v == rl4) {
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_choosen, R.drawable.ic_phongngu_none);

		} else if (v == rl5) {
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_choosen);

		}

		else if (v == rlHuyen) {
			HotdealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListWard(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						wardID = DataManager2.getInstance().getListWard().get(id).getId();
						wardName = DataManager2.getInstance().getListWard().get(id).getProvinceName();
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

		} else if (v == rlKhuVuc) {
			HotdealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListKhuvuc(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						KVID = DataManager2.getInstance().getListKhuvuc().get(id).getId();
						KVName = DataManager2.getInstance().getListKhuvuc().get(id).getProvinceName();
						notifyData();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});

		} else if (v == rlDuong) {
			HotdealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListStreet(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						streetID = DataManager2.getInstance().getListStreet().get(id).getId();
						streetName = DataManager2.getInstance().getListStreet().get(id).getProvinceName();
						notifyData();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});

		} else if (v == rlHT) {
			HotdealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListTypeProperty(),new NotifySomesDataListener() {
				
				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onReturnData(int id) {
					try {
						tyoeID = DataManager2.getInstance().getListTypeProperty().get(id).getId();
						typeName = DataManager2.getInstance().getListTypeProperty().get(id).getProvinceName();
						notifyData();
					} catch (Exception e) {
						// TODO: handle exception
					}

					
				}
			});

		}
		// else if (v == rlTinh) {
		// HotdealUtilities.showDialogCustomListView(getActivity());o
		//
		// }
		// else if (v == rlTinh) {
		// HotdealUtilities.showDialogCustomListView(getActivity());
		//
		// }
		// else if (v == rlTinh) {
		// HotdealUtilities.showDialogCustomListView(getActivity());
		//
		// }
		// else if (v == rlTinh) {
		// HotdealUtilities.showDialogCustomListView(getActivity());
		//
		// }
		// else if (v == rlTinh) {
		// HotdealUtilities.showDialogCustomListView(getActivity());
		//
		// }
		// else if (v == rlTinh) {
		// HotdealUtilities.showDialogCustomListView(getActivity());
		//
		// }

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
