package com.vreal.ui2;

import java.util.StringTokenizer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hotdealvn.hotdealapp.DataManager2;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifySomesDataListener;
import com.vrealvn.vrealapp.HotDealFragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import com.android.vrealapp.R;

public class Main extends HotDealFragmentActivity implements OnClickListener {
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
		HotdealUtilities.setWidthHeight(imgTooggle, 12, 26);
		HotdealUtilities.setWidthHeight(imgLocation, 16, 22);
		HotdealUtilities.setWidthHeight(imgRefresh, 12.5, 22);
		HotdealUtilities.setWidthHeight(imgFilter, 12.5, 22);

		HotdealUtilities.setHeight(llTopBar, 11.5);

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

	}

	private void init() {
		HotdealUtilities.setWH(this);

		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
		// options = new DisplayImageOptions.Builder()
		// .showImageForEmptyUrl(placeholder)
		// .showStubImage(placeholder).cacheOnDisc()
		// .decodingType(DecodingType.MEMORY_SAVING).build();
		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.img_thumb).showImageForEmptyUri(R.drawable.img_thumb).showImageOnFail(R.drawable.img_thumb).cacheInMemory(true)
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if (requestCode == 1) {
//			if (resultCode == Activity.RESULT_OK) {
//				String pos1 = data.getStringExtra("result");
//			}
//		}
	}
}
