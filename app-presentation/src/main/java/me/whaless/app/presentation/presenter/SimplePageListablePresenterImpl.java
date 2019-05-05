package me.whaless.app.presentation.presenter;

import androidx.annotation.NonNull;

import me.whaless.app.common.utils.MapperImpl;
import me.whaless.app.domain.interactor.UseCase;
import me.whaless.app.domain.model.Listable;
import me.whaless.app.domain.model.PageListModel;
import me.whaless.app.presentation.bean.PageList;
import me.whaless.app.presentation.bm.PageListMapper;
import me.whaless.app.presentation.view.IPageListable;
import me.whaless.app.presentation.view.IPageListableView;

import javax.inject.Inject;

/**
 * User: Joy
 * Date: 2017/3/22
 * Time: 14:06
 */

public class SimplePageListablePresenterImpl<RQ extends Listable, RM, RP, RPM extends PageListModel<RM>, RPP extends PageList<RP>, RPMM extends PageListMapper<RM, RP, RPM, RPP, ? extends MapperImpl<RM, RP>>, V extends IPageListableView<RPP>> extends LoadingPresenterImpl<RQ, RQ, RPM, RPP, V> implements IPageListablePresenter, IPageListable {

	private RQ mListable;
	private IPageListable mPageListable;
	private RPMM mPageListMapper;

	/**
	 * 是否需要将loading显示在ListView上面
	 */
	private boolean shouldShowLoadingOnListView = true;

	@Inject
	public SimplePageListablePresenterImpl(@NonNull UseCase<RQ, RPM> useCase, RPMM mPageListMapper) {
		super(useCase);
		this.mPageListMapper = mPageListMapper;
		setLoadingType(LOADING_TYPE_PAGE);
	}

	@Override
	public void setPageListable(IPageListable listable) {
		this.mPageListable = listable;
	}

	@Override
	public void initialize(RQ... qs) {
		mListable = qs[0];
		execute(qs);
		// 如果当前加载状态为get，但是loading不显示到ListView中，则设置当前加载状态为refresh
		onLoadingTypeChanged(mListable.getLoadType() == Listable.LoadType.GET && (!shouldShowLoadingOnListView || !mShowLoading) ? Listable.LoadType.REFRESH : mListable
				.getLoadType());
	}

	/**
	 * 当PageList状态改变时
	 * @param state {@link IPageListable#LOADING_STATE_EMPTY}
	 */
	@Override
	public void onLoadingStateChanged(int state) {
		if (mPageListable != null) {
			mPageListable.onLoadingStateChanged(state);
		}
	}

	/**
	 * 设置pageList的加载状态
	 * @param loadingType get、refresh、more
	 */
	@Override
	public void onLoadingTypeChanged(int loadingType) {
		if (mPageListable != null) {
			mPageListable.onLoadingTypeChanged(loadingType);
		}
	}

	@Override
	public void showLoading() {
		if (mListable != null && mListable.getLoadType() == Listable.LoadType.GET) {
			if (!shouldShowLoadingOnListView)
				super.showLoading();
		}
	}

	@Override
	public void onNext(RPM rpm) {
		super.onNext(rpm);
		RPP pageList = mPageListMapper.transform(rpm);
		//		if(Math.max(pageList.getTotal(), pageList.getItemCount()) == 0)
		if (pageList.isEmpty() && pageList.getTotal() <= 0) {
			onLoadingStateChanged(IPageListable.LOADING_STATE_EMPTY);
			onGet(pageList);
			return;
		}
		// 是否有更多页面
		if (pageList.hasMore()) {
			onLoadingStateChanged(IPageListable.LOADING_STATE_LOADING);
			mListable.validateMore();
		} else {
			onLoadingStateChanged(IPageListable.LOADING_STATE_NOMORE);
		}
		if (mListable.isFirstPage()) {
			onGet(pageList);
		} else {
			onMore(pageList);
		}
	}

	@Override
	public void onError(Throwable e) {
		super.onError(e);
		onLoadingStateChanged(IPageListable.LOADING_STATE_ERROR);
	}

	protected void onGet(RPP pageList) {
		if (pageList != null) {
			if (pageList.isEmpty()) {
				onLoadingStateChanged(IPageListable.LOADING_STATE_EMPTY);
			}
			if (getView() != null) {
				getView().onGet(pageList);
			}
		} else {
			onLoadingStateChanged(IPageListable.LOADING_STATE_EMPTY);
		}
	}

	protected void onMore(RPP pageList) {
		getView().onMore(pageList);
	}

	public boolean shouldShowLoadingOnListView() {
		return shouldShowLoadingOnListView;
	}

	public void setShouldShowLoadingOnListView(boolean shouldShowLoadingOnListView) {
		this.shouldShowLoadingOnListView = shouldShowLoadingOnListView;
	}
}
