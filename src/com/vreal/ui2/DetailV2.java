package com.vreal.ui2;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.vreal.adapter.DetailsBannerAdapter;
import com.vreal.adapter.TienIchAdapter;
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
//	int pos;
	private TextView tvName;
	private TextView tvAddress;
	private TextView tvGiaVl;
	private TextView tvDTVl;

	private TextView tvDes;

	private TextView tvFong;
	private TextView tvPhone;
	private TextView tvCellPhone;
	private TextView tvLoai;
	private TextView tvDateStart;
	private TextView tvDateEnd;

	 private TextView tvAddress1;
	 private GridView gvTienich;

//	public DetailV2(int string) {
//		pos = string;
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.detailv2, container, false);
		initView(rootView);
		initData();
		return rootView;
	}

	private void initData() {
		VrealModel md = DataManager2.getInstance().getVrealModel();
		DetailsBannerAdapter adapter = new DetailsBannerAdapter(getActivity(), md.getListPhoto());
		pageSlide.setAdapter(adapter);
		mIndicator.setViewPager(pageSlide);
		pageSlide.setPageTransformer(true, new DepthPageTransformer());

		tvName.setText(md.getProvinceName());
		tvAddress.setText(md.getAddress());
		tvGiaVl.setText(md.getPrice()+" "+md.getUnitName());
		tvDTVl.setText(HotdealUtilities.formatDientich(md.getAcreage()));
		tvDes.setText(Html.fromHtml(md.getDescription()));
		tvFong.setText(md.getContactName());
		tvPhone.setText(md.getContacPhone());
		tvCellPhone.setText(md.getContactEmail());
		if(md.getRealNewsTypeID()==1){
			tvLoai.setText("Nhà đất bán");
		}else{
			tvLoai.setText("Nhà đất cho thuê");
		}
		
		tvDateStart.setText("Ngày đăng: "+md.getPublishStart());
		tvDateEnd.setText("Ngày hết hạn: "+md.getPublishEnd());
		tvAddress1.setText("Địa chỉ: "+md.getAddress());
		ArrayList<VrealModel> listTI=new ArrayList<>();
		for(String a:md.getListTienIch()){
			listTI.add(new VrealModel(a));
		}
		TienIchAdapter adapter1=new TienIchAdapter(getActivity(), listTI, null,true);
		gvTienich.setAdapter(adapter1);
		// tvName.setText(md.getProvinceName());
		// tvName.setText(md.getProvinceName());
		// tvName.setText(md.getProvinceName());

	}

	private void initView(View rootView) {
		tvAddress1= (TextView) rootView.findViewById(R.id.tvAddress1);
		tvLoai= (TextView) rootView.findViewById(R.id.tvLoai);
		tvDateStart= (TextView) rootView.findViewById(R.id.tvDateStart);
		tvDateEnd= (TextView) rootView.findViewById(R.id.tvDateEnd);
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
		gvTienich = (GridView) rootView.findViewById(R.id.gvTienich);
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
