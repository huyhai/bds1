package com.vreal.ui2;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.vreal.adapter.TienIchAdapter;
import com.vreal.libs.MapLibs;
import com.vreal.libs.NotifyDataListener;
import com.vreal.libs.VrealUtilities;
import com.vreal.model.VrealModel;
import com.vrealvn.vrealapp.DataManager2;

public class MapF extends MapLibs implements OnMapReadyCallback, OnClickListener {
	// private LinearLayout llDistance;
	private TextView tvD;
	private RelativeLayout rlTienICh;
	private RelativeLayout rlTop;
	private TextView tvEX;
	int pos;
	private TextView tvKm1;
	private TextView tvKm2;
	private TextView tvKm3;
	private TextView tvKm4;
	private TextView tvKm5;
	private GridView gvTienich;

	public MapF(int pos2) {
		pos = pos2;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.map, container, false);
		initView(rootView);
		return rootView;
	}

	/*
	 * @Override protected void onCreate(@Nullable Bundle arg0) { // TODO
	 * Auto-generated method stub super.onCreate(arg0);
	 * setContentView(R.layout.map); initView(); }
	 */

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// releaseMap();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		releaseMap();
	}

	public void releaseMap() {
		try {
			FragmentManager manager = this.getFragmentManager();
			FragmentTransaction trans = manager.beginTransaction();
			SupportMapFragment frag = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.location_map));
			trans.remove(frag);
			trans.commitAllowingStateLoss();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void initView(View v) {
//		pos = Integer.parseInt(HotdealUtilities.getDataFragment(this));
		// llDistance = (LinearLayout) rootView.findViewById(R.id.llDistance);
		rlTienICh = (RelativeLayout) v.findViewById(R.id.rlTienICh);
		rlTop = (RelativeLayout) v.findViewById(R.id.rlTop);
		tvD = (TextView) v.findViewById(R.id.tvD);
		tvEX = (TextView) v.findViewById(R.id.tvEX);
		tvKm1 = (TextView) v.findViewById(R.id.tvKm1);
		tvKm2 = (TextView) v.findViewById(R.id.tvKm2);
		tvKm3 = (TextView) v.findViewById(R.id.tvKm3);
		tvKm4 = (TextView) v.findViewById(R.id.tvKm4);
		tvKm5 = (TextView) v.findViewById(R.id.tvKm5);
		gvTienich = (GridView) v.findViewById(R.id.gvTienich);
		rlTop.setOnClickListener(this);
		tvEX.setOnClickListener(this);
		tvD.setOnClickListener(this);
		tvKm1.setOnClickListener(this);
		tvKm2.setOnClickListener(this);
		tvKm3.setOnClickListener(this);
		tvKm4.setOnClickListener(this);
		tvKm5.setOnClickListener(this);

		VrealUtilities.setHeight(rlTienICh, 2.5);

		// for (int i = 0; i < 6; i++) {
		// TextView tv = new TextView(getActivity());
		// tv.setText(i + " km");
		// tv.setPadding(5, 5, 5, 5);
		// tv.setTextSize(tvD.getTextSize());
		// llDistance.addView(tv);
		// }
		// GoogleMap mMap = ((SupportMapFragment)
		// getFragmentManager().findFragmentById(R.id.location_map)).getMap();
		// MapFragment mapFragment = (MapFragment)
		// getActivity().getFragmentManager().findFragmentById(R.id.location_map);
		SupportMapFragment m = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.location_map));
		m.getMapAsync(this);
	}

	private void getTienich() {
		DataManager2.getInstance().getTienich(getActivity(), true, false, new NotifyDataListener() {

			@Override
			public void onNotify(String api, int id) {
				TienIchAdapter adapter = new TienIchAdapter(getActivity(), DataManager2.getInstance().getListTienich(), null, false);
				gvTienich.setAdapter(adapter);

			}
		});
	}

	@Override
	public void onMapReady(GoogleMap arg0) {
		arg0.getUiSettings().setMyLocationButtonEnabled(true);
		getTienich();
		if (pos == -1) {
			setMultiMarkDiaDiem(DataManager2.getInstance().getListSearch(), arg0);
		} else {
			ArrayList<VrealModel> list = new ArrayList<>();
			VrealModel vmd = DataManager2.getInstance().getVrealModel();
			list.add(vmd);
			setMultiMarkDiaDiem(list, arg0);
		}

		// LatLng latlng = HotdealUtilities.getCurrentLocation(getActivity());
		// arg0.addMarker(new MarkerOptions().position(latlng).title("SG"));
		// moveToMaker(latlng, arg0);
		// ArrayList<DiaDiemModel> list = new ArrayList<>();
		// DiaDiemModel md;
		//
		// md = new DiaDiemModel();
		// md.setAddress(vmd.getProvinceName());
		// md.setClick(true);
		// md.setLatt(vmd.getLatitude());
		// md.setLont(vmd.getLongitude());
		// list.add(md);

		// md = new DiaDiemModel();
		// md.setAddress("bbbbbbbb");
		// md.setClick(true);
		// md.setLatt(10.7790893);
		// md.setLont(106.6906343);
		// list.add(md);
		// md = new DiaDiemModel();
		// md.setAddress("ccccc");
		// md.setClick(true);
		// md.setLatt(10.7799764);
		// md.setLont(106.6868654);
		// list.add(md);
		// md = new DiaDiemModel();
		// md.setAddress("ddddd");
		// md.setClick(true);
		// md.setLatt(10.7789463);
		// md.setLont(106.6804863);
		// list.add(md);

		// Circle circle = arg0.addCircle(new CircleOptions().center(new
		// LatLng(md.getLatt(),
		// md.getLont())).radius(1500).strokeColor(R.color.vreal_orange).strokeWidth(2).fillColor(Color.TRANSPARENT));

	}

	Animation bottomUp;

	@Override
	public void onClick(View v) {
		if (v == rlTop) {
			bottomUp = AnimationUtils.loadAnimation(getActivity(), R.animator.slid_up);
			rlTienICh.startAnimation(bottomUp);
			rlTienICh.setVisibility(View.VISIBLE);

		} else if (tvD == v) {
			rlTienICh.setVisibility(View.GONE);

		} else if (tvEX == v) {
			rlTienICh.setVisibility(View.GONE);
		} else if (tvKm1 == v) {

		} else if (tvKm2 == v) {

		} else if (tvKm3 == v) {
		} else if (tvKm4 == v) {
		} else if (tvKm5 == v) {
		}

	}

}
