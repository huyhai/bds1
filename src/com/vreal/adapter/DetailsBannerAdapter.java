package com.vreal.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.android.vrealapp.R;
import com.vreal.libs.HotdealUtilities;
import com.vreal.pageindicator.IconPagerAdapter;

public class DetailsBannerAdapter extends PagerAdapter implements IconPagerAdapter{

	static class ViewHolder {
	}

	private ArrayList<String> listBanner;
	private LayoutInflater inflater;
	private ImageView imgBanner;
	private Activity ac;

	public DetailsBannerAdapter(final Activity _act, ArrayList<String> _listBanner) {
		this.listBanner = _listBanner;
		this.inflater = _act.getLayoutInflater();
		ac = _act;
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

		final View imageLayout = this.inflater.inflate(R.layout.details_item_banner_layout, null);
		imgBanner = (ImageView) imageLayout.findViewById(R.id.imgBanner);
		((ViewPager) view).addView(imageLayout, 0);
		HotdealUtilities.loadImage(listBanner.get(position),imgBanner, ac);
//		Picasso.with(ac).load(listBanner.get(position)).placeholder(R.drawable.img_thumb)
//				.error(R.drawable.noimage).into(imgBanner);
		imageLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent i=new Intent(ac, ViewImageActivity.class);
//				ac.startActivity(i);
				
			}
		});
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

	@Override
	public int getIconResId(int index) {
		// TODO Auto-generated method stub
		return 0;
	}


}
