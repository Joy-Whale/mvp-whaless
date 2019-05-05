package me.whaless.app.presentation.presenter;

import androidx.annotation.NonNull;

import me.whaless.app.domain.interactor.UseCase;
import me.whaless.app.presentation.view.ILoadingView;
import me.whaless.app.presentation.view.IPageLoading;

/**
 * User: Joy
 * Date: 2017-01-15
 * Time: 9:56
 */

public class SimpleLoadingPresenterImpl<RQ, RPM, RP, V extends ILoadingView> extends LoadingPresenterImpl<RQ, RQ, RPM, RP, V> implements IPageLoadingPresenter {

	private IPageLoading mPageLoading;
	// 是否在PageLoadingView上显示Loading,一般的为PlusRecyclerView上
	private boolean shouldShowLoadingOnPageLoading = false;

	protected SimpleLoadingPresenterImpl(@NonNull UseCase<RQ, RPM> useCase) {
		super(useCase);
		setLoadingType(LOADING_TYPE_POST);
	}

	@Override
	public void initialize(RQ... qs) {
		execute(qs);
		if (mPageLoading != null) {
			mPageLoading.onLoading();
		}
	}

	@Override
	public void setPageLoading(IPageLoading pageLoading) {
		this.mPageLoading = pageLoading;
	}

	@Override
	public void showLoading() {
		if (!shouldShowLoadingOnPageLoading)
			super.showLoading();
	}

	@Override
	public void onError() {
		super.onError();
		if (mPageLoading != null) {
			mPageLoading.onLoadingError();
		}
	}

	@Override
	public void onSuccess() {
		super.onSuccess();
		if (mPageLoading != null) {
			mPageLoading.onLoadingSuccess();
		}
	}

	public void setShowLoadingOnPageLoading(boolean shouldShowLoadingOnPageLoading) {
		this.shouldShowLoadingOnPageLoading = shouldShowLoadingOnPageLoading;
	}
}
