package com.hotdealapp.ui2;

import com.android.vrealapp.R;
import com.hotdeal.adapter.LoaiNhadatAdapter;
import com.hotdeal.libs.ConstantValue;
import com.hotdeal.libs.HotdealUtilities;
import com.hotdeal.model.CateSildeModel;
import com.hotdeal.model.CateSildeSubModel;
import com.hotdealvn.hotdealapp.DataManager2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class LoaiNhaDat extends Activity {
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

		CateSildeModel md1;
		CateSildeSubModel mdsub;
		md1 = new CateSildeModel();
		md1.setName("Bán căn hộ chung cư");
		DataManager2.getInstance().getListCateSlide().add(md1);

		md1 = new CateSildeModel();
		md1.setName("Tất cả các loại nhà bán");
		DataManager2.getInstance().getListCateSlide().add(md1);
		mdsub = new CateSildeSubModel();
		mdsub.setName("Bán nhà riêng");
		DataManager2.getInstance().getListCateSlide().get(1).getListSub()
				.add(mdsub);
		mdsub = new CateSildeSubModel();
		mdsub.setName("Bán nhà biệt thự, liền kề");
		DataManager2.getInstance().getListCateSlide().get(1).getListSub()
				.add(mdsub);
		mdsub = new CateSildeSubModel();
		mdsub.setName("Bán nhà mặt phố");
		DataManager2.getInstance().getListCateSlide().get(1).getListSub()
				.add(mdsub);

		md1 = new CateSildeModel();
		md1.setName("Tất cả các loại đất bán");
		DataManager2.getInstance().getListCateSlide().add(md1);
		mdsub = new CateSildeSubModel();
		mdsub.setName("Bán đất nền dự án");
		DataManager2.getInstance().getListCateSlide().get(2).getListSub()
				.add(mdsub);
		mdsub = new CateSildeSubModel();
		mdsub.setName("Bán đất");
		DataManager2.getInstance().getListCateSlide().get(2).getListSub()
				.add(mdsub);

		md1 = new CateSildeModel();
		md1.setName("Bán trang trại nghỉ dưỡng");
		DataManager2.getInstance().getListCateSlide().add(md1);

		md1 = new CateSildeModel();
		md1.setName("Bán kho nhà xưởng");
		DataManager2.getInstance().getListCateSlide().add(md1);

		md1 = new CateSildeModel();
		md1.setName("Bán các loại bất động sản khác");
		DataManager2.getInstance().getListCateSlide().add(md1);

		LoaiNhadatAdapter adapter = new LoaiNhadatAdapter(this, DataManager2
				.getInstance().getListCateSlide(), null);
		epLoai.setAdapter(adapter);
	}

}
