package com.vreal.ui2;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.vreal.adapter.GanAdapter;
import com.vreal.libs.ConstantValue;
import com.vreal.libs.VrealUtilities;
import com.vreal.model.StateModel;
import com.vreal.model.VrealModel;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.VrealFragmentActivity;

public class DetailF extends VrealFragmentActivity implements OnClickListener {
	private RelativeLayout rlHome;
	private RelativeLayout rlPic;
	private RelativeLayout rlPic1;
	private LinearLayout rlToogle;
	private LinearLayout llPic;
	private ImageView imgPic1;
	// private RelativeLayout rlPic;
	// private RelativeLayout rlPic;
	// private RelativeLayout rlPic;
	// private RelativeLayout rlPic;
	// private RelativeLayout rlPic;
	private ListView lvGan;
	private TextView a;
	private TextView aa;
	private TextView aaa;
	private TextView aaaa;
	private TextView aaaaa;
	private TextView aaaaaa;
	private TextView aaaaaaa;
	private TextView tvDes;
	private TextView tvTienich;

	private TextView tvName;
	private TextView tvAddress;
	private TextView imgLogo;

	// private TextView aaaa;
	// private TextView aaaaa;
	// private TextView aaaaaa;
	// private TextView aaaaaaa;
	// private TextView tvDes;
	// private TextView a;
	// private TextView aa;
	// private TextView aaa;
	// private TextView aaaa;
	// private TextView aaaaa;
	// private TextView aaaaaa;
	// private TextView aaaaaaa;
	// private TextView tvDes;

	@Override
	protected void onCreate(@Nullable Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.detail);
		initView();
		initData();
	}

	private void initData() {
		VrealModel md = DataManager2.getInstance().getVrealModel();
		tvName.setText(md.getProvinceName());
		tvAddress.setText(md.getAddress());
		tvDes.setText(Html.fromHtml(md.getDescription()));
		String ticc = "";
		for (String ti : md.getListTienIch()) {
			ticc += "- " + ti + "<br />";
		}
		tvTienich.setText(Html.fromHtml(ticc));
		imgLogo.setText(md.getDeveloper());
		try {
			VrealUtilities.loadImagePicaso(md.getListPhoto().get(0), imgPic1,
					this);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// tvAddress.setText(md.getProvinceName());
		// tvAddress.setText(md.getProvinceName());
		// tvAddress.setText(md.getProvinceName());
		// tvAddress.setText(md.getProvinceName());
		// tvAddress.setText(md.getProvinceName());

	}

	private void initView() {
		rlHome = (RelativeLayout) this.findViewById(R.id.rlHome);
		rlPic = (RelativeLayout) this.findViewById(R.id.rlPic);
		a = (TextView) this.findViewById(R.id.a);
		aa = (TextView) this.findViewById(R.id.aa);
		aaa = (TextView) this.findViewById(R.id.aaa);
		aaaa = (TextView) this.findViewById(R.id.aaaa);
		aaaaa = (TextView) this.findViewById(R.id.aaaaa);
		aaaaaa = (TextView) this.findViewById(R.id.aaaaaa);
		aaaaaaa = (TextView) this.findViewById(R.id.aaaaaaa);
		tvDes = (TextView) this.findViewById(R.id.tvDes);
		tvTienich = (TextView) this.findViewById(R.id.tvTienich);

		rlPic1 = (RelativeLayout) this.findViewById(R.id.rlPic1);
		llPic = (LinearLayout) this.findViewById(R.id.llPic);
		lvGan = (ListView) this.findViewById(R.id.lvGan);

		rlToogle = (LinearLayout) this.findViewById(R.id.rlToogle);
		VrealUtilities.setWidth(rlToogle, ConstantValue.WIDTH_BACK);
		rlToogle.setOnClickListener(this);
		tvName = (TextView) this.findViewById(R.id.tvName);
		tvAddress = (TextView) this.findViewById(R.id.tvAddress);
		imgLogo = (TextView) this.findViewById(R.id.imgLogo);
		// tvName = (TextView) this.findViewById(R.id.tvName);
		// tvName = (TextView) this.findViewById(R.id.tvName);
		// tvName = (TextView) this.findViewById(R.id.tvName);
		// tvName = (TextView) this.findViewById(R.id.tvName);
		// tvName = (TextView) this.findViewById(R.id.tvName);
		// tvName = (TextView) this.findViewById(R.id.tvName);
		// tvName = (TextView) this.findViewById(R.id.tvName);
		// rlPic=(RelativeLayout)this.findViewById(R.id.rlPic);
		// rlHome=(RelativeLayout)this.findViewById(R.id.rlHome);
		// rlPic=(RelativeLayout)this.findViewById(R.id.rlPic);
		// rlHome=(RelativeLayout)this.findViewById(R.id.rlHome);
		// rlPic=(RelativeLayout)this.findViewById(R.id.rlPic);
		// rlHome=(RelativeLayout)this.findViewById(R.id.rlHome);
		// rlPic=(RelativeLayout)this.findViewById(R.id.rlPic);
		// rlHome=(RelativeLayout)this.findViewById(R.id.rlHome);
		imgPic1 = (ImageView) this.findViewById(R.id.imgPic1);
		imgTooggle=(ImageView)findViewById(R.id.imgTooggle);
		VrealUtilities.setWidthHeight(imgTooggle, 17, 35);
		findViewById(R.id.rlToogle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DetailF.this.finish();

			}
		});
		double w = 2.2;
		VrealUtilities.setWidth(a, w);
		VrealUtilities.setWidth(aa, w);
		VrealUtilities.setWidth(aaa, w);
		VrealUtilities.setWidth(aaaa, w);
		VrealUtilities.setWidth(aaaaa, w);
		VrealUtilities.setWidth(aaaaaa, w);
		VrealUtilities.setWidth(aaaaaaa, w);

		VrealUtilities.setHeight(rlHome, ConstantValue.HEIGHT_TOP_BAR);
		VrealUtilities.setHeight(rlPic, 3);
		VrealUtilities.setWidthHeight(rlPic1, 10, 20);
		VrealUtilities.setHeight(llPic, 4.5);
		imgPic1.setOnClickListener(this);

		// String htl =
		// "Chỉ 735 triệu, bạn và gia đình đã sở hữu nhà mới – đẹp –hiện đại.<br /> - Ngay ngã 4 Nữ Dân Công và Quách Điêu.<br />- Cách Ngã 5 Nguyễn Thị Tú, Vĩnh lộc, Hương Lộ 80 1km.<br />- Với thiết kế 1 trệt 1 lầu, không gian rộng rãi thoáng mát, đón nắng và gió tự nhiện.<br />- Phòng khách, 2 phòng ngủ, 2 toilet, bếp, sân để xe, ban công, tất cả đều đẹp, nội thất hài hòa, màu sắc trang nhã.<br />- Công trình được thiết kế móng đổ vỉ đà kiềng, sắt thép Việt-Nhật, xi măng Fico, gạch xây Tuynel.<br />- Nên chất lượng nhà rất tốt.<br />- Đường rộng 5m, bê tông, thông thoáng, sạch sẽ, xe hơi vào tận nhà.<br />- Xung quanh là khu vui chơi giải trí, mua sắm, chợ và công viên.<br />- Giao thông thuận tiện.<br />- Chỉ mất 5 phút tới KCN Tân Bình.<br />- Thanh toán dễ dàng, thủ tuc nhanh – gọn – lẹ.";
		//
		// String aaa =
		// "- Công viên<br /> - Trường học<br /> - Khu vui chơi trẻ em<br /> - Nhà hàng<br /> - Chuỗi cửa hàng tiện lợi<br /> - Hầm đậu xe<br /> - Nhà trẻ...";

		ArrayList<StateModel> list = new ArrayList<>();
		StateModel md;

		md = new StateModel();
		md.setImage(R.drawable.ic_maybay);
		md.setStateName("Sân bay");
		md.setSort("20.3 km");
		md.setStateID("23 phút");
		list.add(md);

		md = new StateModel();
		md.setImage(R.drawable.ic_cho);
		md.setStateName("Gần chợ");
		md.setSort("20.3 km");
		md.setStateID("23 phút");
		list.add(md);

		md = new StateModel();
		md.setImage(R.drawable.ic_mu);
		md.setStateName("Gần trường học");
		md.setSort("20.3 km");
		md.setStateID("23 phút");
		list.add(md);

		md = new StateModel();
		md.setImage(R.drawable.ic_trungtam);
		md.setStateName("Gần thành phố");
		md.setSort("20.3 km");
		md.setStateID("23 phút");
		list.add(md);

		md = new StateModel();
		md.setImage(R.drawable.ic_dao);
		md.setStateName("Cửa hàng đồ ăn 12 cửa hàng gần nhất");
		md.setSort("");
		md.setStateID("");
		list.add(md);

//		GanAdapter adapter = new GanAdapter(this, list, null);
//		lvGan.setAdapter(adapter);
//		HotdealUtilities.setListViewHeightBasedOnChildren(lvGan);
	}

	@Override
	public void onClick(View v) {
		if (rlToogle == v) {
			VrealUtilities.setClickAnim(rlToogle);
			this.finish();
		} else if (v == imgPic1) {
			VrealUtilities.startActivity(this, ViewImageActivity.class, "");
		}

	}
}
