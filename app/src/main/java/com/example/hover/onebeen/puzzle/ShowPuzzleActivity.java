package com.example.hover.onebeen.puzzle;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.dto.Puzzle;

public class ShowPuzzleActivity extends FragmentActivity {
	private ViewPager mPager;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_puzzle);

//		Intent intent = getIntent();
//		Puzzle puzzle = (Puzzle) intent.getSerializableExtra("puzzle");

		Puzzle puzzle = new Puzzle();

		mPager = (ViewPager)findViewById(R.id.pager);
		mPager.setAdapter(new PagerAdapterClass(getApplicationContext(), puzzle));
	}

	private void setCurrentInflateItem(int type){
		if(type==0){
			mPager.setCurrentItem(0);
		}else if(type==1){
			mPager.setCurrentItem(1);
		}else{
			mPager.setCurrentItem(2);
		}
	}

	private View.OnClickListener mPagerListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String text = ((Button)v).getText().toString();
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
		}
	};

	/**
	 * PagerAdapter
	 */
	private class PagerAdapterClass extends PagerAdapter {

		private LayoutInflater mInflater;
		private Puzzle puzzle;

		public PagerAdapterClass(Context c, Puzzle puzzle){
			super();
			mInflater = LayoutInflater.from(c);
			this.puzzle = puzzle;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public Object instantiateItem(View pager, int position) {
			View v = null;
			if(position==0){
				v = mInflater.inflate(R.layout.fragment_show_puzzle, null);
				((TextView) v.findViewById(R.id.puzzle_description)).setText("1번");
//				((ImageView) v.findViewById(R.id.puzzle_image)).setImageResource(R.drawable.first);
//				((ImageView) v.findViewById(R.id.puzzle_image)).setImageResource(R.drawable.dsc05792);

				((ImageView) v.findViewById(R.id.page_control1)).setImageResource(R.drawable.img_control_selected);
			}
			else if(position==1){
				v = mInflater.inflate(R.layout.fragment_show_puzzle, null);
				((TextView) v.findViewById(R.id.puzzle_description)).setText("2번");
				((ImageView) v.findViewById(R.id.page_control2)).setImageResource(R.drawable.img_control_selected);
			}else{
				v = mInflater.inflate(R.layout.fragment_show_puzzle, null);
				((TextView) v.findViewById(R.id.puzzle_description)).setText("3번");
				((ImageView) v.findViewById(R.id.page_control3)).setImageResource(R.drawable.img_control_selected);
			}

			((ViewPager)pager).addView(v, 0);
			return v;
		}

		@Override
		public void destroyItem(View pager, int position, Object view) {
			((ViewPager)pager).removeView((View)view);
		}

		@Override
		public boolean isViewFromObject(View pager, Object obj) {
			return pager == obj;
		}

		@Override public void restoreState(Parcelable arg0, ClassLoader arg1) {}
		@Override public Parcelable saveState() { return null; }
		@Override public void startUpdate(View arg0) {}
		@Override public void finishUpdate(View arg0) {}
	}
}
