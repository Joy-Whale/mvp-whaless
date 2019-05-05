package me.whaless.app.domain.interactor.user;

import me.whaless.app.domain.executor.PostExecutionThread;
import me.whaless.app.domain.executor.ThreadExecutor;
import me.whaless.app.domain.interactor.UseCaseImpl;
import me.whaless.app.domain.model.user.SignEditor;
import me.whaless.app.domain.repository.IUserRepository;

import me.whaless.app.domain.model.user.UserModel;

import javax.inject.Inject;

import rx.Observable;

public class UserSignInUseCaseImpl extends UseCaseImpl<IUserRepository, SignEditor, UserModel> {

    @Inject
    UserSignInUseCaseImpl(IUserRepository repository, ThreadExecutor threadScheduler, PostExecutionThread postScheduler) {
        super(repository, threadScheduler, postScheduler);
    }
    
    @Override
    protected Observable<UserModel> buildUseCaseObservable(SignEditor... ts) {
        return getRepository().signIn(ts[0]);
    }
}
