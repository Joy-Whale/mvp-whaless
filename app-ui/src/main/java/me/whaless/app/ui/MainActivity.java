package me.whaless.app.ui;

import android.os.Bundle;
import android.util.SparseArray;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import me.whaless.app.R;

/**
 * Author: Ji
 * Date:   2019/5/5
 */
public class MainActivity extends ParentActivity {

	private static final int CONTENT_ID = R.id.contentPanel;

	@BindView(R.id.tabLayout)
	TabLayout mTabLayout;
	@BindView(R.id.contentPanel)
	ViewPager mViewPager;

	SparseArray<ParentFragment> mFragments = new SparseArray<>();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actiivty_main);

		mFragments.put(0, HomeFragment.newInstance());
		mFragments.put(1, FriendsFragment.newInstance());
		mFragments.put(2, UserFragment.newInstance());

		mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
			@Override
			public Fragment getItem(int position) {
				return mFragments.get(position);
			}

			@Override
			public int getCount() {
				return mFragments.size();
			}
		});
		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
	}

}
