package me.whaless.app.common.widget.plus;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import com.whaless.app.common.R;

/**
 * User: JiYu
 * Date: 2016-09-24
 * Time: 13-34
 */

public class PlusFrameLayout extends PlusRefreshLayout implements IPlusFrame {

	private LoadingType mLoadingType = LoadingType.Get;
	private LoadingState mLoadingState = LoadingState.None;

	// 错误view
	protected View mErrorView;
	// 空view
	protected View mEmptyView;
	// 加载中View
	protected View mLoadingView;

	public PlusFrameLayout(Context context) {
		this(context, null);
	}

	public PlusFrameLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PlusFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initChildView();
		setRefreshEnabled(true);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initDefault();
	}

	/**
	 * 初始化子View，loadingView、emptyView、errorView等
	 */
	protected void initChildView() {

	}

	/**
	 * 当页面加载完毕后初始化一些基本信息
	 */
	public void initDefault() {

	}

	public void setHeaderView(View headerView) {
		if (this.mHeaderView != null) {
			removeView(this.mHeaderView);
		}
		this.mHeaderView = headerView;
		this.mHeaderView.setId(R.id.plus_refresh_header);
		addView(this.mHeaderView, -1, -2);
	}

	/**
	 * 设置上拉加载尾布局
	 * @param footerView footerView
	 */
	public void setFooterView(View footerView) {
		if (this.mFooterView != null) {
			removeView(this.mFooterView);
		}
		this.mFooterView = footerView;
		this.mFooterView.setId(R.id.plus_refresh_footer);
		addView(this.mFooterView, -1, -2);
	}

	/**
	 * 设置没数据时显示的view
	 * @param view view
	 */
	public void setEmptyView(View view) {
		if (mEmptyView != null) {
			removeView(mEmptyView);
		}
		if (view != null) {
			mEmptyView = view;
			addView(view, -1, -1);
		}
	}

	/**
	 * 设置出错时显示的view
	 * @param view view
	 */
	public void setErrorView(View view) {
		if (mErrorView != null) {
			removeView(mErrorView);
		}
		if (view != null) {
			mErrorView = view;
			addView(view, -1, -1);
//			onInitErrorListener(mOnErrorListener);
		}
	}

	/**
	 * 设置加载时显示的view
	 * @param view view
	 */
	public void setLoadingView(View view) {
		if (mLoadingView != null) {
			removeView(mLoadingView);
		}
		if (view != null) {
			mLoadingView = view;
			addView(view, -1, -1);
		}
	}

	public View getErrorView() {
		return mErrorView;
	}

	public View getEmptyView() {
		return mEmptyView;
	}

	public View getLoadingView() {
		return mLoadingView;
	}

	/**
	 * 初始化errorListener
	 */
	protected void onInitErrorListener(OnPlusErrorClickListener listener) {
		if (mErrorView != null) {
			mErrorView.setOnClickListener(v -> listener.onErrorClick());
		}
	}

	/**
	 * 设置ErrorView的点击事件
	 * @param mOnErrorListener errorClickListener
	 */
	public void setOnErrorListener(@NonNull OnPlusErrorClickListener mOnErrorListener) {
		this.mOnErrorListener = mOnErrorListener;
		onInitErrorListener(mOnErrorListener);
	}

	/**
	 * 设置当前的加载状态
	 * @param state 加载状态{@link LoadingState}
	 */
	public void setLoadingState(LoadingState state) {
		this.mLoadingState = state;
		onLoadingStateChanged(mLoadingState);
	}

	/**
	 * 获取当前的加载状态{@link LoadingState}
	 */
	public LoadingState getLoadingState() {
		return mLoadingState;
	}

	/**
	 * 获取当前的加载类型{@link LoadingType}
	 */
	public LoadingType getLoadingType() {
		return mLoadingType;
	}

	/**
	 * 当加载状态发生改变时
	 * @param state 加载状态{@link LoadingState}
	 */
	protected void onLoadingStateChanged(LoadingState state) {
		switch (state) {
			case None:
				hideLoadingView();
				hideEmptyView();
				hideErrorView();
				break;
			case Empty:
				hideLoadingView();
				showEmptyView();
				hideErrorView();
				break;
			case Error:
				hideLoadingView();
				hideEmptyView();
				showErrorView();
				break;
			case Loading:
			case NoMore:
				hideLoadingView();
				hideEmptyView();
				hideErrorView();
				break;
		}
	}

	@Override
	public void notifySuccess() {
		notifyDataSetChanged();
		setLoadingState(LoadingState.Loading);
	}

	/**
	 * 列表加载完成时，即没有更多数据可加载
	 */
	@Override
	public void notifyFinish() {
		notifyDataSetChanged();
		setLoadingState(LoadingState.NoMore);
	}

	@Override
	public void notifyError() {
		notifyDataSetChanged();
		setLoadingState(LoadingState.Error);
	}

	@Override
	public void notifyEmpty() {
		notifyDataSetChanged();
		setLoadingState(LoadingState.Empty);
	}

	/**
	 * 刷新当前的加载状态
	 * @param type 加载状态{@link LoadingType}
	 */
	@Override
	public void notifyLoading(LoadingType type) {
		this.mLoadingType = type;
		setLoadingState(type == LoadingType.More ? LoadingState.Loading : LoadingState.None);
		if (type == LoadingType.Get) {
			showLoadingView();
		}
		//		notifyDataSetChanged();
	}

	/**
	 * 通知数据状态发生改变
	 */
	protected void notifyDataSetChanged() {
		refreshComplete();
		loadMoreComplete();
		hideLoadingView();
	}

	protected synchronized void showLoadingView() {
		if (mLoadingView != null && mLoadingView.getVisibility() != VISIBLE) {
			mLoadingView.setVisibility(VISIBLE);
			onLoadingViewShow();
		}
	}

	protected synchronized void hideLoadingView() {
		if (mLoadingView != null && mLoadingView.getVisibility() != GONE) {
			mLoadingView.setVisibility(GONE);
			onLoadingViewHide();
		}
	}

	protected void onLoadingViewShow() {
		Log.d("PlusFrameLayout", "onLoadingViewShow");
	}

	protected void onLoadingViewHide() {
		Log.d("PlusFrameLayout", "onLoadingViewHide");
	}

	protected synchronized void showErrorView() {
		if (mErrorView != null && mErrorView.getVisibility() != VISIBLE) {
			mErrorView.setVisibility(VISIBLE);
		}
	}

	protected synchronized void hideErrorView() {
		if (mErrorView != null && mErrorView.getVisibility() != GONE) {
			mErrorView.setVisibility(GONE);
		}
	}

	protected synchronized void showEmptyView() {
		if (mEmptyView != null && mEmptyView.getVisibility() != VISIBLE) {
			mEmptyView.setVisibility(VISIBLE);
		}
	}

	protected synchronized void hideEmptyView() {
		if (mEmptyView != null && mEmptyView.getVisibility() != GONE) {
			mEmptyView.setVisibility(GONE);
		}
	}
}
