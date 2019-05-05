package me.whaless.app.domain.interactor.common;

import me.whaless.app.domain.executor.PostExecutionThread;
import me.whaless.app.domain.executor.ThreadExecutor;
import me.whaless.app.domain.interactor.UseCaseImpl;
import me.whaless.app.domain.model.Listable;
import me.whaless.app.domain.model.common.TestListModel;
import me.whaless.app.domain.repository.ICommonRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * User: Joy
 * Date: 2017/5/22
 * Time: 11:59
 */
public class TestListUseCaseImpl extends UseCaseImpl<ICommonRepository, Listable, TestListModel> {

	/**
	 * 构造参数
	 * @param repository      repository
	 * @param threadScheduler 工作线程
	 * @param postScheduler   结果执行线程
	 */
	@Inject
	TestListUseCaseImpl(ICommonRepository repository, ThreadExecutor threadScheduler, PostExecutionThread postScheduler) {
		super(repository, threadScheduler, postScheduler);
	}

	@Override
	protected Observable<TestListModel> buildUseCaseObservable(Listable... ts) {
		return getRepository().testList(ts[0]);
	}
}
