package com.vreal.ui2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.vreal.adapter.DetailsBannerAdapter;
import com.vreal.libs.DepthPageTransformer;
import com.vreal.libs.HotdealUtilities;
import com.vreal.model.VrealModel;
import com.vreal.pageindicator.CirclePageIndicator;
import com.vreal.pageindicator.PageIndicator;
import com.vrealvn.vrealapp.DataManager2;

public class DetailV2 extends Fragment {
	PageIndicator mIndicator;
	private ViewPager pageSlide;
	private RelativeLayout rlSlide;
	int pos;
	private TextView tvName;
	private TextView tvAddress;
	private TextView tvGiaVl;
	private TextView tvDTVl;

	private TextView tvDes;

	 private TextView tvFong;
	 private TextView tvPhone;
	 private TextView tvCellPhone;
	// private TextView tvName;
	// private TextView tvName;

	public DetailV2(int string) {
		pos = string;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.detailv2, container, false);
		initView(rootView);
		initData();
		return rootView;
	}

	private void initData() {
		VrealModel md = DataManager2.getInstance().getListSearch().get(pos);
		DetailsBannerAdapter adapter = new DetailsBannerAdapter(getActivity(), md.getListPhoto());
		pageSlide.setAdapter(adapter);
		mIndicator.setViewPager(pageSlide);
		pageSlide.setPageTransformer(true, new DepthPageTransformer());

		tvName.setText(md.getProvinceName());
		tvAddress.setText(md.getAddress());
		tvGiaVl.setText(HotdealUtilities.formatMoney(md.getPrice()));
		tvDTVl.setText(HotdealUtilities.formatDientich(md.getAcreage()));
		tvDes.setText(Html.fromHtml(md.getDescription()));
		tvFong.setText(md.getContactName());
		tvPhone.setText(md.getContacPhone());
		tvCellPhone.setText(md.getContactEmail());
		// tvName.setText(md.getProvinceName());
		// tvName.setText(md.getProvinceName());
		// tvName.setText(md.getProvinceName());
		// tvName.setText(md.getProvinceName());

	}

	private void initView(View rootView) {
		pageSlide = (ViewPager) rootView.findViewById(R.id.pageSlide);
		mIndicator = (CirclePageIndicator) rootView.findViewById(R.id.indicator);
		rlSlide = (RelativeLayout) rootView.findViewById(R.id.rlSlide);
		tvName = (TextView) rootView.findViewById(R.id.tvName);
		tvAddress = (TextView) rootView.findViewById(R.id.tvAddress);
		tvGiaVl = (TextView) rootView.findViewById(R.id.tvGiaVl);
		tvDTVl = (TextView) rootView.findViewById(R.id.tvDTVl);
		tvDes = (TextView) rootView.findViewById(R.id.tvDes);
		tvFong = (TextView) rootView.findViewById(R.id.tvFong);
		tvPhone = (TextView) rootView.findViewById(R.id.tvPhone);
		tvCellPhone = (TextView) rootView.findViewById(R.id.tvCellPhone);
		// tvName = (TextView) rootView.findViewById(R.id.tvName);
		// tvName = (TextView) rootView.findViewById(R.id.tvName);

		HotdealUtilities.setHeight(rlSlide, 3);

		// ArrayList<String> list = new ArrayList<>();
		// list.add("http://img.v3.news.zdn.vn/w660/Uploaded/wyhktpu/2016_04_11/Hai_mau_xe_long_lay_dam_tinh_thoi_trang_cua_Yamaha_1.jpg");
		// list.add("http://img.v3.news.zdn.vn/w660/Uploaded/wyhktpu/2016_04_11/Hai_mau_xe_long_lay_dam_tinh_thoi_trang_cua_Yamaha_6.jpg");
		// list.add("http://img.v3.news.zdn.vn/w660/Uploaded/wyhktpu/2016_04_11/Can_canh_nakedbike_Yamaha_MT03_5.jpg");
		// list.add("http://img.v3.news.zdn.vn/w660/Uploaded/wyhktpu/2016_04_11/Can_canh_nakedbike_Yamaha_MT03_8.jpg");
		// String a=HotdealUtilities.getDataFragment(this);
		// int pos=HotdealUtilities.parseInt(a);

	}

}
