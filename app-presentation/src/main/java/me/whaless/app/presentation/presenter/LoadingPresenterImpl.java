package me.whaless.app.presentation.presenter;

import androidx.annotation.NonNull;

import com.whaless.app.data.R;
import me.whaless.app.data.exception.HttpResponseException;
import me.whaless.app.domain.interactor.UseCase;
import me.whaless.app.presentation.view.ILoadingView;


/**
 * User: JiYu
 * DateModel: 2016-09-01
 * Time: 18-16
 * @param <RQM> 请求参数(request) domain Model
 * @param <RQ>  请求参数 Presenter model
 * @param <RPM> 响应参数 Presenter model
 * @param <RP>  响应参数(response) domain Model
 * @param <V>   view
 */
public abstract class LoadingPresenterImpl<RQM, RQ, RPM, RP, V extends ILoadingView> extends ResultPresenterImpl<RQM, RQ, RPM, RP, V> implements ILoadingView {

	protected boolean mShowLoading = true;
	protected boolean mShowMessage = true;

	private int mDefaultLoadingType = LOADING_TYPE_PAGE;

	LoadingPresenterImpl(@NonNull UseCase<RQM, RPM> useCase) {
		super(useCase);
	}

	@Override
	protected final void execute(RQM... qs) {
		super.execute(qs);
		showLoading();
	}

	@Override
	public void onCompleted() {
		super.onCompleted();
	}

	@Override
	public void onError(Throwable e) {
		super.onError(e);
		//  如果有错误信息，则打印错误信息
		if (e instanceof HttpResponseException) {
			showMessage(e.getMessage());
			return;
		}
		showMessage(R.string.error_unknown_net);
	}

	@Override
	public void onFinish() {
		super.onFinish();
		hideLoading();
	}

	public void showMessage(String message) {
		if (getView() != null && canShowMessage())
			getView().showMessage(message);
	}

	public void showMessage(int resId) {
		if (getView() != null && canShowMessage())
			getView().showMessage(resId);
	}

	public void showLoading(int type) {
		if (getView() != null && canShowLoading())
			getView().showLoading(type);
	}

	@Override
	public void showLoading() {
		if (getView() != null && canShowLoading())
			showLoading(mDefaultLoadingType);
	}

	@Override
	public void hideLoading() {
		if (getView() != null && canShowLoading())
			getView().hideLoading();
	}

	public void setLoadingType(int type) {
		this.mDefaultLoadingType = type;
	}

	public void setShowLoading(boolean showLoading) {
		this.mShowLoading = showLoading;
	}

	public void setShowMessage(boolean showMessage) {
		this.mShowMessage = showMessage;
	}

	public boolean canShowLoading() {
		return mShowLoading;
	}

	public boolean canShowMessage() {
		return mShowMessage;
	}
}
