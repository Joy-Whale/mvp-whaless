package me.whaless.app.domain.interactor.common;

import me.whaless.app.domain.executor.PostExecutionThread;
import me.whaless.app.domain.executor.ThreadExecutor;
import me.whaless.app.domain.interactor.UseCaseImpl;
import me.whaless.app.domain.model.ResponseModel;
import me.whaless.app.domain.repository.ICommonRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * User: Joy
 * Date: 2017/5/22
 * Time: 11:57
 */
public class TestUseCaseImpl extends UseCaseImpl<ICommonRepository, String, ResponseModel> {

	/**
	 * 构造参数
	 * @param repository      repository
	 * @param threadScheduler 工作线程
	 * @param postScheduler   结果执行线程
	 */
	@Inject
	TestUseCaseImpl(ICommonRepository repository, ThreadExecutor threadScheduler, PostExecutionThread postScheduler) {
		super(repository, threadScheduler, postScheduler);
	}

	@Override
	protected Observable<ResponseModel> buildUseCaseObservable(String... ts) {
		return getRepository().test(ts[0]);
	}
}
