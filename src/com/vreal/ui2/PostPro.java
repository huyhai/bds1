package com.vreal.ui2;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.vreal.adapter.ListImagesAdapter;
import com.vreal.libs.VrealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vreal.libs.NotifySomesDataListener;
import com.vreal.libs.TwoWayView;
import com.vreal.model.VrealModel;
import com.vrealvn.vrealapp.DataManager2;

public class PostPro extends Fragment implements OnClickListener {
	private TextView tvAddIMG;
	private TwoWayView lvIMG;
	private ArrayList<Uri> listUri = new ArrayList<>();
	ListImagesAdapter adapter;
	private RelativeLayout rlBatdau;
	private RelativeLayout rlKetthuc;
	private TextView edBatdau;
	private TextView edKetthuc;
	private RelativeLayout rlHinhthuc;
	private RelativeLayout rlTinh;
	private RelativeLayout rlKhuVuc;
	private RelativeLayout rlXa;
	private RelativeLayout rlQuan1;
	private RelativeLayout rlDuong;
	private RelativeLayout rlDuan;

	private RelativeLayout rlDT;
	private RelativeLayout rlMota;
	private RelativeLayout rlHuongnha;

	private RelativeLayout rlDonvi;
	// private RelativeLayout rlHuongnha;
	// private RelativeLayout rlMota;
	// private RelativeLayout rlHuongnha;
	// private RelativeLayout rlMota;
	// private RelativeLayout rlHuongnha;
	// private RelativeLayout rlMota;
	// private RelativeLayout rlHuongnha;
	// private RelativeLayout rlMota;
	// private RelativeLayout rlHuongnha;

	private RelativeLayout rlHuongbancong;

	private TextView tvHinhthuc;

	private TextView tvTinh;
	private TextView tvKV;
	private TextView tvXa;
	private TextView tvQ;
	private TextView tvDuong;
	private TextView tvDuanValue;
	private TextView tvHuongNha;
	private TextView tvHuongbancong;
	private Button btnDangTin;

	private TextView tvDientich;

	private EditText edKhuvuc;
	private EditText edMota;
	private EditText edMattien;

	private EditText edDuongvao;
	private EditText edSoTang;
	private EditText edNoithat;
	private EditText edDiachi;
	private EditText edSoPngu;
	private EditText edToilet;

	private EditText edTenLh;
	private EditText edDiachiLh;
	private EditText edDienThoai;
	private EditText edEmailLh;

	// private EditText edSoPngu;
	// private EditText edToilet;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.post, container, false);
		initView(rootView);
		initData();
		return rootView;
	}

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// // TODO Auto-generated method stub
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.post);
	// initView();
	// initData();
	// }

	private void initData() {
		adapter = new ListImagesAdapter(getActivity(), listUri, no);
		lvIMG.setAdapter(adapter);
		lvIMG.setItemMargin(10);
	}

	NotifyDataListener no = new NotifyDataListener() {

		@Override
		public void onNotify(String api, int id) {
			try {
				listUri.remove(id);
			} catch (Exception e) {
			}
			adapter.notifyDataSetChanged();

		}
	};

	private void initView(View rootView) {
		tvAddIMG = (TextView) rootView.findViewById(R.id.tvAddIMG);
		lvIMG = (TwoWayView) rootView.findViewById(R.id.lvIMG);
		rlBatdau = (RelativeLayout) rootView.findViewById(R.id.rlBatdau);
		rlKetthuc = (RelativeLayout) rootView.findViewById(R.id.rlKetthuc);
		edBatdau = (TextView) rootView.findViewById(R.id.edBatdau);
		edKetthuc = (TextView) rootView.findViewById(R.id.edKetthuc);
		rlHinhthuc = (RelativeLayout) rootView.findViewById(R.id.rlTinh);
		rlTinh = (RelativeLayout) rootView.findViewById(R.id.rlHuyen);
		rlKhuVuc = (RelativeLayout) rootView.findViewById(R.id.rlKhuVuc);
		rlXa = (RelativeLayout) rootView.findViewById(R.id.rlXa);
		rlQuan1 = (RelativeLayout) rootView.findViewById(R.id.rlQuan1);
		rlDuong = (RelativeLayout) rootView.findViewById(R.id.rlDuong);
		rlDuan = (RelativeLayout) rootView.findViewById(R.id.rlDuan);
		rlDT = (RelativeLayout) rootView.findViewById(R.id.rlDT);
		rlMota = (RelativeLayout) rootView.findViewById(R.id.rlMota);
		rlHuongnha = (RelativeLayout) rootView.findViewById(R.id.rlHuongnha);
		rlHuongbancong = (RelativeLayout) rootView.findViewById(R.id.rlHuongbancong);
		tvHinhthuc = (TextView) rootView.findViewById(R.id.tv);
		tvTinh = (TextView) rootView.findViewById(R.id.tvWard);
		tvKV = (TextView) rootView.findViewById(R.id.tvKV);
		tvXa = (TextView) rootView.findViewById(R.id.tvXa);
		tvQ = (TextView) rootView.findViewById(R.id.tvQ);
		tvDuong = (TextView) rootView.findViewById(R.id.tvDuong);
		tvDuanValue = (TextView) rootView.findViewById(R.id.tvDuanValue);
		tvHuongNha = (TextView) rootView.findViewById(R.id.tvHuongNha);
		tvHuongbancong = (TextView) rootView.findViewById(R.id.tvHuongbancong);
		btnDangTin = (Button) rootView.findViewById(R.id.btnDangTin);
		tvDientich = (TextView) rootView.findViewById(R.id.tvDientich);
		edKhuvuc = (EditText) rootView.findViewById(R.id.edKhuvuc);
		edMota = (EditText) rootView.findViewById(R.id.edMota);
		edMattien = (EditText) rootView.findViewById(R.id.edMattien);
		rlDonvi = (RelativeLayout) rootView.findViewById(R.id.rlDonvi);
		// rlDuong = (RelativeLayout) rootView.findViewById(R.id.rlDuong);
		// rlDuan = (RelativeLayout) rootView.findViewById(R.id.rlDuan);
		// rlDT = (RelativeLayout) rootView.findViewById(R.id.rlDT);
		// rlMota = (RelativeLayout) rootView.findViewById(R.id.rlMota);
		// rlHuongnha = (RelativeLayout) rootView.findViewById(R.id.rlHuongnha);
		// rlHuongbancong = (RelativeLayout)
		// rootView.findViewById(R.id.rlHuongbancong);
		// rlQuan1 = (RelativeLayout) rootView.findViewById(R.id.rlQuan1);
		// rlDuong = (RelativeLayout) rootView.findViewById(R.id.rlDuong);
		// rlDuan = (RelativeLayout) rootView.findViewById(R.id.rlDuan);
		// rlDT = (RelativeLayout) rootView.findViewById(R.id.rlDT);
		// rlMota = (RelativeLayout) rootView.findViewById(R.id.rlMota);
		// rlHuongnha = (RelativeLayout) rootView.findViewById(R.id.rlHuongnha);
		// rlHuongbancong = (RelativeLayout)
		// rootView.findViewById(R.id.rlHuongbancong);
		edDuongvao = (EditText) rootView.findViewById(R.id.edDuongvao);
		edSoTang = (EditText) rootView.findViewById(R.id.edSoTang);
		edNoithat = (EditText) rootView.findViewById(R.id.edNoithat);
		edDiachi = (EditText) rootView.findViewById(R.id.edDiachi);
		edSoPngu = (EditText) rootView.findViewById(R.id.edSoPngu);
		edToilet = (EditText) rootView.findViewById(R.id.edToilet);
		edTenLh = (EditText) rootView.findViewById(R.id.edTenLh);
		edDiachiLh = (EditText) rootView.findViewById(R.id.edDiachiLh);

		edDienThoai = (EditText) rootView.findViewById(R.id.edDienThoai);
		edEmailLh = (EditText) rootView.findViewById(R.id.edEmailLh);
		// edToilet = (EditText) rootView.findViewById(R.id.edToilet);
		// edTenLh = (EditText) rootView.findViewById(R.id.edTenLh);
		// edDiachiLh = (EditText) rootView.findViewById(R.id.edDiachiLh);

		tvAddIMG.setOnClickListener(this);
		rlBatdau.setOnClickListener(this);
		rlKetthuc.setOnClickListener(this);
		rlHinhthuc.setOnClickListener(this);
		rlTinh.setOnClickListener(this);
		rlKhuVuc.setOnClickListener(this);
		rlXa.setOnClickListener(this);
		rlQuan1.setOnClickListener(this);
		rlDuong.setOnClickListener(this);
		rlDuan.setOnClickListener(this);
		rlDT.setOnClickListener(this);
		VrealUtilities.setHeight(rlMota, 4);
		rlHuongnha.setOnClickListener(this);
		rlHuongbancong.setOnClickListener(this);
		btnDangTin.setOnClickListener(this);
		rlDonvi.setOnClickListener(this);
		// tvAddIMG.setOnClickListener(this);
		// tvAddIMG.setOnClickListener(this);
		// tvAddIMG.setOnClickListener(this);
		// tvAddIMG.setOnClickListener(this);

	}

	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			Calendar myCalendar = Calendar.getInstance();
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

			if (isStart) {
				edBatdau.setText(VrealUtilities.getDateFromDate(myCalendar.getTime(), VrealUtilities.FORMAT_DATE));
			} else {
				edKetthuc.setText(VrealUtilities.getDateFromDate(myCalendar.getTime(), VrealUtilities.FORMAT_DATE));
			}
		}

	};
	private boolean isStart;
	private int posHinhthuc = 0;
	private int posTinh = 0;

	private int posKhuvuc = 0;
	private int posXa = 0;
	private int posQuan = 0;
	private int posDuong = 0;
	private int posDuan = 0;
	private int posHuongnha = 0;
	private int posHuongbancong = 0;

	private int posDientich = 0;

	// private int posHinhthuc = 0;
	// private int posTinh = 0;

	@Override
	public void onClick(View v) {
		if (v == tvAddIMG) {
			VrealUtilities.getImageFromGalery(getActivity());
		} else if (v == rlBatdau) {
			isStart = true;
			VrealUtilities.pickerDate(getActivity(), date);
		} else if (v == rlKetthuc) {
			isStart = false;
			VrealUtilities.pickerDate(getActivity(), date);
		}

		else if (v == rlHinhthuc) {
			DataManager2.getInstance().setListTypeProperty(new ArrayList<VrealModel>());
			VrealModel md;
			md = new VrealModel();
			md.setProvinceName("Nhà bán");
			md.setId("1");
			md = new VrealModel();
			md.setProvinceName("Nhà cho thuê");
			md.setId("2");
			VrealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListTypeProperty(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						posHinhthuc = id;
						tvHinhthuc.setText(DataManager2.getInstance().getListTypeProperty().get(posHinhthuc).getProvinceName());
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});

		} else if (v == rlTinh) {
			VrealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListProvices(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
				}

				@Override
				public void onReturnData(int id) {

					try {
						posTinh = id;
						tvTinh.setText(DataManager2.getInstance().getListProvices().get(posTinh).getProvinceName());
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});

		} else if (v == rlKhuVuc) {
			VrealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListKhuvuc(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						posKhuvuc = id;
						tvKV.setText(DataManager2.getInstance().getListKhuvuc().get(posKhuvuc).getProvinceName());
					} catch (Exception e) {
					}

				}
			});
		} else if (v == rlXa) {
			VrealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListWard(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
				}

				@Override
				public void onReturnData(int id) {
					try {
						posXa = id;
						tvXa.setText(DataManager2.getInstance().getListWard().get(posXa).getProvinceName());
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});
		} else if (v == rlQuan1) {
			// final ArrayList<VrealModel> listDisFilter = new ArrayList<>();
			// for (VrealModel md :
			// DataManager2.getInstance().getListDistrict()) {
			// if (md.getProvinceID().equals(proviceID)) {
			// listDisFilter.add(md);
			// }
			// }
			VrealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListDistrict(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						posQuan = id;
						tvQ.setText(DataManager2.getInstance().getListDistrict().get(posQuan).getProvinceName());
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});
		} else if (v == rlDuong) {
			VrealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListStreet(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {

				}

				@Override
				public void onReturnData(int id) {
					try {
						posDuong = id;
						tvDuong.setText(DataManager2.getInstance().getListStreet().get(posDuong).getProvinceName());
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});
		} else if (v == rlDuan) {
			VrealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListDuAn(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						posDuan = id;
						tvDuanValue.setText(DataManager2.getInstance().getListDuAn().get(posDuan).getProvinceName());
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});
		} else if (v == rlDT) {
			VrealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListDientich(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReturnData(int id) {
					try {
						posDientich = id;
						tvDientich.setText(DataManager2.getInstance().getListDientich().get(posDientich).getProvinceName());
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});
		} else if (v == rlHuongnha) {
			VrealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListHuong(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
				}

				@Override
				public void onReturnData(int id) {
					try {
						posHuongnha = id;
						tvHuongNha.setText(DataManager2.getInstance().getListHuong().get(posHuongnha).getProvinceName());
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});
		} else if (v == rlHuongbancong) {
			VrealUtilities.showDialogCustomListView(getActivity(), DataManager2.getInstance().getListHuong(), new NotifySomesDataListener() {

				@Override
				public void onReturnDataString(String id) {
				}

				@Override
				public void onReturnData(int id) {
					try {
						posHuongbancong = id;
						tvHuongbancong.setText(DataManager2.getInstance().getListHuong().get(posHuongbancong).getProvinceName());
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});
		} else if (v == btnDangTin) {
			submit();
		} else if (v == rlDonvi) {

		}
		// else if (v == rlBatdau) {
		//
		// }
		// else if (v == rlBatdau) {
		//
		// }
		// else if (v == rlBatdau) {
		//
		// }
		// else if (v == rlBatdau) {
		//
		// }
		// else if (v == rlBatdau) {
		//
		// }
		// else if (v == rlBatdau) {
		//
		// }
		// else if (v == rlBatdau) {
		//
		// }

	}

	private void submit() {
		String hinhthucID = "";
		String tinhID = "";
		String khuvucID = "";
		String phuongID = "";
		String quanID = "";
		String duongID = "";
		String duanID = "";
		String huongnhaID = "";
		String huongbancongID = "";
		int dientichID = 0;
		try {
			hinhthucID = DataManager2.getInstance().getListTypeProperty().get(posHinhthuc).getId();
			tinhID = DataManager2.getInstance().getListProvices().get(posTinh).getProvinceID();
			khuvucID = DataManager2.getInstance().getListKhuvuc().get(posKhuvuc).getId();
			phuongID = DataManager2.getInstance().getListWard().get(posXa).getWardID();
			quanID = DataManager2.getInstance().getListDistrict().get(posQuan).getDistrictID();
			duongID = DataManager2.getInstance().getListStreet().get(posDuong).getStreetID();
			duanID = DataManager2.getInstance().getListDuAn().get(posDuan).getId();
			huongnhaID = DataManager2.getInstance().getListHuong().get(posHuongnha).getId();
			huongbancongID = DataManager2.getInstance().getListHuong().get(posHuongbancong).getId();
			dientichID = DataManager2.getInstance().getListDientich().get(posDientich).getValue1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataManager2.getInstance().post(getActivity(), true, true, new NotifyDataListener() {

			@Override
			public void onNotify(String api, int id) {
		
				if (NOTIFY_OK == id) {
					VrealUtilities.showToast("Đăng tin thành công", 0, getActivity());
				} else {
//					HotdealUtilities.showToast("Đăng tin TB", 0, getActivity());
				}

			}
		}, hinhthucID, duanID, "", duanID, tinhID, quanID, phuongID, duongID, "", khuvucID, "", edKhuvuc.getText().toString(), edMota.getText().toString(), edMattien.getText().toString(),
				edDuongvao.getText().toString(), edSoTang.getText().toString(), edNoithat.getText().toString(), edDiachi.getText().toString(), "price", huongnhaID, huongbancongID,
				edSoPngu.getText().toString(), edToilet.getText().toString(), /* latitude */"0", /* longitude */"0", "1", edBatdau.getText().toString(), edKetthuc.getText().toString(),
				edTenLh.getText().toString(), edDiachiLh.getText().toString(), edDienThoai.getText().toString(), edEmailLh.getText().toString(), /* creatorID */"", /* userID */"");

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case VrealUtilities.SELECT_PHOTO:
			if (resultCode == Activity.RESULT_OK) {
				Uri selectedImage = data.getData();
				listUri.add(Uri.parse("file://" + VrealUtilities.getPath(selectedImage, getActivity())));
				adapter.notifyDataSetChanged();
				// try {
				// Bitmap yourSelectedImage =
				// HotdealUtilities.decodeUri(selectedImage, PostPro.this);
				// ImageView img = (ImageView) findViewById(R.id.aaa);
				// img.setImageBitmap(yourSelectedImage);
				// } catch (FileNotFoundException e) {
				// e.printStackTrace();
				// }
			}
		}
	}

}
