package com.vreal.ui;

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
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vreal.libs.NotifySomesDataListener;
import com.vreal.ui2.LoaiNhaDat;
import com.vreal.ui2.VrealFragment;
import com.vrealvn.vrealapp.DataManager2;

public class NhabanF extends VrealFragment implements OnClickListener,
		OnCheckedChangeListener {
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

	// private RelativeLayout rl5;

	// DATA
	private String proviceID = "-1";
	private String proviceName = "Tỉnh/Thành phố";
	private String disID = "-1";
	private String disName = "Quận/Huyện";
	private String areaID = "-1";
	private String areaName = "Diện tích";
	private String priceID = "-1";
	private String priceName = "Mức giá";
	private String wayID = "-1";
	private String wayName = "Hướng nhà";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.nhaban, container, false);
		initView(rootView);
		return rootView;
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
		// rl5 = (RelativeLayout) rootView.findViewById(R.id.rl5);
		// rlLoai = (RelativeLayout) rootView.findViewById(R.id.rlLoai);
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
		swLuu.setOnCheckedChangeListener(this);
		// rlLoai.setOnClickListener(this);
		// rlLoai.setOnClickListener(this);
		// rlLoai.setOnClickListener(this);
		// rlLoai.setOnClickListener(this);
		// rlLoai.setOnClickListener(this);
		getProvice();

	}

	private void getProvice() {
		DataManager2.getInstance().getProvice(getActivity(), true, false,
				new NotifyDataListener() {

					@Override
					public void onNotify(int id) {
						if (NotifyDataListener.NOTIFY_OK == id) {
						} else {

						}

					}
				});
	}

	private void getDistric(String dis) {
		DataManager2.getInstance().getDistrict(getActivity(), true, false,
				new NotifyDataListener() {

					@Override
					public void onNotify(int id) {
						if (NotifyDataListener.NOTIFY_OK == id) {

						} else {

						}

					}
				}, dis);
	}

	private void setSP(int batki, int l1, int l2, int l3, int l4, int l5) {
		rlBatki.setBackgroundResource(batki);
		rl1.setBackgroundResource(l1);
		rl2.setBackgroundResource(l2);
		rl3.setBackgroundResource(l3);
		rl4.setBackgroundResource(l4);
		rl5.setBackgroundResource(l5);
	}

	@Override
	public void onClick(View v) {
		// HotdealUtilities.setClickAnim(v);
		if (v == rlLoai) {
			HotdealUtilities.startActivity(getActivity(), LoaiNhaDat.class, "");

		} else if (v == rlTinh) {
			HotdealUtilities.showDialogCustomListView(getActivity(),
					DataManager2.getInstance().getListProvices(),
					new NotifySomesDataListener() {

						@Override
						public void onReturnDataString(String id) {
						}

						@Override
						public void onReturnData(int id) {
							try {
								tvQuan.setText(disName);
								tvTP.setText(DataManager2.getInstance()
										.getListProvices().get(id)
										.getProvinceName());
								getDistric(DataManager2.getInstance()
										.getListProvices().get(id).getId());
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
					});

		} else if (v == rlQuan) {
			HotdealUtilities.showDialogCustomListView(getActivity(),
					DataManager2.getInstance().getListDistrict(),
					new NotifySomesDataListener() {

						@Override
						public void onReturnDataString(String id) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onReturnData(int id) {
							tvQuan.setText(DataManager2.getInstance()
									.getListDistrict().get(id)
									.getProvinceName());

						}
					});

		} else if (v == rlDT) {
			HotdealUtilities.showDialogCustomListView(getActivity(), null,
					new NotifySomesDataListener() {

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
			HotdealUtilities.showDialogCustomListView(getActivity(), null,
					new NotifySomesDataListener() {

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
			HotdealUtilities.showDialogCustomListView(getActivity(), null,
					new NotifySomesDataListener() {

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
			setSP(R.drawable.ic_phongngu_choosen, R.drawable.ic_phongngu_none,
					R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none,
					R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none);

		} else if (v == rl1) {
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_choosen,
					R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none,
					R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none);

		} else if (v == rl2) {
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none,
					R.drawable.ic_phongngu_choosen,
					R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none,
					R.drawable.ic_phongngu_none);

		} else if (v == rl3) {
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none,
					R.drawable.ic_phongngu_none,
					R.drawable.ic_phongngu_choosen,
					R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none);

		} else if (v == rl4) {
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none,
					R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none,
					R.drawable.ic_phongngu_choosen, R.drawable.ic_phongngu_none);

		} else if (v == rl5) {
			setSP(R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none,
					R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_none,
					R.drawable.ic_phongngu_none, R.drawable.ic_phongngu_choosen);

		}

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
