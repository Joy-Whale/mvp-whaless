package me.whaless.app.domain.interactor;

import me.whaless.app.domain.executor.PostExecutionThread;
import me.whaless.app.domain.executor.ThreadExecutor;

import rx.Observable;

/**
 * User: JiYu
 * DateModel: 2016-09-05
 * Time: 09-28
 * @param <T>   Repository类型
 * @param <RQM> 请求参数
 * @param <RPM> 响应参数
 */

public abstract class UseCaseImpl<T, RQM, RPM> extends UseCase<RQM, RPM> {

	private T repository;

	/**
	 * 构造参数
	 * @param repository      repository
	 * @param threadScheduler 工作线程
	 * @param postScheduler   结果执行线程
	 */
	public UseCaseImpl(T repository, ThreadExecutor threadScheduler, PostExecutionThread postScheduler) {
		super(threadScheduler, postScheduler);
		this.repository = repository;
	}

	@Override
	protected abstract Observable<RPM> buildUseCaseObservable(RQM... ts);

	protected T getRepository() {
		return repository;
	}
}
