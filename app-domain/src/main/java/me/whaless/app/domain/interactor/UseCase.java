package me.whaless.app.domain.interactor;

import me.whaless.app.domain.executor.PostExecutionThread;
import me.whaless.app.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * UserModel: JiYu
 * DateModel: 2016-08-04
 * Time: 10-11
 * @param <RQM> 请求类型 request
 * @param <RPM> 响应类型 response
 */

public abstract class UseCase<RQM, RPM> {

	private ThreadExecutor threadScheduler;
	private PostExecutionThread postScheduler;

	private Subscription subscription = Subscriptions.empty();

	/**
	 * 构造参数
	 * @param threadScheduler 工作线程
	 * @param postScheduler   结果执行线程
	 */
	public UseCase(ThreadExecutor threadScheduler, PostExecutionThread postScheduler) {
		this.threadScheduler = threadScheduler;
		this.postScheduler = postScheduler;
	}

	/**
	 * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
	 */
	protected abstract Observable<RPM> buildUseCaseObservable(RQM... ts);

	public void execute(Subscriber<RPM> useCaseSubscriber, RQM... ts) {
		this.subscription = buildUseCaseObservable(ts)
				//  工作线程
				.subscribeOn(Schedulers.from(threadScheduler))
				//  结果线程
				.observeOn(postScheduler.getScheduler())
				//  工作取消线程
				.unsubscribeOn(Schedulers.from(threadScheduler))
				//
				.subscribe(useCaseSubscriber);
	}

	public void unsubscribe() {
		this.subscription.unsubscribe();
	}
}
