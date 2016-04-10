package com.hotdealapp.ui2;

import java.util.ArrayList;

import com.android.vrealapp.R;
import com.hotdeal.adapter.LoaiNhadatAdapter;
import com.hotdeal.libs.ConstantValue;
import com.hotdeal.libs.HotdealUtilities;
import com.hotdeal.model.CateSildeModel;
import com.hotdeal.model.CateSildeSubModel;
import com.hotdealvn.hotdealapp.DataManager2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class LoaiNhaDat extends Activity implements OnClickListener {
	private RelativeLayout rlHome;
	private ExpandableListView epLoai;
	private Button btnOK;
	private LinearLayout rlToogle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loainhadat);
		initView();
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
		ArrayList<CateSildeModel> list=new ArrayList<>();
		CateSildeModel md1;
		CateSildeSubModel mdsub;
		md1 = new CateSildeModel();
		md1.setName("Bán căn hộ chung cư");
		list.add(md1);

		md1 = new CateSildeModel();
		md1.setName("Tất cả các loại nhà bán");
		list.add(md1);
		mdsub = new CateSildeSubModel();
		mdsub.setName("Bán nhà riêng");
		list.get(1).getListSub()
				.add(mdsub);
		mdsub = new CateSildeSubModel();
		mdsub.setName("Bán nhà biệt thự, liền kề");
		list.get(1).getListSub()
				.add(mdsub);
		mdsub = new CateSildeSubModel();
		mdsub.setName("Bán nhà mặt phố");
		list.get(1).getListSub()
				.add(mdsub);

		md1 = new CateSildeModel();
		md1.setName("Tất cả các loại đất bán");
		list.add(md1);
		mdsub = new CateSildeSubModel();
		mdsub.setName("Bán đất nền dự án");
		list.get(2).getListSub()
				.add(mdsub);
		mdsub = new CateSildeSubModel();
		mdsub.setName("Bán đất");
		list.get(2).getListSub()
				.add(mdsub);

		md1 = new CateSildeModel();
		md1.setName("Bán trang trại nghỉ dưỡng");
		list.add(md1);

		md1 = new CateSildeModel();
		md1.setName("Bán kho nhà xưởng");
		list.add(md1);

		md1 = new CateSildeModel();
		md1.setName("Bán các loại bất động sản khác");
		list.add(md1);

		LoaiNhadatAdapter adapter = new LoaiNhadatAdapter(this,list, null);
		epLoai.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		if (rlToogle == v) {
			HotdealUtilities.setClickAnim(rlToogle);
			this.finish();
		}

	}

}
