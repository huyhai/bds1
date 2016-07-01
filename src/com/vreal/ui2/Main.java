package com.vreal.ui2;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.vrealapp.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.vreal.libs.ConstantValue;
import com.vreal.libs.VrealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.VrealFragmentActivity;

public class Main extends VrealFragmentActivity implements OnClickListener {
	public static ImageLoader imageLoader;
	public static DisplayImageOptions options;
	private LinearLayout llTopBar;
	private LinearLayout rlToogle;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private FrameLayout left_drawerV2;
	private boolean isOpenDrawer;
	private static TextView imgLogo;
	private ImageView imgTooggle;
	private ImageView imgLocation;
	private ImageView imgRefresh;
	private ImageView imgFilter;

	@Override
	protected void onCreate(@Nullable Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_main_v2);
		init();
		initView();
		startFragment(new HomeF(), "");
		startSlide(new MenuSlide());
	}

	private void initView() {
		llTopBar = (LinearLayout) findViewById(R.id.llTopBar);
		rlToogle = (LinearLayout) findViewById(R.id.rlToogle);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		left_drawerV2 = (FrameLayout) findViewById(R.id.left_drawerV2);
		imgLogo = (TextView) findViewById(R.id.imgLogo);

		imgTooggle = (ImageView) findViewById(R.id.imgTooggle);
		imgLocation = (ImageView) findViewById(R.id.imgLocation);
		imgRefresh = (ImageView) findViewById(R.id.imgRefresh);
		imgFilter = (ImageView) findViewById(R.id.imgFilter);
		VrealUtilities.setWidthHeight(imgTooggle, 16, 37);
		VrealUtilities.setWidthHeight(imgLocation, 17, 24);
		VrealUtilities.setWidthHeight(imgRefresh, 12.5, 25);
		VrealUtilities.setWidthHeight(imgFilter, 15, 32);

		VrealUtilities.setHeight(llTopBar, 11.5);

		rlToogle.setOnClickListener(this);

		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_launcher, /* nav drawer image to replace 'Up' caret */
		R.string.app_name, /* "open drawer" description for accessibility */
		R.string.app_name /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				isOpenDrawer = false;
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				isOpenDrawer = true;
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		DataManager2.getInstance().showProgress(this);
		if (DataManager2.getInstance().getListProvices().size() == 0) {
			if (sm.getProviceJson().equals("")) {
				getProvice();
			} else {
				JSONArray job = null;
				try {
					job = new JSONArray(sm.getProviceJson());
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				DataManager2.getInstance().handleProvice(job, notifyData);
			}
		}

	}
	NotifyDataListener notifyData = new NotifyDataListener() {

		@Override
		public void onNotify(String api, int id) {
			if (api.equals(ConstantValue.GET_CITY)) {
				if (NotifyDataListener.NOTIFY_OK == id) {
					if (sm.getDisJson().equals("")) {
						getDistric();
					} else {
						JSONArray job = null;
						try {
							job = new JSONArray(sm.getDisJson());
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						DataManager2.getInstance().handleDistrict(job, notifyData);
					}
				} else {

				}
			} else if (api.equals(ConstantValue.GET_DIS)) {
				if (NotifyDataListener.NOTIFY_OK == id) {
					if (sm.getWardJson().equals("")) {
						getWard();
					} else {
						JSONArray job = null;
						try {
							job = new JSONArray(sm.getWardJson());
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						DataManager2.getInstance().handleWard(job, notifyData);
					}
				} else {

				}
			} else if (api.equals(ConstantValue.GET_WARD)) {
				if (NotifyDataListener.NOTIFY_OK == id) {
					if (sm.getStreetJson().equals("")) {
						getStreet();
					} else {
						JSONArray job = null;
						try {
							job = new JSONArray(sm.getStreetJson());
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						DataManager2.getInstance().handleStreet(job, notifyData);
					}
				} else {

				}
			} else if (api.equals(ConstantValue.GET_STREET)) {
				if (NotifyDataListener.NOTIFY_OK == id) {
					if (sm.getKhuVucJson().equals("")) {
						getKhuvuc();
					} else {
						JSONArray job = null;
						try {
							job = new JSONArray(sm.getKhuVucJson());
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						DataManager2.getInstance().handleKV(job, notifyData);
					}
				} else {

				}

			} else if (api.equals(ConstantValue.GET_KHUVUC)) {
				getHuong();

			} else if (api.equals(ConstantValue.GET_HUONG)) {
				getDuan();
			} else if (api.equals(ConstantValue.GET_DUAN)) {
				getGia();
			} else if (api.equals(ConstantValue.GET_GIA)) {
				getDienTich();
			} else if (api.equals(ConstantValue.GET_DIENTICH)) {

				DataManager2.getInstance().stopProgress();
			}
			// else if (api.equals(ConstantValue.GET_DIS)) {
			//
			// }

//			DataManager2.getInstance().stopProgress();
		}

	};
	private void getDienTich() {
		DataManager2.getInstance().getDienTich(this, false, false, notifyData);
	}

	private void getGia() {
		DataManager2.getInstance().getGia(this, false, false, notifyData);
	}

	private void getProvice() {
		DataManager2.getInstance().getProvice(this, false, false, notifyData);
	}

	private void getDistric() {
		DataManager2.getInstance().getDistrict(this, false, false, notifyData, "");
	}

	private void getWard() {
		DataManager2.getInstance().getWard(this, false, false, notifyData);
	}

	private void getStreet() {
		DataManager2.getInstance().getStreet(this, false, false, notifyData);
	}

	private void getKhuvuc() {
		DataManager2.getInstance().getKhuvuc(this, false, false, notifyData);
	}

	private void getHuong() {
		DataManager2.getInstance().getHuong(this, false, false, notifyData);
	}

	private void getDuan() {
		DataManager2.getInstance().getDuan(this, false, false, notifyData);
	}
	private void init() {
		VrealUtilities.setWH(this);

		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
		// options = new DisplayImageOptions.Builder()
		// .showImageForEmptyUrl(placeholder)
		// .showStubImage(placeholder).cacheOnDisc()
		// .decodingType(DecodingType.MEMORY_SAVING).build();
		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.noimage).showImageForEmptyUri(R.drawable.noimage).showImageOnFail(R.drawable.noimage).cacheInMemory(true)
				.cacheOnDisc().considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
		// startService(new Intent(this, MyService.class));

	}

	@Override
	public void onClick(View v) {
		if (v == rlToogle) {
			if (isOpenDrawer) {
				closeMenu();
			} else {
				openMenu();
			}

		}

	}

	public static void setTextTop(String text) {
		try {
			imgLogo.setText(text);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void openMenu() {
		mDrawerLayout.openDrawer(left_drawerV2);
	}

	public void closeMenu() {
		mDrawerLayout.closeDrawer(left_drawerV2);

	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		Fragment fragment = (Fragment) getSupportFragmentManager().findFragmentByTag(new PostPro().getClass().getSimpleName());
		if (fragment != null) {
			fragment.onActivityResult(arg0, arg1, arg2);
		}
	}
}
