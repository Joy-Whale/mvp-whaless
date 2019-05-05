package me.whaless.app.common.widget;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import me.whaless.app.common.utils.Logs;

/**
 * User: Joy
 * Date: 2016-11-13
 * Time: 14:11
 */

public class BaseFragment extends Fragment {

	private boolean isInitData = false;
	private boolean isDisplay = false;
	private boolean isViewReady;

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (view != null) {
			isViewReady = true;
			view.setOnClickListener(v -> Logs.d(""));
		}
	}

	/**
	 * 当显示的时候调用
	 */
	public void onDisplay() {
		isDisplay = true;
		if (!isInitData) {
			onInitData();
		}
	}

	protected boolean isViewReady() {
		return isViewReady;
	}

	public void onHide() {
		isDisplay = false;
	}

	/**
	 * 当第一次需要加载数据时调用
	 */
	protected void onInitData() {
		isInitData = true;
	}

	protected void onInitDataFalse() {
		isInitData = false;
	}

	public boolean isDisplay() {
		return isDisplay;
	}

	protected boolean isInitData() {
		return isInitData;
	}

	//	@Override
	//	public void onHiddenChanged(boolean hidden) {
	//		super.onHiddenChanged(hidden);
	//		if(!hidden){
	//			onDisplay();
	//		}
	//	}
}
