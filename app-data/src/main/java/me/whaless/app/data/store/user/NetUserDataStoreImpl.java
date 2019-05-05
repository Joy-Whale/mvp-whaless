package me.whaless.app.data.store.user;

import me.whaless.app.data.cache.user.IUserCache;
import me.whaless.app.data.entity.ResponseEntity;
import me.whaless.app.data.entity.user.UserEntity;
import me.whaless.app.data.net.user.IUserNet;
import me.whaless.app.data.store.NetDataStoreImpl2;
import me.whaless.app.data.utils.UserControl;
import me.whaless.app.domain.model.user.SignEditor;
import rx.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * User: Joy
 * Date: 2017/1/3
 * Time: 15:34
 */
@Singleton
class NetUserDataStoreImpl extends NetDataStoreImpl2<IUserNet, IUserCache> implements IUserDataStore {

	@Inject
	NetUserDataStoreImpl(IUserNet service, IUserCache cache) {
		super(service, cache);
	}

	@Override
	public Observable<UserEntity> signIn(SignEditor editor) {
		return getService().signIn(editor).doOnNext(UserControl.getInstance()::onSignIn);
	}

	@Override
	public Observable<ResponseEntity> signUp(SignEditor editor) {
		return getService().signUp(editor);
	}


	@Override
	public Observable<UserEntity> info() {
		return getService().info().doOnNext(UserControl.getInstance()::onSignIn);
	}

}
