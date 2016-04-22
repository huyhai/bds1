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
import com.vreal.libs.HotdealUtilities;
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
		},HotdealUtilities.getDataBundle(this));
	}

	private void initView() {
		rlHome = (RelativeLayout) this.findViewById(R.id.rlHome);
		epLoai = (ExpandableListView) this.findViewById(R.id.epLoai);
		btnOK = (Button) this.findViewById(R.id.btnOK);
		rlToogle = (LinearLayout) this.findViewById(R.id.rlToogle);
		HotdealUtilities.setHeight(rlHome, ConstantValue.HEIGHT_TOP_BAR);
		HotdealUtilities.setHeight(btnOK, 12);
		HotdealUtilities.setWidth(rlToogle, ConstantValue.WIDTH_BACK);
		rlToogle.setOnClickListener(this);
		// ArrayList<CateSildeModel> list = new ArrayList<>();
		// CateSildeModel md1;
		// CateSildeSubModel mdsub;
		// md1 = new CateSildeModel();
		// md1.setName("Bán căn hộ chung cư");
		// list.add(md1);
		//
		// md1 = new CateSildeModel();
		// md1.setName("Tất cả các loại nhà bán");
		// list.add(md1);
		// mdsub = new CateSildeSubModel();
		// mdsub.setName("Bán nhà riêng");
		// list.get(1).getListSub().add(mdsub);
		// mdsub = new CateSildeSubModel();
		// mdsub.setName("Bán nhà biệt thự, liền kề");
		// list.get(1).getListSub().add(mdsub);
		// mdsub = new CateSildeSubModel();
		// mdsub.setName("Bán nhà mặt phố");
		// list.get(1).getListSub().add(mdsub);
		//
		// md1 = new CateSildeModel();
		// md1.setName("Tất cả các loại đất bán");
		// list.add(md1);
		// mdsub = new CateSildeSubModel();
		// mdsub.setName("Bán đất nền dự án");
		// list.get(2).getListSub().add(mdsub);
		// mdsub = new CateSildeSubModel();
		// mdsub.setName("Bán đất");
		// list.get(2).getListSub().add(mdsub);
		//
		// md1 = new CateSildeModel();
		// md1.setName("Bán trang trại nghỉ dưỡng");
		// list.add(md1);
		//
		// md1 = new CateSildeModel();
		// md1.setName("Bán kho nhà xưởng");
		// list.add(md1);
		//
		// md1 = new CateSildeModel();
		// md1.setName("Bán các loại bất động sản khác");
		// list.add(md1);

		getData();
		
//		epLoai.setOnGroupClickListener(new OnGroupClickListener() {
//			
//			@Override
//			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//		});
	}

	NotifyVreal notify = new NotifyVreal() {

		@Override
		public void onNotify(VrealModel md) {
			HotdealUtilities.sendMessage(LoaiNhaDat.this, "ABC", md.getProvinceName()+"-"+md.getId());
		}
	};

	@Override
	public void onClick(View v) {
		if (rlToogle == v) {
			HotdealUtilities.setClickAnim(rlToogle);
			this.finish();
		}

	}

}
