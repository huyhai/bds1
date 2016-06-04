package com.vrealvn.vrealapp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.vrealapp.R;
import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.vreal.libs.ConstantValue;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.SessionManager;
import com.vreal.ui2.PostPro;

public class HotDealFragmentActivity extends FragmentActivity {
	public LinearLayout llTopBar;
	public ImageView imgTooggle;
	public SessionManager sm;

	@Override
	protected void onCreate(@Nullable Bundle arg0) {
		super.onCreate(arg0);
		sm=new SessionManager(this);
		// HotdealApp.context = HotDealFragmentActivity.this;

	}

	private void doGcmRegistration() {
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		HotdealApp.regId = GCMRegistrar.getRegistrationId(this);
		HotdealUtilities.showALog("regId = " + HotdealApp.regId);
		// GCMRegistrar.unregister(getApplicationContext());
		if (HotdealApp.regId.equals("")) {
			GCMRegistrar.register(getApplicationContext(), GCMIntentService.SENDER_ID);
		}
	}

	public void setMenuSlideContent() {
		if (!isFinishing()) {
			FragmentTransaction fragmentTS = getSupportFragmentManager().beginTransaction();
			// fragmentTS.replace(R.id.flHome, mFragment, "-1");
			try {
				fragmentTS.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public class DiskBitmapCache extends DiskBasedCache implements ImageCache {

		public DiskBitmapCache(File rootDirectory, int maxCacheSizeInBytes) {
			super(rootDirectory, maxCacheSizeInBytes);
		}

		public DiskBitmapCache(File cacheDir) {
			super(cacheDir);
		}

		public Bitmap getBitmap(String url) {
			final Entry requestedItem = get(url);

			if (requestedItem == null)
				return null;

			return BitmapFactory.decodeByteArray(requestedItem.data, 0, requestedItem.data.length);
		}

		public void putBitmap(String url, Bitmap bitmap) {

			final Entry entry = new Entry();

			/*
			 * //Down size the bitmap.If not done, OutofMemoryError occurs while
			 * decoding large bitmaps. // If w & h is set during image request (
			 * using ImageLoader ) then this is not required.
			 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); Bitmap
			 * downSized = BitmapUtil.downSizeBitmap(bitmap, 50);
			 * 
			 * downSized.compress(Bitmap.CompressFormat.JPEG, 100, baos); byte[]
			 * data = baos.toByteArray();
			 * 
			 * System.out.println("####### Size of bitmap is ######### "+url+" : "
			 * +data.length); entry.data = data ;
			 */

			entry.data = convertBitmapToBytes(bitmap);
			put(url, entry);
		}
	}

	public class BitmapCache extends LruCache<String, Bitmap> implements ImageCache {
		public BitmapCache(int maxSize) {
			super(maxSize);
		}

		@Override
		public Bitmap getBitmap(String url) {
			return (Bitmap) get(url);
		}

		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			put(url, bitmap);
		}
	}

	@SuppressLint("NewApi")
	public static byte[] convertBitmapToBytes(Bitmap bitmap) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			ByteBuffer buffer = ByteBuffer.allocate(bitmap.getByteCount());
			bitmap.copyPixelsToBuffer(buffer);
			return buffer.array();
		} else {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] data = baos.toByteArray();
			return data;
		}
	}

	public void backToFragment() {
		// listFragment.remove(listFragment.size() - 1);
		// FragmentTransaction fragmentTS = this.getSupportFragmentManager()
		// .beginTransaction();
		// fragmentStack.lastElement().onPause();
		// fragmentTS.remove(fragmentStack.pop());
		// fragmentStack.lastElement().onResume();
		// fragmentTS.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		// // fragmentTS.add(R.id.frMain, fragmentStack.lastElement(),
		// // fragmentStack.lastElement().getTag());
		// fragmentTS.show(fragmentStack.lastElement());
		// fragmentTS.commit();
	}

	public void returnFragment() {
		// FragmentTransaction fragmentTS = this.getSupportFragmentManager()
		// .beginTransaction();
		// // fragmentStack.lastElement().onPause();
		// // fragmentTS.remove(fragmentStack.pop());
		// // fragmentStack.lastElement().onc
		// fragmentTS.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		// fragmentTS.show(fragmentStack.lastElement());
		// fragmentTS.commit();
	}

	public void startFragment(Fragment fragment, String dataString) {
		String backStateName="";
		backStateName = fragment.getClass().getName();

		FragmentManager manager = getSupportFragmentManager();
		boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

		if (!fragmentPopped) { // fragment not in back stack, create it.
			FragmentTransaction ft = manager.beginTransaction();
			ft.replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.addToBackStack(backStateName);
			Bundle bundle = new Bundle();
			bundle.putString(ConstantValue.DATA_FRAGMENT, dataString);
			fragment.setArguments(bundle);
			ft.commit();
		}
	}

	public void startSlide(Fragment fragment) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		ft.replace(R.id.left_drawerV2, fragment);
		ft.commit();
	}

	@Override
	public void onBackPressed() {
		if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
			finish();
		} else {
			super.onBackPressed();
		}
	}

	public void destroyFragment() {
		try {
			// fragmentStack.lastElement().onDestroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resetListFragment() {
		// try {
		// fragmentStack.lastElement().onPause();
		// } catch (Exception e) {
		// // e.printStackTrace();
		// }
		//
		// fragmentStack.clear();
	}

	public int getCurrentFragment() {
		return 0;
		// return listFragment.get(listFragment.size() - 1);
	}

	public void sendMessage(Context cont, String Action, String idCate) {
		Intent intent = new Intent(Action);
		intent.putExtra(ConstantValue.IS_SUCCESS, idCate);
		cont.sendBroadcast(intent);
	}


}
