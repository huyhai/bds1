package com.hotdeal.libs;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.android.vrealapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hotdeal.model.DiaDiemModel;

public class MapLibs extends Fragment implements OnInfoWindowClickListener {
	public static LatLng mCurrentPoint;
	public static Boolean isSetMaker = false;
	protected SupportMapFragment mMapFragment;
	protected ArrayList<Marker> listMarker;
	protected Marker mCurrentMarker;
	protected View mVIew;
	private Marker mClickedMarker;
	public static Boolean isFilter = false;
	public String addressLocal = null;
	public LatLng latlogLocal = null;

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// ((LinearLayout) mVIew.findViewById(R.id.mapviewLinearLayout))
		// .removeAllViews();
	}

	LatLng latlogfocus = null;

	public void setMultiMarkDiaDiem(final ArrayList<DiaDiemModel> listAddress, final GoogleMap mMap) {
		LatLng latlog = null;
		mMap.clear();
		final LatLngBounds.Builder builder = new LatLngBounds.Builder();
		for (int i = 0; i < listAddress.size(); i++) {
			try {
				latlog = new LatLng(listAddress.get(i).getLatt(), listAddress.get(i).getLont());
			} catch (Exception e) {
				latlog = new LatLng(0, 0);
			}

			if (listAddress.get(i).isClick()) {
				mClickedMarker = mMap.addMarker(new MarkerOptions().title(listAddress.get(i).getAddress()).position(latlog).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_green_map_point)));
				latlogfocus = latlog;
			} else {
				mClickedMarker = mMap.addMarker(new MarkerOptions().title(listAddress.get(i).getAddress()).position(latlog).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_gray_map_point)));
			}
			// mClickedMarker.showInfoWindow();
			builder.include(latlog);
		}
		mMap.setOnMapLoadedCallback(new OnMapLoadedCallback() {
			@Override
			public void onMapLoaded() {
				// LatLngBounds bounds = builder.build();
				if (listAddress.size() > 1) {
					// mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,
					// 50));
					moveToMaker(latlogfocus, mMap);
				} else {
					try {
						mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(listAddress.get(0).getLatt(), listAddress.get(0).getLont()), 14));
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}
		});

	}

	public void moveToMaker(LatLng pos, GoogleMap m) {
		final CameraPosition cameraPosition = new CameraPosition.Builder().target(pos).zoom(14).bearing(90) // Sets
																											// the
																											// orientation
																											// of
																											// the
																											// camera
																											// to
																											// east
				// .tilt(30) // Sets the tilt of the camera to 30 degrees
				.build();
		m.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub

	}

}