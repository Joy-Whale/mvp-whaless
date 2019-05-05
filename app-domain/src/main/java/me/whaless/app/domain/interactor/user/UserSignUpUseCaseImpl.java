package me.whaless.app.domain.interactor.user;

import javax.inject.Inject;

import me.whaless.app.domain.executor.PostExecutionThread;
import me.whaless.app.domain.executor.ThreadExecutor;
import me.whaless.app.domain.interactor.UseCaseImpl;
import me.whaless.app.domain.model.ResponseModel;
import me.whaless.app.domain.model.user.SignEditor;
import me.whaless.app.domain.repository.IUserRepository;
import rx.Observable;

public class UserSignUpUseCaseImpl extends UseCaseImpl<IUserRepository, SignEditor, ResponseModel> {

    @Inject
    UserSignUpUseCaseImpl(IUserRepository repository, ThreadExecutor threadScheduler, PostExecutionThread postScheduler) {
        super(repository, threadScheduler, postScheduler);
    }
    
    @Override
    protected Observable<ResponseModel> buildUseCaseObservable(SignEditor... ts) {
        return getRepository().signUp(ts[0]);
    }
}
