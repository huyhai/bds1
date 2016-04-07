package com.hotdealapp.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.vrealapp.R;
import com.hotdeal.libs.HotdealUtilities;
import com.hotdealapp.ui2.LoaiNhaDat;

public class NhabanF extends Fragment implements OnClickListener {
	private RelativeLayout rlBatki;
	private RelativeLayout rl1;
	private RelativeLayout rl2;
	private RelativeLayout rl3;
	private RelativeLayout rl4;
	private RelativeLayout rl5;

	private RelativeLayout rlLoai;

	private RelativeLayout rlTinh;

	private RelativeLayout rlQuan;
	private RelativeLayout rlDT;
	private RelativeLayout rlMG;
	private RelativeLayout rlHuong;

	// private RelativeLayout rl5;

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
		// rl5 = (RelativeLayout) rootView.findViewById(R.id.rl5);
		// rlLoai = (RelativeLayout) rootView.findViewById(R.id.rlLoai);
		// rl5 = (RelativeLayout) rootView.findViewById(R.id.rl5);
		// rlLoai = (RelativeLayout) rootView.findViewById(R.id.rlLoai);
		// rl5 = (RelativeLayout) rootView.findViewById(R.id.rl5);
		// rlLoai = (RelativeLayout) rootView.findViewById(R.id.rlLoai);
		// rl5 = (RelativeLayout) rootView.findViewById(R.id.rl5);
		// rlLoai = (RelativeLayout) rootView.findViewById(R.id.rlLoai);

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
		// rlLoai.setOnClickListener(this);
		// rlLoai.setOnClickListener(this);
		// rlLoai.setOnClickListener(this);
		// rlLoai.setOnClickListener(this);
		// rlLoai.setOnClickListener(this);

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
			HotdealUtilities.showDialogCustomListView(getActivity());

		} else if (v == rlQuan) {
			HotdealUtilities.showDialogCustomListView(getActivity());

		} else if (v == rlDT) {
			HotdealUtilities.showDialogCustomListView(getActivity());

		} else if (v == rlMG) {
			HotdealUtilities.showDialogCustomListView(getActivity());

		} else if (v == rlHuong) {
			HotdealUtilities.showDialogCustomListView(getActivity());

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
}
