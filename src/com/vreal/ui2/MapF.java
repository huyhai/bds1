package com.vreal.ui2;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.MapLibs;
import com.vreal.model.DiaDiemModel;

public class MapF extends MapLibs implements OnMapReadyCallback, OnClickListener {
	private LinearLayout llDistance;
	private TextView tvD;
	private RelativeLayout rlTienICh;
	private RelativeLayout rlTop;
	private TextView tvEX;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.map, container, false);
		initView(rootView);
		return rootView;
	}

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

	private void initView(View rootView) {
		llDistance = (LinearLayout) rootView.findViewById(R.id.llDistance);
		rlTienICh = (RelativeLayout) rootView.findViewById(R.id.rlTienICh);
		rlTop = (RelativeLayout) rootView.findViewById(R.id.rlTop);
		tvD = (TextView) rootView.findViewById(R.id.tvD);
		tvEX = (TextView) rootView.findViewById(R.id.tvEX);
		rlTop.setOnClickListener(this);
		tvEX.setOnClickListener(this);
		tvD.setOnClickListener(this);

		HotdealUtilities.setHeight(rlTienICh, 2.5);

		for (int i = 0; i < 6; i++) {
			TextView tv = new TextView(getActivity());
			tv.setText(i + " km");
			tv.setPadding(5, 5, 5, 5);
			tv.setTextSize(tvD.getTextSize());
			llDistance.addView(tv);
		}
		// GoogleMap mMap = ((SupportMapFragment)
		// getFragmentManager().findFragmentById(R.id.location_map)).getMap();
		// MapFragment mapFragment = (MapFragment)
		// getActivity().getFragmentManager().findFragmentById(R.id.location_map);
		SupportMapFragment m = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.location_map));
		m.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap arg0) {
		LatLng latlng = HotdealUtilities.getCurrentLocation(getActivity());
		arg0.addMarker(new MarkerOptions().position(latlng).title("SG"));
		// moveToMaker(latlng, arg0);
		ArrayList<DiaDiemModel> list = new ArrayList<>();
		DiaDiemModel md;

		md = new DiaDiemModel();
		md.setAddress("aaaaaaaaa");
		md.setClick(true);
		md.setLatt(10.7719493);
		md.setLont(106.6792773);
		list.add(md);
		md = new DiaDiemModel();
		md.setAddress("bbbbbbbb");
		md.setClick(true);
		md.setLatt(10.7790893);
		md.setLont(106.6906343);
		list.add(md);
		md = new DiaDiemModel();
		md.setAddress("ccccc");
		md.setClick(true);
		md.setLatt(10.7799764);
		md.setLont(106.6868654);
		list.add(md);
		md = new DiaDiemModel();
		md.setAddress("ddddd");
		md.setClick(true);
		md.setLatt(10.7789463);
		md.setLont(106.6804863);
		list.add(md);

		setMultiMarkDiaDiem(list, arg0);
		Circle circle = arg0.addCircle(new CircleOptions().center(new LatLng(md.getLatt(), md.getLont())).radius(1500).strokeColor(R.color.vreal_orange).strokeWidth(2).fillColor(Color.TRANSPARENT));

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
		}

	}

}
