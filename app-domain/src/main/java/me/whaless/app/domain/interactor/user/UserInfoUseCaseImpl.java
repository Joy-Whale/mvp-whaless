package me.whaless.app.domain.interactor.user;

import me.whaless.app.domain.executor.PostExecutionThread;
import me.whaless.app.domain.executor.ThreadExecutor;
import me.whaless.app.domain.interactor.UseCaseImpl;
import me.whaless.app.domain.repository.IUserRepository;

import me.whaless.app.domain.model.user.UserModel;

import javax.inject.Inject;

import rx.Observable;

public class UserInfoUseCaseImpl extends UseCaseImpl<IUserRepository, Object, UserModel> {

    @Inject
    UserInfoUseCaseImpl(IUserRepository repository, ThreadExecutor threadScheduler, PostExecutionThread postScheduler) {
        super(repository, threadScheduler, postScheduler);
    }
    
    @Override
    protected Observable<UserModel> buildUseCaseObservable(Object... ts) {
        return getRepository().info();
    }
}
