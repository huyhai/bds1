package com.vreal.adapter;

import java.util.ArrayList;

import com.android.vrealapp.R;
import com.vreal.libs.VrealUtilities;
import com.vreal.libs.TouchImageView;

import android.app.Activity;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;


public class ViewImagesAdapter extends PagerAdapter {

	static class ViewHolder {
	}

	@SuppressWarnings("unused")
	private ArrayList<String> listBanner = new ArrayList<String>();
	private LayoutInflater inflater;
	private TouchImageView imgBanner;
	private Activity act;

	public ViewImagesAdapter(final Activity _act, ArrayList<String> _listBanner) {
		this.listBanner = _listBanner;
		this.inflater = _act.getLayoutInflater();
		act = _act;
	}

	public ViewImagesAdapter() {
	}

	public void setData(ArrayList<String> _listBanner) {
		this.listBanner = _listBanner;
	}

	@Override
	public void destroyItem(final View container, final int position, final Object object) {
		((ViewPager) container).removeView((View) object);
	}

	@Override
	public void finishUpdate(final View container) {
	}

	@Override
	public int getCount() {
		return this.listBanner.size();
	}

	@Override
	public Object instantiateItem(final View view, final int position) {

		final View imageLayout = this.inflater.inflate(R.layout.view_image_item, null);
		imgBanner = (TouchImageView) imageLayout.findViewById(R.id.imgBanner);
		((ViewPager) view).addView(imageLayout, 0);
		VrealUtilities.loadImage(listBanner.get(position),imgBanner, act);
//		Picasso.with(act).load(listBanner.get(position)).placeholder(R.drawable.img_thumb)
//				.error(R.drawable.noimage).into(imgBanner);
		return imageLayout;
	}

	@Override
	public boolean isViewFromObject(final View view, final Object object) {
		return view.equals(object);
	}

	@Override
	public void restoreState(final Parcelable state, final ClassLoader loader) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(final View container) {
	}

	public void recycleImg() {
		// Utilities.recycle(imgBanner);
	}

}
