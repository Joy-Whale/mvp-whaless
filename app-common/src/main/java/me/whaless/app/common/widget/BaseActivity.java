package me.whaless.app.common.widget;

import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.whaless.app.common.R;
import me.whaless.app.common.utils.DeviceHelper;
import me.whaless.app.common.utils.FieldUtils;


/**
 * User: Joy
 * Date: 2016-11-13
 * Time: 14:08
 */

public class BaseActivity extends AppCompatActivity {

	protected Toolbar mToolbar;

	private SparseArray<SparseArray<BaseFragment>> mChildFragments;
	private SparseIntArray mChildFragmentCurrentIndex;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 清除缓存中的Fragment
		//		List<Fragment> temF = getSupportFragmentManager().getFragments();
		//		if (temF != null) {
		//			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		//			for (Fragment f : temF) {
		//				transaction.remove(f);
		//			}
		//			transaction.commit();
		//			temF.clear();
		//			temF = null;
		//		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mChildFragments != null) {
			mChildFragments.clear();
			mChildFragments = null;
		}
		if (mChildFragmentCurrentIndex != null) {
			mChildFragmentCurrentIndex.clear();
			mChildFragmentCurrentIndex = null;
		}
	}

	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		super.setContentView(layoutResID);
		if (findViewById(R.id.toolbar) instanceof Toolbar)
			mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (mToolbar != null) {
			setSupportActionBar(mToolbar);
			//			mToolbar.setNavigationIcon(R.drawable.draw_vector_navigate_arrow);
			mToolbar.setNavigationOnClickListener(onCreateNavigationClickListener());
		}
	}

	/**
	 * 生成一个toolbar返回按钮的点击事件
	 */
	protected View.OnClickListener onCreateNavigationClickListener() {
		return v -> finish();
	}

	/**
	 * 隐藏输入框
	 */
	public void hideInput() {
		if (DeviceHelper.isInputDisplaying(getContext())) {
			if (getCurrentFocus() != null && getCurrentFocus() instanceof EditText)
				DeviceHelper.toggleInput(getCurrentFocus(), false);
		}
	}

	/**
	 * 设置toolbar标题
	 * @param title 标题内容
	 */
	protected void setTitle(String title) {
		if (mToolbar != null) {
			mToolbar.setTitle(title);
			getSupportActionBar().setTitle(title);
		}
	}

	protected BaseActivity getContext() {
		return this;
	}

	/**
	 * 替换子fragment
	 * @param contentViewId 子Fragment所在的父FrameLayout
	 * @param fragment      需要替换的fragment
	 */
	protected void replaceChildFragment(int contentViewId, BaseFragment fragment) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(contentViewId, fragment);
		transaction.commitNow();
		fragment.onDisplay();
	}

	/**
	 * 切换子Fragment
	 * @param contentViewId 子Fragment所在的父FrameLayout
	 * @param index         需要显示的子Fragment的下标
	 */
	protected void switchChildFragment(int contentViewId, int index) {
		if (mChildFragments == null) {
			mChildFragments = new SparseArray<>();
		}
		if (mChildFragments.get(contentViewId) == null) {
			SparseArray<BaseFragment> arr = new SparseArray<>();
			mChildFragments.put(contentViewId, arr);
		}
		final SparseArray<BaseFragment> childArray = mChildFragments.get(contentViewId);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		BaseFragment child = childArray.get(index);
		// 如果子Fragment未被创建,则创建该Fragment,并添加到FragmentTransaction中
		if (child == null) {
			child = onCreateChildFragment(contentViewId, index);
			if (child != null) {
				childArray.put(index, child);
				transaction.add(contentViewId, child, getChildFragmentTag(contentViewId, index, child.getClass()));
			} else {
				throw new NullPointerException("Child Fragment not be success created! index:" + index);
			}
		} else {
			transaction.show(child);
		}
		// 隐藏其他子Fragment
		for (int i = 0; i < childArray.size(); i++) {
			final int key = childArray.keyAt(i);
			if (key != index) {
				if (childArray.get(key) != null) {
					transaction.hide(childArray.get(key));
				}
			}
		}
		try {
			transaction.commit();
			child.onDisplay();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 保存当前FrameLayout中正在显示的child下标
		if (mChildFragmentCurrentIndex == null) {
			mChildFragmentCurrentIndex = new SparseIntArray();
		}
		mChildFragmentCurrentIndex.put(contentViewId, index);
		getWindow().getDecorView().post(() -> onChildDisplayChanged(contentViewId, index));
	}

	public int getChildFragmentPosition(int contentId) {
		return mChildFragmentCurrentIndex.get(contentId);
	}

	/**
	 * 生成父FrameLayout中对应的index的Fragment实例
	 * @param contentViewId FrameLayoutId
	 * @param index         下标
	 */
	protected BaseFragment onCreateChildFragment(int contentViewId, int index) {
		return null;
	}

	/**
	 * 当子Fragment显示或隐藏时
	 * @param contentId       子Fragment所在的ContentViewId
	 * @param currentPosition 当前显示的下标
	 */
	protected void onChildDisplayChanged(int contentId, int currentPosition) {
		final SparseArray<BaseFragment> childArray = mChildFragments.get(contentId);
		if (childArray == null)
			return;
		for (int i = 0; i < childArray.size(); i++) {
			final int key = childArray.keyAt(i);
			if (childArray.get(key) != null) {
				if (key == currentPosition) {
					childArray.get(key).onDisplay();
				} else {
					childArray.get(key).onHide();
				}
			}
		}
	}

	/**
	 * 恢复fragment
	 * @param fragment fragment
	 */
	@Override
	public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);
		if (!(fragment instanceof BaseFragment))
			return;
		if (fragment.getTag() != null) {
			String tag = fragment.getTag();
			String[] info = tag.split(":");
			if (info.length > 1) {
				int contentViewId = FieldUtils.parseInt(info[0]);
				if (contentViewId > 0) {
					int index = FieldUtils.parseInt(info[1]);
					if (mChildFragments == null) {
						mChildFragments = new SparseArray<>();
					}
					SparseArray<BaseFragment> mContentFragments = mChildFragments.get(contentViewId);
					if (mChildFragments.get(contentViewId) == null) {
						mContentFragments = new SparseArray<>();
						mChildFragments.put(contentViewId, mContentFragments);
					}
					if (mContentFragments.get(index) == null) {
						mContentFragments.put(index, (BaseFragment) fragment);
					}
				}
			}
		}
	}

	/**
	 * 根据父FrameLayoutId及Fragment获取对应的Tag值
	 * @param contentViewId 父FrameLayout
	 * @param fragmentClass Fragment的Class
	 */
	private String getChildFragmentTag(int contentViewId, int index, Class<? extends BaseFragment> fragmentClass) {
		return String.valueOf(contentViewId) + ":" + index + ":" + fragmentClass.getName();
	}
}
