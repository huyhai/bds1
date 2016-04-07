package com.hotdealapp.ui2;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.hotdeal.adapter.GanAdapter;
import com.hotdeal.libs.ConstantValue;
import com.hotdeal.libs.HotdealUtilities;
import com.hotdeal.model.StateModel;

public class DetailF extends FragmentActivity implements OnClickListener {
	private RelativeLayout rlHome;
	private RelativeLayout rlPic;
	private RelativeLayout rlPic1;
	private LinearLayout rlToogle;
	private LinearLayout llPic;
	// private RelativeLayout rlPic;
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

	@Override
	protected void onCreate(@Nullable Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.detail);
		initView();
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
		HotdealUtilities.setWidth(rlToogle, ConstantValue.WIDTH_BACK);
		rlToogle.setOnClickListener(this);
		// rlPic=(RelativeLayout)this.findViewById(R.id.rlPic);
		// rlHome=(RelativeLayout)this.findViewById(R.id.rlHome);
		// rlPic=(RelativeLayout)this.findViewById(R.id.rlPic);
		// rlHome=(RelativeLayout)this.findViewById(R.id.rlHome);
		// rlPic=(RelativeLayout)this.findViewById(R.id.rlPic);
		// rlHome=(RelativeLayout)this.findViewById(R.id.rlHome);
		// rlPic=(RelativeLayout)this.findViewById(R.id.rlPic);
		// rlHome=(RelativeLayout)this.findViewById(R.id.rlHome);
		// rlPic=(RelativeLayout)this.findViewById(R.id.rlPic);
		double w = 2.2;
		HotdealUtilities.setWidth(a, w);
		HotdealUtilities.setWidth(aa, w);
		HotdealUtilities.setWidth(aaa, w);
		HotdealUtilities.setWidth(aaaa, w);
		HotdealUtilities.setWidth(aaaaa, w);
		HotdealUtilities.setWidth(aaaaaa, w);
		HotdealUtilities.setWidth(aaaaaaa, w);

		HotdealUtilities.setHeight(rlHome, ConstantValue.HEIGHT_TOP_BAR);
		HotdealUtilities.setHeight(rlPic, 3);
		HotdealUtilities.setWidthHeight(rlPic1, 10, 20);
		HotdealUtilities.setHeight(llPic, 4.5);

		String htl = "Chỉ 735 triệu, bạn và gia đình đã sở hữu nhà mới – đẹp –hiện đại.<br /> - Ngay ngã 4 Nữ Dân Công và Quách Điêu.<br />- Cách Ngã 5 Nguyễn Thị Tú, Vĩnh lộc, Hương Lộ 80 1km.<br />- Với thiết kế 1 trệt 1 lầu, không gian rộng rãi thoáng mát, đón nắng và gió tự nhiện.<br />- Phòng khách, 2 phòng ngủ, 2 toilet, bếp, sân để xe, ban công, tất cả đều đẹp, nội thất hài hòa, màu sắc trang nhã.<br />- Công trình được thiết kế móng đổ vỉ đà kiềng, sắt thép Việt-Nhật, xi măng Fico, gạch xây Tuynel.<br />- Nên chất lượng nhà rất tốt.<br />- Đường rộng 5m, bê tông, thông thoáng, sạch sẽ, xe hơi vào tận nhà.<br />- Xung quanh là khu vui chơi giải trí, mua sắm, chợ và công viên.<br />- Giao thông thuận tiện.<br />- Chỉ mất 5 phút tới KCN Tân Bình.<br />- Thanh toán dễ dàng, thủ tuc nhanh – gọn – lẹ.";
		tvDes.setText(Html.fromHtml(htl));
		String aaa = "- Công viên<br /> - Trường học<br /> - Khu vui chơi trẻ em<br /> - Nhà hàng<br /> - Chuỗi cửa hàng tiện lợi<br /> - Hầm đậu xe<br /> - Nhà trẻ...";

		tvTienich.setText(Html.fromHtml(aaa));

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
		md.setStateName("Cửa hàng đồ ăn");
		md.setSort("12 cửa hàng gần nhất");
		md.setStateID("");
		list.add(md);

		GanAdapter adapter = new GanAdapter(this, list, null);
		lvGan.setAdapter(adapter);
		// HotdealUtilities.setHeightHardCode(lvGan, 1000);
		HotdealUtilities.setListViewHeightBasedOnChildren(lvGan);
	}

	@Override
	public void onClick(View v) {
		if (rlToogle == v) {
			HotdealUtilities.setClickAnim(rlToogle);
			this.finish();
		}

	}
}
