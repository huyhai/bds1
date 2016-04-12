package com.hotdealapp.ui2;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.vrealapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hotdeal.libs.HotdealUtilities;
import com.hotdeal.libs.MapLibs;
import com.hotdeal.model.DiaDiemModel;

public class MapF extends MapLibs implements OnMapReadyCallback {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.map, container, false);
		initView(rootView);
		return rootView;
	}

	private void initView(View rootView) {
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

}
