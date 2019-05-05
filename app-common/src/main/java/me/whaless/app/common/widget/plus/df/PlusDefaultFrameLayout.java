package me.whaless.app.common.widget.plus.df;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.whaless.app.common.R;
import me.whaless.app.common.widget.plus.PlusFrameLayout;


/**
 * User: Joy
 * Date: 2016/10/22
 * Time: 12:41
 * 实现了自定义刷新样式，全局通用
 */

public class PlusDefaultFrameLayout extends PlusFrameLayout {


	public PlusDefaultFrameLayout(Context context) {
		super(context);
	}

	public PlusDefaultFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PlusDefaultFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	@Override
	protected void initChildView() {
		super.initChildView();
		View header = onCreateHeaderView(LayoutInflater.from(getContext()));
		if(header != null){
			setHeaderView(header);
		}
		View footer = onCreateFooterView(LayoutInflater.from(getContext()));
		if(footer != null){
			setFooterView(footer);
		}
		View loading = onCreateLoadingView(LayoutInflater.from(getContext()));
		if(loading != null){
			setLoadingView(loading);
		}
		View empty = onCreateEmptyView(LayoutInflater.from(getContext()));
		if(empty != null){
			setEmptyView(empty);
		}
		View error = onCreateErrorView(LayoutInflater.from(getContext()));
		if(error != null){
			setErrorView(error);
		}
		//  默认不显示三个状态view
		hideLoadingView();
		hideEmptyView();
		hideErrorView();
	}

	protected View onCreateHeaderView(LayoutInflater inflater) {
		//  添加顶部上拉加载布局
		return new PlusDefaultHeaderView(getContext());
	}

	protected View onCreateFooterView(LayoutInflater inflater) {
//		return new ClassicRefreshView(getContext());
		return null;
	}

	protected View onCreateLoadingView(LayoutInflater inflater) {
		return inflater.inflate(R.layout.plus_frame_refresh_loading, this, false);
	}

	protected View onCreateEmptyView(LayoutInflater inflater) {
		return inflater.inflate(R.layout.plus_frame_refresh_empty, this, false);
	}

	protected View onCreateErrorView(LayoutInflater inflater) {
		return inflater.inflate(R.layout.plus_frame_refresh_error, this, false);
	}
	//
	//	public void setLastRefreshTimeKey(Object object) {
	//		mHeader.setLastUpdateTimeKey(object.getClass().getName());
	//	}
	//
	//	public void setLastRefreshTimeKey(String key) {
	//		mHeader.setLastUpdateTimeKey(key);
	//	}
}
