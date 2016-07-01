package com.vreal.ui2;

import java.util.Calendar;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.vreal.libs.ConstantValue;
import com.vreal.libs.VrealUtilities;
import com.vreal.libs.NotifySomesDataListener;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.VrealFragmentActivity;
import com.vrealvn.vrealapp.VrealApp;

public class FilterF extends VrealFragmentActivity implements OnClickListener {
	// private RelativeLayout rlRefresh;
	private RelativeLayout rlKhuvuc;
	private RelativeLayout rlMuagia;
	private RelativeLayout rlLoai;
	private RelativeLayout rlNam;
	private TextView tvKhuvuc;
	private TextView tvMucgia;
	private TextView tvLoai;
	private TextView tvNam;
	private Button btnTimkiem;

	private String defauldID = "-1";
	private String loaiID = defauldID;
	private String loaiName = "Chọn loại";
	private String KVID = defauldID;
	private String KVName = VrealApp.getContext().getString(R.string.str_khuvuc);
	private String giaFrom = defauldID;
	private String giaTo = defauldID;
	private String giaName = "Chọn giá";
	private String namXD = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filter);
		initView();
	}

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View rootView = inflater.inflate(R.layout.filter, container, false);
	// initView(rootView);
	// return rootView;
	// }

	private void initView() {
		// rlRefresh = (RelativeLayout)
		// getActivity().findViewById(R.id.rlRefresh);
		rlKhuvuc = (RelativeLayout) findViewById(R.id.rlKhuvuc);
		rlMuagia = (RelativeLayout) findViewById(R.id.rlMuagia);
		rlLoai = (RelativeLayout) findViewById(R.id.rlLoai);
		rlNam = (RelativeLayout) findViewById(R.id.rlNam);
		tvKhuvuc = (TextView) findViewById(R.id.tvKhuvuc);
		tvMucgia = (TextView) findViewById(R.id.tvMucgia);
		tvLoai = (TextView) findViewById(R.id.tvLoai);
		tvNam = (TextView) findViewById(R.id.tvNam);
		btnTimkiem = (Button) findViewById(R.id.btnTimkiem);
		llTopBar = (LinearLayout) findViewById(R.id.llTopBar);
		imgTooggle = (ImageView) findViewById(R.id.imgTooggle);
		VrealUtilities.setHeight(llTopBar, 11.5);
		VrealUtilities.setWidthHeight(imgTooggle, 17, 35);
		findViewById(R.id.rlToogle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FilterF.this.finish();

			}
		});

		rlKhuvuc.setOnClickListener(this);
		rlMuagia.setOnClickListener(this);
		rlLoai.setOnClickListener(this);
		rlNam.setOnClickListener(this);
		// rlRefresh.setOnClickListener(this);
		btnTimkiem.setOnClickListener(this);
		notifyData();
	}

	@Override
	public void onResume() {
		super.onResume();
		// rlRefresh.setVisibility(View.VISIBLE);
		Main.setTextTop("Bộ lọc");
		IntentFilter intentGetKeySend = new IntentFilter();
		intentGetKeySend.addAction("ABC");
		registerReceiver(receiver, intentGetKeySend);
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try {
			unregisterReceiver(receiver);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View v) {
		if (v == rlKhuvuc) {
			VrealUtilities.showDialogCustomListView(this, DataManager2.getInstance().getListKhuvuc(), new NotifySomesDataListener() {

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
					}

				}
			});
		} else if (v == rlMuagia) {
			VrealUtilities.showDialogCustomListView(this, DataManager2.getInstance().getListGia(), new NotifySomesDataListener() {

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

		} else if (v == rlLoai) {
			VrealUtilities.startActivityForResult(this, LoaiNhaDat.class, 1, VrealUtilities.getDataBundle(this));

		} else if (v == rlNam) {
			show();
		}
		// else if (v == rlRefresh) {
		// setDefault();
		// }
		else if (v == btnTimkiem) {
			Intent returnIntent = new Intent();
			returnIntent.putExtra("result", KVID + "/" + giaFrom + "/" + loaiID);
			setResult(Activity.RESULT_OK, returnIntent);
			finish();

		}

	}

	public void show() {
		Calendar calendar = Calendar.getInstance();
		int thisYear = calendar.get(Calendar.YEAR);
		final Dialog d = new Dialog(this);
		d.setTitle("NumberPicker");
		d.setContentView(R.layout.dialog);
		Button b1 = (Button) d.findViewById(R.id.button1);
		Button b2 = (Button) d.findViewById(R.id.button2);
		final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
		np.setMaxValue(thisYear);
		np.setMinValue(thisYear - 20);
		np.setWrapSelectorWheel(false);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// tv.setText(String.valueOf(np.getValue()));
				d.dismiss();
				namXD = String.valueOf(np.getValue());
				notifyData();
			}
		});
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				d.dismiss();
			}
		});
		d.show();

	}

	private void setDefault() {
		defauldID = "";
		loaiID = defauldID;
		loaiName = "Chọn loại";
		KVID = defauldID;
		KVName = VrealApp.getContext().getString(R.string.str_khuvuc);
		giaFrom = defauldID;
		giaTo = defauldID;
		giaName = "Chọn giá";
		notifyData();
	}

	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equalsIgnoreCase("ABC")) {
				VrealUtilities.showALog(intent.getExtras().get(ConstantValue.IS_SUCCESS));
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
		tvKhuvuc.setText(KVName);
		tvMucgia.setText(giaName);
		tvLoai.setText(loaiName);
		tvNam.setText(namXD);
	}

}
