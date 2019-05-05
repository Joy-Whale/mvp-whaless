package me.whaless.app.presentation.view;

import androidx.annotation.StringRes;

/**
 * User: Joy
 * Date: 2017/3/3
 * Time: 15:13
 */

public class LoadingViewWrapper implements ILoadingView {

	private ILoadingView mLoadingView;

	public LoadingViewWrapper(ILoadingView view) {
		this.mLoadingView = view;
	}

	@Override
	public void showLoading(int type) {
		if (mLoadingView != null)
			mLoadingView.showLoading(type);
	}

	@Override
	public void showLoading() {
		if (mLoadingView != null)
			mLoadingView.showLoading();
	}

	@Override
	public void hideLoading() {
		if (mLoadingView != null)
			mLoadingView.hideLoading();
	}

	@Override
	public void showMessage(String message) {
		if (mLoadingView != null)
			mLoadingView.showMessage(message);
	}

	@Override
	public void showMessage(@StringRes int resId) {
		if (mLoadingView != null)
			mLoadingView.showMessage(resId);
	}

	@Override
	public void onInitiate() {
		if (mLoadingView != null)
			mLoadingView.onInitiate();
	}

	@Override
	public void onSuccess() {
		if(mLoadingView != null)
			mLoadingView.onSuccess();
	}

	@Override
	public void onError() {
		if(mLoadingView != null)
			mLoadingView.onError();
	}

	@Override
	public void onFinish() {
		if(mLoadingView != null)
			mLoadingView.onFinish();
	}
}
