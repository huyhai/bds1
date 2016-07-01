package com.vreal.ui2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.vrealapp.R;
import com.vreal.adapter.LoaiNhadatAdapter;
import com.vreal.libs.ConstantValue;
import com.vreal.libs.VrealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vreal.libs.NotifyVreal;
import com.vreal.model.VrealModel;
import com.vrealvn.vrealapp.DataManager2;

public class LoaiNhaDat extends Activity implements OnClickListener {
	private RelativeLayout rlHome;
	private ExpandableListView epLoai;
	private Button btnOK;
	private LinearLayout rlToogle;
	LoaiNhadatAdapter adapter;
	String idType = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loainhadat);
		initView();
	}

	private void getData() {
	
		DataManager2.getInstance().getLoaiNhaDat(this, true, false, new NotifyDataListener() {

			@Override
			public void onNotify(String api, int id) {
				if (NotifyDataListener.NOTIFY_OK == id) {
					adapter = new LoaiNhadatAdapter(LoaiNhaDat.this, DataManager2.getInstance().getListLoainhadat(), notify);
					epLoai.setAdapter(adapter);
					for (int i = 0; i < adapter.getGroupCount(); i++)
						epLoai.expandGroup(i);
				} else {

				}

			}
		}, idType);
	}

	private void getLoaiProject() {
		DataManager2.getInstance().getLoaiDuan(this, false, false, new NotifyDataListener() {
			
			@Override
			public void onNotify(String api, int id) {
				adapter = new LoaiNhadatAdapter(LoaiNhaDat.this, DataManager2.getInstance().getListLoaiDuAn(), notify);
				epLoai.setAdapter(adapter);
				for (int i = 0; i < adapter.getGroupCount(); i++)
					epLoai.expandGroup(i);
				
			}
		});
	}

	private void initView() {
		rlHome = (RelativeLayout) this.findViewById(R.id.rlHome);
		epLoai = (ExpandableListView) this.findViewById(R.id.epLoai);
		btnOK = (Button) this.findViewById(R.id.btnOK);
		rlToogle = (LinearLayout) this.findViewById(R.id.rlToogle);
		VrealUtilities.setHeight(rlHome, ConstantValue.HEIGHT_TOP_BAR);
		VrealUtilities.setHeight(btnOK, 12);
		VrealUtilities.setWidth(rlToogle, ConstantValue.WIDTH_BACK);
		rlToogle.setOnClickListener(this);
		try {
			idType = VrealUtilities.getDataBundle(this);
		} catch (Exception e) {
		}
		if(idType.equals("-1")){
			getLoaiProject();
		}else{
			getData();
		}
	

	}

	NotifyVreal notify = new NotifyVreal() {

		@Override
		public void onNotify(VrealModel md) {
			VrealUtilities.sendMessage(LoaiNhaDat.this, "ABC", md.getProvinceName() + "-" + md.getId());
			LoaiNhaDat.this.finish();
		}
	};

	@Override
	public void onClick(View v) {
		if (rlToogle == v) {
			VrealUtilities.setClickAnim(rlToogle);
			this.finish();
		}

	}

}
