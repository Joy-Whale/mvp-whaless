package me.whaless.app.presentation.presenter;

import androidx.annotation.NonNull;

import me.whaless.app.domain.interactor.UseCase;
import me.whaless.app.presentation.subscriber.LoadingSubscriber;
import me.whaless.app.presentation.view.IView;

import rx.Observer;

/**
 * User: JiYu
 * DateModel: 2016-09-02
 * Time: 10-14
 * Q, QM, R, RM
 */

public abstract class ResultPresenterImpl<RQM, RQ, RPM, RP, V extends IView> implements IPresenter, IView, Observer<RPM> {

	private V view;
	private UseCase<RQM, RPM> useCase;

	protected ResultPresenterImpl(@NonNull UseCase<RQM, RPM> useCase) {
		this.useCase = useCase;
	}

	public void setView(@NonNull V view) {
		this.view = view;
	}

	public V getView() {
		return view;

	}

	protected UseCase<RQM, RPM> getUseCase() {
		return useCase;
	}

	public abstract void initialize(RQ... qs);

	protected void execute(RQM... qs) {
		getUseCase().execute(new LoadingSubscriber<>(this), qs);
	}

	@Override
	public void resume() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void destroy() {
		getUseCase().unsubscribe();
	}

	@Override
	public void onNext(RPM rpm) {

	}

	@Override
	public void onCompleted() {
		onSuccess();
		onFinish();
	}

	/**
	 * Observer中调用的onError{@link Observer#onError(Throwable)}
	 * @param e Throwable
	 */
	@Override
	public void onError(Throwable e) {
		e.printStackTrace();
		onError();
		onFinish();
	}

	@Override
	public void onInitiate() {
		if (getView() != null)
			getView().onInitiate();
	}

	@Override
	public void onSuccess() {
		if (getView() != null)
			getView().onSuccess();
	}

	/**
	 * View中的调用{@link IView#onError()}
	 */
	@Override
	public void onError() {
		if (getView() != null)
			getView().onError();
		//		onError(getClass());
	}

	//	/**
	//	 * View中的调用{@link IResultView#onError(Class)}
	//	 */
	//	@Override
	//	public void onError(Class clazz) {
	//		if (getView() != null)
	//			getView().onError(clazz);
	//	}

	@Override
	public void onFinish() {
		if (getView() != null)
			getView().onFinish();
	}
}
