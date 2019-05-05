package me.whaless.app.data.repository;

import me.whaless.app.data.em.ResponseEntityMapper;
import me.whaless.app.data.em.user.UserEntityMapper;
import me.whaless.app.data.store.user.IUserDataStore;
import me.whaless.app.data.store.user.UserDataStoreFactory;
import me.whaless.app.domain.model.ResponseModel;
import me.whaless.app.domain.model.user.SignEditor;
import me.whaless.app.domain.model.user.UserModel;
import me.whaless.app.domain.repository.IUserRepository;
import rx.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * User: Joy
 * Date: 2017-01-15
 * Time: 13:55
 */
@Singleton
public class UserRepositoryImpl extends RepositoryImpl<IUserDataStore, UserDataStoreFactory> implements IUserRepository {

    private ResponseEntityMapper responseEntityMapper;
    private UserEntityMapper userEntityMapper;

    @Inject
    UserRepositoryImpl(UserDataStoreFactory dataStoreFactory, UserEntityMapper userEntityMapper, ResponseEntityMapper responseEntityMapper) {
        super(dataStoreFactory);
        this.userEntityMapper = userEntityMapper;
        this.responseEntityMapper = responseEntityMapper;
    }

    @Override
    public Observable<UserModel> signIn(SignEditor editor) {
        return getNetDataStore().signIn(editor).map(userEntityMapper::transform);
    }

    @Override
    public Observable<ResponseModel> signUp(SignEditor editor) {
        return getNetDataStore().signUp(editor).map(responseEntityMapper::transform);
    }

    @Override
    public Observable<UserModel> info() {
        return getNetDataStore().info().map(userEntityMapper::transform);
    }

}
