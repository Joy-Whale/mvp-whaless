package me.whaless.app.presentation.subscriber;


import me.whaless.app.domain.DefaultSubscriber;

import rx.Observer;

/**
 * User: JiYu
 * DateModel: 2016-09-02
 * Time: 09-49
 * 数据加载Subscriber Wrapper
 */

public class LoadingSubscriber<T> extends DefaultSubscriber<T> {

	private Observer<T> observer;

	public LoadingSubscriber(Observer<T> observer) {
		this.observer = observer;
	}

	@Override
	public void onNext(T t) {
		observer.onNext(t);
	}

	/**
	 * 添加个Try-Catch是为了解决（Fatal Exception thrown on Scheduler.Worker thread）错误，能使用吗？待测试！
	 * @param e e
	 */
	@Override
	public void onError(Throwable e) {
		try {
			observer.onError(e);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void onCompleted() {
		observer.onCompleted();
	}
}
