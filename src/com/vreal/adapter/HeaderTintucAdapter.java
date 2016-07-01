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
import android.widget.TextView;

import com.android.vrealapp.R;
import com.vreal.libs.VrealUtilities;
import com.vreal.model.DetailsModel;
import com.vreal.model.VrealModel;
import com.vreal.pageindicator.IconPagerAdapter;
import com.vreal.ui2.NewsDetailF;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.VrealFragmentActivity;

public class HeaderTintucAdapter extends PagerAdapter implements IconPagerAdapter {

	static class ViewHolder {
	}

	private ArrayList<DetailsModel> listBanner;
	private LayoutInflater inflater;
	private ImageView imgBanner;
	private Activity ac;
	private TextView tv1;
	private TextView tv12;

	public HeaderTintucAdapter(final Activity _act, ArrayList<DetailsModel> _listBanner) {
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

		final View imageLayout = this.inflater.inflate(R.layout.header_tintuc_viewpage, null);
		imgBanner = (ImageView) imageLayout.findViewById(R.id.imgBanner);
		tv1 = (TextView) imageLayout.findViewById(R.id.tv1);
		tv12 = (TextView) imageLayout.findViewById(R.id.tv12);
		((ViewPager) view).addView(imageLayout, 0);
		final DetailsModel md = listBanner.get(position);
		VrealUtilities.loadImage(md.getImage(), imgBanner, ac);
		tv1.setText(md.getIntroduce());
		tv12.setText(md.getName());
		// Picasso.with(ac).load(listBanner.get(position)).placeholder(R.drawable.img_thumb)
		// .error(R.drawable.noimage).into(imgBanner);
		imageLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent i=new Intent(ac, ViewImageActivity.class);
				// ac.startActivity(i);
				DataManager2.getInstance().setMd(md);
				VrealUtilities.startActivity(ac, NewsDetailF.class, "");
//				((HotDealFragmentActivity) ac).startFragment(new NewsDetailF(), "");

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
